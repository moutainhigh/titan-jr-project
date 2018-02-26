/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLPaymentServiceImpl.java
 * @author Jerry
 * @date 2017年12月18日 上午10:48:44  
 */
package com.titanjr.checkstand.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.constants.TLQrReturnCodeEnum;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.TLQrCodePayRequest;
import com.titanjr.checkstand.respnse.TLQrCodePayResponse;
import com.titanjr.checkstand.respnse.TitanQrCodePayResponse;
import com.titanjr.checkstand.service.TLPaymentService;
import com.titanjr.checkstand.util.CommonUtil;

/**
 * 通联支付服务实现类
 * @author Jerry
 * @date 2017年12月18日 上午10:48:44  
 */
@Service("tlPaymentService")
public class TLPaymentServiceImpl implements TLPaymentService {
	
	private static Logger logger = LoggerFactory.getLogger(TLPaymentServiceImpl.class);
	

	@Override
	public TitanQrCodePayResponse qrCodePay(TLQrCodePayRequest tlQrCodePayRequest) {
		
		TitanQrCodePayResponse titanQrCodePayResponse = new TitanQrCodePayResponse();
		TLQrCodePayResponse tlQrCodePayResponse = new TLQrCodePayResponse();
		String responseStr ="";
		
		try {
			
			//获取网关配置
			String configKey = tlQrCodePayRequest.getCusid() +"_" + PayTypeEnum.QR_WECHAT_URL.combPayType + 
					"_" + SysConstant.TL_CHANNEL_CODE + "_" + tlQrCodePayRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【通联-扫码支付】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, tlQrCodePayRequest.getVersion());
				titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanQrCodePayResponse;
			}
			
