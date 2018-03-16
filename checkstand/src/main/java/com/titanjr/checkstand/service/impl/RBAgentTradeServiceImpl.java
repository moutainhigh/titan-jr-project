/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBAgentTradeServiceImpl.java
 * @author Jerry
 * @date 2018年2月5日 下午6:16:51  
 */
package com.titanjr.checkstand.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;

import com.fangcang.util.DateUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.enums.WithDrawStatusEnum;
import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.util.JsonUtil;
import com.titanjr.checkstand.constants.AgentRetCodeEnum;
import com.titanjr.checkstand.constants.TradeCodeEnum;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.RBAgentDownloadRequest;
import com.titanjr.checkstand.request.RBAgentPayQueryRequest;
import com.titanjr.checkstand.request.RBAgentPayRequest;
import com.titanjr.checkstand.request.RBDataRequest;
import com.titanjr.checkstand.respnse.RBAgentPayQueryResponse;
import com.titanjr.checkstand.respnse.RBAgentPayResponse;
import com.titanjr.checkstand.respnse.RSResponse;
import com.titanjr.checkstand.respnse.TitanAgentPayResponse;
import com.titanjr.checkstand.respnse.TitanAgentQueryResponse;
import com.titanjr.checkstand.service.RBAgentTradeService;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.SignMsgBuilder;
import com.titanjr.checkstand.util.rbUtil.Decipher;

/**
 * 融宝代付服务实现
 * @author Jerry
 * @date 2018年2月5日 下午6:16:51  
 */
@Service("rbAgentTradeService")
public class RBAgentTradeServiceImpl implements RBAgentTradeService {
	
	private static final Logger logger = LoggerFactory.getLogger(RBAgentTradeServiceImpl.class);
	private final String resUrl = this.getClass().getResource("/").getPath().replace("classes/", "");
	private final String tmpUrl = System.getProperty("java.io.tmpdir");//建议临时存放tmp目录
	
	@Resource
	private TitanSysconfigService titanSysconfigService;

	@Override
	public TitanAgentPayResponse agentPay(RBAgentPayRequest rbAgentPayRequest) {
		
		TitanAgentPayResponse titanAgentPayResponse = new TitanAgentPayResponse();
		RBAgentPayResponse rbAgentPayResponse = new RBAgentPayResponse();
		
		try{
			
			String configKey = rbAgentPayRequest.getMerchant_id() +"_" + PayTypeEnum.AGENT_TRADE.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbAgentPayRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-代付请求】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, rbAgentPayRequest.getBatch_no());
				titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanAgentPayResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.rbAgentPaySign(rbAgentPayRequest, gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbAgentPayRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-代付请求】参数错误：{}", res.getReturnMessage());
				titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanAgentPayResponse;
			}
			logger.info("【融宝-代付请求】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbAgentPayRequest.getMerchant_id(), resUrl);
			rbDataRequest.setVersion(rbAgentPayRequest.getVersion());
			
			//发送请求
			logger.info("【融宝-代付请求】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr, resUrl);
				rbAgentPayResponse = (RBAgentPayResponse)JsonUtil.jsonToBean(responseStr, RBAgentPayResponse.class);
				logger.info("【融宝-代付请求】返回信息:" + rbAgentPayResponse.toString());
				
				if(!SysConstant.RB_QUICKPAY_SUCCESS.equals(rbAgentPayResponse.getResult_code())){
					titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.build(rbAgentPayResponse.getResult_msg()));
					return titanAgentPayResponse;
				}
				titanAgentPayResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				titanAgentPayResponse.setOrderNo(rbAgentPayResponse.getBatch_no());
				titanAgentPayResponse.setTradeCode(TradeCodeEnum.RB_AGENT_PAY.getCode());
				titanAgentPayResponse.setStatus(WithDrawStatusEnum.WithDraw_SUCCESSED.getKey().toString());
				titanAgentPayResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanAgentPayResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanAgentPayResponse;
				
			}else{
				
				logger.error("【融宝-代付请求】失败 httpRes为空，orderNo=" + rbAgentPayResponse.getBatch_no());
				titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanAgentPayResponse;
			}
			
		}catch(Exception e){
			
			logger.error("【融宝-代付请求】异常：", e);
			titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
		}
		
