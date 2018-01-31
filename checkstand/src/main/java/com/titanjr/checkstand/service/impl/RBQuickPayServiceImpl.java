package com.titanjr.checkstand.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.enums.RefundStatusEnum;
import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RBRefundStatusEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RSPayStatusEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.RBBindCardQueryRequest;
import com.titanjr.checkstand.request.RBCardAuthRequest;
import com.titanjr.checkstand.request.RBCardBINQueryRequest;
import com.titanjr.checkstand.request.RBDataRequest;
import com.titanjr.checkstand.request.RBQuickPayConfirmRequest;
import com.titanjr.checkstand.request.RBQuickPayQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayRefundQueryRequest;
import com.titanjr.checkstand.request.RBQuickPayRefundRequest;
import com.titanjr.checkstand.request.RBQuickPayRequest;
import com.titanjr.checkstand.request.RBReSendMsgRequest;
import com.titanjr.checkstand.request.RBUnBindCardRequest;
import com.titanjr.checkstand.respnse.RBBaseResponse;
import com.titanjr.checkstand.respnse.RBBindCardQueryResponse;
import com.titanjr.checkstand.respnse.RBCardBINQueryResponse;
import com.titanjr.checkstand.respnse.RBPayConfirmResponse;
import com.titanjr.checkstand.respnse.RBPayQueryResponse;
import com.titanjr.checkstand.respnse.RBQuickPayResponse;
import com.titanjr.checkstand.respnse.RBReSendMsgResponse;
import com.titanjr.checkstand.respnse.RBRefundQueryResponse;
import com.titanjr.checkstand.respnse.RBUnBindCardResponse;
import com.titanjr.checkstand.respnse.TitanBindCardQueryResponse;
import com.titanjr.checkstand.respnse.TitanCardBINQueryResponse;
import com.titanjr.checkstand.respnse.TitanOrderRefundResponse;
import com.titanjr.checkstand.respnse.TitanPayConfirmResponse;
import com.titanjr.checkstand.respnse.TitanPayQueryResponse;
import com.titanjr.checkstand.respnse.TitanQuickPayResponse;
import com.titanjr.checkstand.respnse.TitanReSendMsgResponse;
import com.titanjr.checkstand.respnse.TitanRefundQueryResponse;
import com.titanjr.checkstand.respnse.TitanUnBindCardResponse;
import com.titanjr.checkstand.service.RBQuickPayService;
import com.titanjr.checkstand.util.BuilderUtil;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.SignMsgBuilder;
import com.titanjr.checkstand.util.rbUtil.Decipher;


/**
 * 融宝支付服务实现
 * @author Jerry
 * @date 2017年12月18日 下午2:54:28
 */
@Service("rbQuickPayService")
public class RBQuickPayServiceImpl implements RBQuickPayService {
	