			//构建参数，发送请求
			String params = buildRequestParam(tlQrCodePayRequest, gateWayConfigDTO.getAppId(), 
					gateWayConfigDTO.getSecretKey());
			logger.info("【通联-扫码支付】请求参数：{}", CommonUtil.toLineString(params));
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl() + "?" + params.toString());
			HttpResponse httpRes = HttpClient.httpRequest(new ArrayList<NameValuePair>(), httpPost);
			
			if (null != httpRes) {
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				logger.info("【通联-扫码支付】返回信息：{}", responseStr);
				
				tlQrCodePayResponse = (TLQrCodePayResponse)JsonUtil.jsonToBean(responseStr, TLQrCodePayResponse.class);
				return getPayResult(tlQrCodePayResponse);
				
			}else{
				
				logger.error("【通联-扫码支付】失败 httpRes为空");
				titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);;
				return titanQrCodePayResponse;
			}
			
		} catch (Exception e) {
			logger.error("【通联-扫码支付】发生异常：", e);
			titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanQrCodePayResponse;
		}
		
	}
	
	
	@Override
	public TitanQrCodePayResponse wechatPay(TLQrCodePayRequest tlQrCodePayRequest) {

		TitanQrCodePayResponse titanQrCodePayResponse = new TitanQrCodePayResponse();
		TLQrCodePayResponse tlQrCodePayResponse = new TLQrCodePayResponse();
		String responseStr ="";
		
		try {
			
			//获取网关配置
			String configKey = tlQrCodePayRequest.getCusid() +"_" + PayTypeEnum.QR_WECHAT_URL.combPayType + 
					"_" + SysConstant.TL_CHANNEL_CODE + "_" + tlQrCodePayRequest.getRequestType();
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(configKey);
			if(gateWayConfigDTO == null){
				logger.error("【通联-微信公众号支付】失败，获取网关配置为空，configKey={}，orderNo={}", configKey, tlQrCodePayRequest.getVersion());
				titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanQrCodePayResponse;
			}
			
			//构建参数，发送请求
			String params = buildRequestParam(tlQrCodePayRequest, gateWayConfigDTO.getAppId(), 
					gateWayConfigDTO.getSecretKey());
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl() + "?" + params.toString());
			HttpResponse httpRes = HttpClient.httpRequest(new ArrayList<NameValuePair>(), httpPost);
			
			//返回结果
			if (null != httpRes) {
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				logger.info("【通联-微信公众号支付】返回信息：{}", responseStr);
				
				tlQrCodePayResponse = (TLQrCodePayResponse)JsonUtil.jsonToBean(responseStr, TLQrCodePayResponse.class);
				return getPayResult(tlQrCodePayResponse);
				
			}else{
				
				logger.error("【通联-微信公众号支付】失败 httpRes为空");
				titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);;
				return titanQrCodePayResponse;
			}
			
		} catch (Exception e) {
			logger.error("【通联-微信公众号支付】发生异常：", e);
			titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanQrCodePayResponse;
		}
	}
	
	
	/**
	 * 构建参数
	 * @author Jerry
	 * @date 2018年1月22日 下午4:01:14
	 */
	private String buildRequestParam(TLQrCodePayRequest tlQrCodePayRequest, String appId, 
			String secretKey) throws UnsupportedEncodingException{
		
		//TreeMap会按字段名的ASCLL码从小到大排序
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("cusid", tlQrCodePayRequest.getCusid());
		params.put("appid", appId);
		params.put("version", tlQrCodePayRequest.getVersion());
		params.put("trxamt", String.valueOf(tlQrCodePayRequest.getTrxamt()));
		params.put("reqsn", tlQrCodePayRequest.getReqsn());
		params.put("paytype", tlQrCodePayRequest.getPaytype());
		params.put("randomstr", tlQrCodePayRequest.getRandomstr());
		params.put("body", tlQrCodePayRequest.getBody());
		params.put("notify_url", tlQrCodePayRequest.getNotify_url());
		if(StringUtil.isValidString(tlQrCodePayRequest.getAcct())){
			params.put("acct", tlQrCodePayRequest.getAcct());
		}
		params.put("key", secretKey);
		
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> entry:params.entrySet()){
			if(entry.getValue()!=null&&entry.getValue().length()>0){
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		logger.info("【通联-扫码/公众号支付】加密前排序为：{}", sb.toString());
		String md5Msg = MD5.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		logger.info("【通联-扫码/公众号支付】加密后sign为：{}", md5Msg);
		params.put("sign", md5Msg);
		params.remove("key");
		
		StringBuilder paramBuf = new StringBuilder();
    	boolean isNotFirst = false;
    	for (Map.Entry<String, String> entry: params.entrySet()){
    		if(entry.getValue()!=null&&entry.getValue().length()>0){
	    		if (isNotFirst)
	    			paramBuf.append('&');
	    		isNotFirst = true;
	    		paramBuf
	    			.append(entry.getKey())
	    			.append('=')
	    			.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
    		}
    	}
    	
    	logger.info("【通联-扫码/公众号支付】上送参数：{}", paramBuf.toString());
		return paramBuf.toString();
		
	}
	
	/**
	 * 解析返回结果
	 * @author Jerry
	 * @date 2018年1月22日 下午4:00:52
	 */
	private TitanQrCodePayResponse getPayResult(TLQrCodePayResponse tlQrCodePayResponse){
		
		TitanQrCodePayResponse titanQrCodePayResponse = new TitanQrCodePayResponse();
		
		if(!tlQrCodePayResponse.qrCodeResult()){
			logger.error("【通联-扫码/公众号支付】失败，retmsg：{}", tlQrCodePayResponse.getRetmsg()+"，"+tlQrCodePayResponse.getErrmsg());
			titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.build(tlQrCodePayResponse.getRetmsg()+"，"+tlQrCodePayResponse.getErrmsg()));
			return titanQrCodePayResponse;
		}
		
		if(TLQrReturnCodeEnum.TRADE_SUCCESS.getCode().equals(tlQrCodePayResponse.getTrxstatus())){

			logger.info("【通联-扫码/公众号支付】成功");
			titanQrCodePayResponse.setMerchantNo(SysConstant.RS_MERCHANT_NO);
			titanQrCodePayResponse.setOrderNo(tlQrCodePayResponse.getReqsn());
			titanQrCodePayResponse.setPayMsg(TLQrReturnCodeEnum.TRADE_SUCCESS.getRemark());
			titanQrCodePayResponse.setRespJs(tlQrCodePayResponse.getPayinfo());
			
		}else {//支付失败
			
			TLQrReturnCodeEnum em = TLQrReturnCodeEnum.getEnumByCode(tlQrCodePayResponse.getTrxstatus());
			titanQrCodePayResponse.putErrorResult(RSErrorCodeEnum.build(em.getRemark()));
		}
		
		return titanQrCodePayResponse;
		
	}

}