		return titanAgentPayResponse;
	}
	
	
	@Override
	public TitanAgentQueryResponse agentQuery(RBAgentPayQueryRequest rbAgentPayQueryRequest) {

		TitanAgentQueryResponse titanAgentQueryResponse = new TitanAgentQueryResponse();
		RBAgentPayQueryResponse rbAgentPayQueryResponse = new RBAgentPayQueryResponse();
		
		try{
			
			String configKey = rbAgentPayQueryRequest.getMerchant_id() +"_" + PayTypeEnum.AGENT_TRADE.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbAgentPayQueryRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-代付查询】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, rbAgentPayQueryRequest.getBatch_no());
				titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanAgentQueryResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.rbAgentQuerySign(rbAgentPayQueryRequest, gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbAgentPayQueryRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-代付查询】参数错误：{}", res.getReturnMessage());
				titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanAgentQueryResponse;
			}
			logger.info("【融宝-代付查询】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbAgentPayQueryRequest.getMerchant_id(), resUrl);
			rbDataRequest.setVersion(rbAgentPayQueryRequest.getVersion());
			
			//发送请求
			logger.info("【融宝-代付查询】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr, resUrl);
				rbAgentPayQueryResponse = (RBAgentPayQueryResponse)JsonUtil.jsonToBean(responseStr, RBAgentPayQueryResponse.class);
				logger.info("【融宝-代付查询】返回信息:" + rbAgentPayQueryResponse.toString());
				
				if(!SysConstant.RB_AGENT_SUCCESS.equals(rbAgentPayQueryResponse.getResult_code())){
					titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.build(rbAgentPayQueryResponse.getResult_msg()));
					return titanAgentQueryResponse;
				}
				titanAgentQueryResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				titanAgentQueryResponse.setOrderNo(rbAgentPayQueryResponse.getBatch_no());
				titanAgentQueryResponse.setTradeCode(TradeCodeEnum.RB_AGENT_QUERY.getCode());
				titanAgentQueryResponse.setRetCode(AgentRetCodeEnum.RET_CODE_SUCCESS.code);
				titanAgentQueryResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanAgentQueryResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				//content格式：序号,银行账户,开户名,分行,支行,开户行,公/私,金额,币种,备注,商户订单号,交易反馈,失败原因
				String tradeResult = rbAgentPayQueryResponse.getContent().split(",")[11];
				if("失败".equals(tradeResult)){
					titanAgentQueryResponse.setTradeStatus("0");
				}else{
					titanAgentQueryResponse.setTradeStatus("1");
				}
				return titanAgentQueryResponse;
				
			}else{
				
				logger.error("【融宝-代付查询】失败 httpRes为空，orderNo=" + rbAgentPayQueryResponse.getBatch_no());
				titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanAgentQueryResponse;
			}
			
		}catch(Exception e){
			
			logger.error("【融宝-代付查询】异常：", e);
			titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
		}
		
		return titanAgentQueryResponse;
	}
	
	
	@Override
	public RSResponse agentDownload(RBAgentDownloadRequest rbAgentDownloadRequest) {

		RSResponse response = new RSResponse();

		try {
			String configKey = rbAgentDownloadRequest.getMerchant_id() + "_" + PayTypeEnum.AGENT_TRADE.combPayType +
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbAgentDownloadRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if (gateWayConfigDTO == null) {
				logger.error("【融宝-对账文件下载】失败，获取网关配置为空，configKey={}", configKey);
				response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return response;
			}

			String transferFormat = DateUtil.dateToString(DateUtil.stringToDate(rbAgentDownloadRequest.
					getTradeDate(), "yyyyMMdd"), "yyyy-MM-dd");

			String accountFileName = rbAgentDownloadRequest.getTradeDate() + ".txt";
			File accountLocal = new File(tmpUrl + SysConstant.RB_ACCOUNT_DIR);
			accountLocal.mkdir();
			String rechargeFileName = transferFormat + ".txt";
			File rechargeLocal = new File(tmpUrl + SysConstant.RB_RECHARGE_DIR);
			rechargeLocal.mkdir();
			String refundFileName = transferFormat + ".txt";
			File refundLocal = new File(tmpUrl + SysConstant.RB_REFUND_DIR);
			refundLocal.mkdir();

			//登录融宝ftp下载文件
			FtpUtil util = new FtpUtil();
			util.loginRemote(SysConstant.RB_FTP_HOST, SysConstant.RB_FTP_USER, SysConstant.RB_FTP_PWD);
			logger.info("login rbFtp success");
			util.downloadFile(accountFileName, accountLocal.getPath(), SysConstant.RB_ACCOUNT_DIR);
			util.downloadFile(rechargeFileName, rechargeLocal.getPath(), SysConstant.RB_RECHARGE_DIR + transferFormat);
			util.downloadFile(refundFileName,refundLocal.getPath() , SysConstant.RB_REFUND_DIR + transferFormat);
			util.ftpLogOut();

			//登录房仓ftp并上传
			FTPConfigResponse configResponse = titanSysconfigService.getFTPConfig();
			util = new FtpUtil(configResponse.getFtpServerIp(),
					configResponse.getFtpServerPort(),
					configResponse.getFtpServerUser(),
					configResponse.getFtpServerPassword());
			util.ftpLogin();
			logger.info("login fcFtp success");

			FTPFileUpload(util, "ACCOUNT-", SysConstant.RB_ACCOUNT_DIR, accountFileName);
			FTPFileUpload(util, "RECHARGE-", SysConstant.RB_RECHARGE_DIR, rechargeFileName);
			FTPFileUpload(util, "REFUND-", SysConstant.RB_REFUND_DIR, refundFileName);

			util.ftpLogOut();
			logger.info("对账文件下载并上传成功");

			response.setMerchantNo(SysConstant.RS_MERCHANT_NO);
			response.setVersion(SysConstant.RS_VERSION);
			response.setSignType(SysConstant.RS_SIGN_TYPE);
			return response;

		} catch (Exception e) {
			logger.error("【融宝-对账文件下载】发生异常：", e);
			response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}

	}


	private boolean FTPFileUpload(FtpUtil util, String prefix, String baseDir, String fileName) {
		File file = new File(tmpUrl + baseDir + fileName);
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			util.uploadStream(prefix + fileName, inputStream, FtpUtil.UPLOAD_PATH_RB_AGENT_CHECKING);
		} catch (FileNotFoundException e) {
			logger.error("本地文件流读取失败,本地文件路径：{}", file.getPath(), e);
			return false;
		} catch (Exception e) {
			logger.error("FTP上传失败", e);
			return false;
		}

		logger.info("upload to fcFtp success fileName=" + baseDir + fileName);
		return true;
	}

}