	private final static Logger logger = LoggerFactory.getLogger(RBQuickPayServiceImpl.class);

	
	@Override
	public TitanCardBINQueryResponse cardBINQuery(RBCardBINQueryRequest rbCardBINQueryRequest) {
		
		TitanCardBINQueryResponse titanCardBINQueryResponse = new TitanCardBINQueryResponse();
		RBCardBINQueryResponse rbCardBINQueryResponse = new RBCardBINQueryResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbCardBINQueryRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbCardBINQueryRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-卡BIN查询】失败，获取网关配置为空，configKey={}", configKey);
				titanCardBINQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanCardBINQueryResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.cardBINQuerySign(rbCardBINQueryRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbCardBINQueryRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-卡BIN查询】参数错误：{}", res.getReturnMessage());
				titanCardBINQueryResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanCardBINQueryResponse;
			}
			logger.info("【融宝-卡BIN查询】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbCardBINQueryRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-卡BIN查询】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbCardBINQueryResponse = (RBCardBINQueryResponse)JsonUtil.jsonToBean(responseStr, RBCardBINQueryResponse.class);
				logger.info("【融宝-卡BIN查询】返回信息:" + rbCardBINQueryResponse.toString());
				
				if(!"0000".equals(rbCardBINQueryResponse.getResult_code())){
					titanCardBINQueryResponse.putErrorResult(RSErrorCodeEnum.build(rbCardBINQueryResponse.getResult_msg()));
					return titanCardBINQueryResponse;
				}
				titanCardBINQueryResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				titanCardBINQueryResponse.setBankName(rbCardBINQueryResponse.getBank_name());
				titanCardBINQueryResponse.setBankInfo(rbCardBINQueryResponse.getBank_code().toLowerCase());
				titanCardBINQueryResponse.setCardNo(rbCardBINQueryResponse.getCard_no());
				if("1".equals(rbCardBINQueryResponse.getBank_card_type())){
					titanCardBINQueryResponse.setCardType("11");
				}else{
					titanCardBINQueryResponse.setCardType("10");
				}
				titanCardBINQueryResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanCardBINQueryResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanCardBINQueryResponse;
				
			}else{
				
				logger.error("【融宝-卡BIN查询】失败 httpRes为空，cardNo=" + rbCardBINQueryRequest.getCard_no());
				titanCardBINQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanCardBINQueryResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-卡BIN查询】发生异常：{}", e);
			titanCardBINQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanCardBINQueryResponse;
			
		}
	}
	
	
	@Override
	public TitanQuickPayResponse contractPay(RBQuickPayRequest rbQuickPayRequest) {
		
		TitanQuickPayResponse titanQuickPayResponse = new TitanQuickPayResponse();
		RBQuickPayResponse rbQuickPayResponse = new RBQuickPayResponse();
		String contractType = "融宝-已绑卡签约";
		if(RequestTypeEnum.QUICK_PAY_DEPOSIT.getKey().equals(rbQuickPayRequest.getRequestType())){
			contractType = "融宝-储蓄卡签约";
		}else if(RequestTypeEnum.QUICK_PAY_CREDIT.getKey().equals(rbQuickPayRequest.getRequestType())){
			contractType = "融宝-信用卡签约";
		}
		
		try {
			
			//获取网关配置
			String configKey = rbQuickPayRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbQuickPayRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【"+contractType+"】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, rbQuickPayRequest.getOrder_no());
				titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanQuickPayResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.quickPaySign(rbQuickPayRequest, gateWayConfigDTO
					.getSecretKey(), rbQuickPayRequest.getRequestType());
			
			ValidateResponse res = GenericValidate.validateNew(rbQuickPayRequest);
			if (!res.isSuccess()){
				logger.error("【"+contractType+"】参数错误：{}", res.getReturnMessage());
				titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanQuickPayResponse;
			}
			logger.info("【"+contractType+"】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbQuickPayRequest.getMerchant_id());
			
			//发送请求
			logger.info("【"+contractType+"】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbQuickPayResponse = (RBQuickPayResponse)JsonUtil.jsonToBean(responseStr, RBQuickPayResponse.class);
				logger.info("【"+contractType+"】返回信息:" + rbQuickPayResponse.toString());
				
				if(!"0000".equals(rbQuickPayResponse.getResult_code())){
					titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.build(rbQuickPayResponse.getResult_msg()));
					return titanQuickPayResponse;
				}
				titanQuickPayResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				titanQuickPayResponse.setOrderNo(rbQuickPayResponse.getOrder_no());
				//titanQuickPayResponse.setOrderAmount();//未返回
				//titanQuickPayResponse.setOrderTime(rbQuickPayResponse);//未返回
				//titanQuickPayResponse.setPayType(rbQuickPayResponse);//未返回
				if(StringUtil.isValidString(rbQuickPayResponse.getBind_id())){
					titanQuickPayResponse.setBindCardId(rbQuickPayResponse.getBind_id());
				}
				if(StringUtil.isValidString(rbQuickPayResponse.getCertificate())){
					titanQuickPayResponse.setCertificate(rbQuickPayResponse.getCertificate());
				}
				titanQuickPayResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanQuickPayResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanQuickPayResponse;
				
			}else{
				
				logger.error("【"+contractType+"】失败 httpRes为空，orderNo=" + rbQuickPayRequest.getOrder_no());
				titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanQuickPayResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【"+contractType+"】发生异常：{}", e);
			titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanQuickPayResponse;
			
		}
		
	}
	
	
	@Override
	public TitanPayConfirmResponse payConfirm(RBQuickPayConfirmRequest rbQuickPayConfirmRequest) {

		TitanPayConfirmResponse titanPayConfirmResponse = new TitanPayConfirmResponse();
		RBPayConfirmResponse rbPayConfirmResponse = new RBPayConfirmResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbQuickPayConfirmRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbQuickPayConfirmRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-确认支付】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, rbQuickPayConfirmRequest.getOrder_no());
				titanPayConfirmResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanPayConfirmResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.quickPayConfirmSign(rbQuickPayConfirmRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbQuickPayConfirmRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-确认支付】参数错误：{}", res.getReturnMessage());
				titanPayConfirmResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanPayConfirmResponse;
			}
			logger.info("【融宝-确认支付】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbQuickPayConfirmRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-确认支付】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbPayConfirmResponse = (RBPayConfirmResponse)JsonUtil.jsonToBean(responseStr, RBPayConfirmResponse.class);
				logger.info("【融宝-确认支付】返回信息:" + rbPayConfirmResponse.toString());
				
				if(!"0000".equals(rbPayConfirmResponse.getResult_code())){
					titanPayConfirmResponse.putErrorResult(RSErrorCodeEnum.build(rbPayConfirmResponse.getResult_msg()));
					return titanPayConfirmResponse;
				}
				titanPayConfirmResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				titanPayConfirmResponse.setOrderNo(rbPayConfirmResponse.getOrder_no());
				//titanPayConfirmResponse.setPayType(payType); 未返回
				titanPayConfirmResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanPayConfirmResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanPayConfirmResponse;
				
			}else{
				
				logger.error("【融宝-确认支付】失败 httpRes为空，orderNo=" + rbQuickPayConfirmRequest.getOrder_no());
				titanPayConfirmResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanPayConfirmResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-确认支付】发生异常：{}", e);
			titanPayConfirmResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanPayConfirmResponse;
			
		}
	}
	
	
	@Override
	public TitanReSendMsgResponse reSendMsg(RBReSendMsgRequest rbReSendMsgRequest) {

		TitanReSendMsgResponse titanReSendMsgResponse = new TitanReSendMsgResponse();
		RBReSendMsgResponse rbReSendMsgResponse = new RBReSendMsgResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbReSendMsgRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbReSendMsgRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-重发验证码】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, rbReSendMsgRequest.getOrder_no());
				titanReSendMsgResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanReSendMsgResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.reSendMsgSign(rbReSendMsgRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbReSendMsgRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-重发验证码】参数错误：{}", res.getReturnMessage());
				titanReSendMsgResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanReSendMsgResponse;
			}
			logger.info("【融宝-重发验证码】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbReSendMsgRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-重发验证码】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbReSendMsgResponse = (RBReSendMsgResponse)JsonUtil.jsonToBean(responseStr, RBReSendMsgResponse.class);
				logger.info("【融宝-重发验证码】返回信息:" + rbReSendMsgResponse.toString());
				
				if(!"0000".equals(rbReSendMsgResponse.getResult_code())){
					titanReSendMsgResponse.putErrorResult(RSErrorCodeEnum.build(rbReSendMsgResponse.getResult_msg()));
					return titanReSendMsgResponse;
				}
				titanReSendMsgResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				titanReSendMsgResponse.setOrderNo(rbReSendMsgResponse.getOrder_no());
				//titanReSendMsgResponse.setPayType(payType);未返回
				titanReSendMsgResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanReSendMsgResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanReSendMsgResponse;
				
			}else{
				
				logger.error("【融宝-重发验证码】失败 httpRes为空，orderNo=" + rbReSendMsgRequest.getOrder_no());
				titanReSendMsgResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanReSendMsgResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-重发验证码】发生异常：{}", e);
			titanReSendMsgResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanReSendMsgResponse;
			
		}
	}
	
	
	@Override
	public TitanPayQueryResponse payQuery(RBQuickPayQueryRequest rbQuickPayQueryRequest) {

		TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
		RBPayQueryResponse rbPayQueryResponse = new RBPayQueryResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbQuickPayQueryRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbQuickPayQueryRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-快捷支付查询】失败，获取网关配置为空，configKey={}", configKey);
				titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanPayQueryResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.quickPayQuerySign(rbQuickPayQueryRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbQuickPayQueryRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-快捷支付查询】参数错误：{}", res.getReturnMessage());
				titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanPayQueryResponse;
			}
			logger.info("【融宝-快捷支付查询】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbQuickPayQueryRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-快捷支付查询】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbPayQueryResponse = (RBPayQueryResponse)JsonUtil.jsonToBean(responseStr, RBPayQueryResponse.class);
				logger.info("【融宝-快捷支付查询】返回信息:" + rbPayQueryResponse.toString());
				
				if(!"0000".equals(rbPayQueryResponse.getResult_code())){
					titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.build(rbPayQueryResponse.getResult_msg()));
					return titanPayQueryResponse;
				}
				titanPayQueryResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				titanPayQueryResponse.setOrderNo(rbPayQueryResponse.getOrder_no());
				titanPayQueryResponse.setOrderAmount(rbPayQueryResponse.getTotal_fee());
				titanPayQueryResponse.setPayAmount(rbPayQueryResponse.getTotal_fee());
				//titanPayQueryResponse.setOrderpayNo(orderpayNo);
				titanPayQueryResponse.setOrderPayTime(rbPayQueryResponse.getTimestamp());
				//titanPayQueryResponse.setOrderTime(orderTime);
				titanPayQueryResponse.setPayStatsu(RSPayStatusEnum.convertRBPayStatus2RS(rbPayQueryResponse.getStatus()));
				titanPayQueryResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanPayQueryResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanPayQueryResponse;
				
			}else{
				
				logger.error("【融宝-快捷支付查询】失败 httpRes为空，orderNo=" + rbQuickPayQueryRequest.getOrder_no());
				titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanPayQueryResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-快捷支付查询】发生异常：{}", e);
			titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanPayQueryResponse;
			
		}
	}
	
	
	@Override
	public TitanOrderRefundResponse refund(RBQuickPayRefundRequest rbQuickPayRefundRequest) {

		TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
		RBBaseResponse rbRefundResponse = new RBBaseResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbQuickPayRefundRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbQuickPayRefundRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-快捷支付退款】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, rbQuickPayRefundRequest.getOrig_order_no());
				titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanOrderRefundResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.quickPayRefundSign(rbQuickPayRefundRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbQuickPayRefundRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-快捷支付退款】参数错误：{}", res.getReturnMessage());
				titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanOrderRefundResponse;
			}
			logger.info("【融宝-快捷支付退款】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbQuickPayRefundRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-快捷支付退款】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbRefundResponse = (RBBaseResponse)JsonUtil.jsonToBean(responseStr, RBBaseResponse.class);
				logger.info("【融宝-快捷支付退款】返回信息：result_code={}，result_msg={}", rbRefundResponse.getResult_code(), rbRefundResponse.getResult_msg());
				if(!"0000".equals(rbRefundResponse.getResult_code())){
					titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.build(rbRefundResponse.getResult_msg()));
					return titanOrderRefundResponse;
				}
				titanOrderRefundResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				/*titanOrderRefundResponse.setOrderAmount(orderAmount);
				titanOrderRefundResponse.setOrderNo(orderNo);
				titanOrderRefundResponse.setOrderTime(orderTime);
				titanOrderRefundResponse.setRefundAmount(refundAmount);
				titanOrderRefundResponse.setRefundOrderno(refundOrderno);
				titanOrderRefundResponse.setRefundTime(refundTime);*/
				titanOrderRefundResponse.setRefundStatus(RefundStatusEnum.REFUND_SUCCESS.status.toString());
				titanOrderRefundResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanOrderRefundResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanOrderRefundResponse;
				
			}else{
				
				logger.error("【融宝-快捷支付退款】失败 httpRes为空，orderNo=" + rbQuickPayRefundRequest.getOrder_no());
				titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanOrderRefundResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-快捷支付退款】发生异常：{}", e);
			titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanOrderRefundResponse;
			
		}
	}
	
	
	@Override
	public TitanRefundQueryResponse refundQuery(RBQuickPayRefundQueryRequest rbQuickPayRefundQueryRequest) {

		TitanRefundQueryResponse titanRefundQueryResponse = new TitanRefundQueryResponse();
		RBRefundQueryResponse rbRefundQueryResponse = new RBRefundQueryResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbQuickPayRefundQueryRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbQuickPayRefundQueryRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-快捷支付退款查询】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, rbQuickPayRefundQueryRequest.getOrig_order_no());
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanRefundQueryResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.quickPayRefundQuerySign(rbQuickPayRefundQueryRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbQuickPayRefundQueryRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-快捷支付退款查询】参数错误：{}", res.getReturnMessage());
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanRefundQueryResponse;
			}
			logger.info("【融宝-快捷支付退款查询】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbQuickPayRefundQueryRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-快捷支付退款查询】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbRefundQueryResponse = (RBRefundQueryResponse)JsonUtil.jsonToBean(responseStr, RBRefundQueryResponse.class);
				logger.info("【融宝-快捷支付退款查询】返回信息:" + rbRefundQueryResponse.toString());
				if(!"0000".equals(rbRefundQueryResponse.getResult_code())){
					titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.build(rbRefundQueryResponse.getResult_msg()));
					return titanRefundQueryResponse;
				}
				titanRefundQueryResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
				/*titanRefundQueryResponse.setOrderAmount(orderAmount);
				titanRefundQueryResponse.setOrderNo(orderNo);
				titanRefundQueryResponse.setOrderTime(orderTime);*/
				/*titanRefundQueryResponse.setRefundOrderno(refundOrderno);
				titanRefundQueryResponse.setRefundTime(refundTime);*/
				titanRefundQueryResponse.setRefundAmount(rbRefundQueryResponse.getTotal_fee());
				titanRefundQueryResponse.setRefundStatus(RBRefundStatusEnum.trans2TitanStatus(
						rbRefundQueryResponse.getStatus()));
				titanRefundQueryResponse.setVersion(SysConstant.RS_VERSION_NEW);
				titanRefundQueryResponse.setSignType(SysConstant.RS_SIGN_TYPE);
				return titanRefundQueryResponse;
				
			}else{
				
				logger.error("【融宝-快捷支付退款查询】失败 httpRes为空，orderNo=" + rbQuickPayRefundQueryRequest.getOrder_no());
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanRefundQueryResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-快捷支付退款查询】发生异常：{}", e);
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanRefundQueryResponse;
			
		}
	}
	
	
	@Override
	public Map<String, Object> cardAuth(RBCardAuthRequest rbCardAuthRequest) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			//获取网关配置
			String configKey = rbCardAuthRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbCardAuthRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-卡密鉴权】失败，获取网关配置为空，configKey={}", configKey);
				return null;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.cardAuthSign(rbCardAuthRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbCardAuthRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-卡密鉴权】参数错误：{}", res.getReturnMessage());
				return null;
			}
			logger.info("【融宝-卡密鉴权】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbCardAuthRequest.getMerchant_id());
			logger.info("【融宝-卡密鉴权】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			
			map.put("gateWayUrl", gateWayConfigDTO.getGateWayUrl());
			map.put("rbDataRequest", rbDataRequest);
			
			return map;
			
		} catch (Exception e) {
			
			logger.error("【融宝-卡密鉴权】发生异常：{}", e);
			return null;
			
		}
	}
	
	
	@Override
	public TitanBindCardQueryResponse bindCardList(RBBindCardQueryRequest rbBindCardQueryRequest) {
		
		TitanBindCardQueryResponse titanBindCardQueryResponse = new TitanBindCardQueryResponse();
		RBBindCardQueryResponse rbBindCardQueryResponse = new RBBindCardQueryResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbBindCardQueryRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbBindCardQueryRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-查询绑卡列表】失败，获取网关配置为空，configKey={}", configKey);
				titanBindCardQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanBindCardQueryResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.queryBindCardSign(rbBindCardQueryRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbBindCardQueryRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-查询绑卡列表】参数错误：{}", res.getReturnMessage());
				titanBindCardQueryResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanBindCardQueryResponse;
			}
			logger.info("【融宝-查询绑卡列表】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbBindCardQueryRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-查询绑卡列表】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbBindCardQueryResponse = (RBBindCardQueryResponse)JsonUtil.jsonToBean(responseStr, RBBindCardQueryResponse.class);
				logger.info("【融宝-查询绑卡列表】返回信息:" + rbBindCardQueryResponse.toString());
				
				return BuilderUtil.convertBindCardQueryRes(rbBindCardQueryResponse);
				
			}else{
				
				logger.error("【融宝-查询绑卡列表】失败 httpRes为空，用户ID=" + rbBindCardQueryRequest.getMember_id());
				titanBindCardQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanBindCardQueryResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-查询绑卡列表】发生异常：{}", e);
			titanBindCardQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanBindCardQueryResponse;
			
		}
	}
	
	
	@Override
	public TitanUnBindCardResponse unBindCard(RBUnBindCardRequest rbUnBindCardRequest) {

		TitanUnBindCardResponse titanUnBindCardResponse = new TitanUnBindCardResponse();
		RBUnBindCardResponse rbUnBindCardResponse = new RBUnBindCardResponse();
		
		try {
			
			//获取网关配置
			String configKey = rbUnBindCardRequest.getMerchant_id() +"_" + PayTypeEnum.QUICK_NEW.combPayType + 
					"_" + SysConstant.RB_CHANNEL_CODE + "_" + rbUnBindCardRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【融宝-解绑卡】失败，获取网关配置为空，configKey={}", configKey);
				titanUnBindCardResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanUnBindCardResponse;
			}
			
			//签名并获取排序后的参数
			TreeMap<String,String> params = SignMsgBuilder.unBindCardSign(rbUnBindCardRequest, 
					gateWayConfigDTO.getSecretKey());
			
			ValidateResponse res = GenericValidate.validateNew(rbUnBindCardRequest);
			if (!res.isSuccess()){
				logger.error("【融宝-解绑卡】参数错误：{}", res.getReturnMessage());
				titanUnBindCardResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanUnBindCardResponse;
			}
			logger.info("【融宝-解绑卡】请求参数：{}", CommonUtil.treeMapString(params));
			
			//数据加密
			String json = JsonUtil.objectToJson(params);
			RBDataRequest rbDataRequest = Decipher.encryptData(json, rbUnBindCardRequest.getMerchant_id());
			
			//发送请求
			logger.info("【融宝-解绑卡】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> paramsList = BeanConvertor.beanToList(rbDataRequest);
			HttpResponse httpRes = HttpClient.httpRequest(paramsList, httpPost);
			
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				// 解密返回的数据
				responseStr = Decipher.decryptData(responseStr);
				rbUnBindCardResponse = (RBUnBindCardResponse)JsonUtil.jsonToBean(responseStr, RBUnBindCardResponse.class);
				logger.info("【融宝-解绑卡】返回信息:" + rbUnBindCardResponse.toString());
				
				return BuilderUtil.convertUnBindCardRes(rbUnBindCardResponse);
				
			}else{
				
				logger.error("【融宝-解绑卡】失败 httpRes为空");
				titanUnBindCardResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanUnBindCardResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【融宝-解绑卡】发生异常：{}", e);
			titanUnBindCardResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanUnBindCardResponse;
			
		}
	}
	
}
