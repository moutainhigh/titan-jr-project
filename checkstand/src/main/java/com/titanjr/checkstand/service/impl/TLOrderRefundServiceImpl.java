/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLOrderRefundServiceImpl.java
 * @author Jerry
 * @date 2017年12月4日 下午2:42:58  
 */
package com.titanjr.checkstand.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.allinpay.ets.client.PaymentResult;
import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.TLNetBankOrderRefundRequest;
import com.titanjr.checkstand.respnse.TitanOrderRefundResponse;
import com.titanjr.checkstand.service.TLOrderRefundService;
import com.titanjr.checkstand.util.SignMsgBuilder;

/**
 * @author Jerry
 * @date 2017年12月4日 下午2:42:58  
 */
@Service("tlOrderRefundService")
public class TLOrderRefundServiceImpl implements TLOrderRefundService {
	
	private final static Logger logger = LoggerFactory.getLogger(TLOrderRefundServiceImpl.class);
	

	@Override
	public TitanOrderRefundResponse netBankOrderRefund(TLNetBankOrderRefundRequest 
			tlNetBankOrderRefundRequest) {
		
		TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
		String responseStr ="";
		
		try {
			//查询订单，获取支付方式
			
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(tlNetBankOrderRefundRequest.getMerchantId()+"_1_01_"+tlNetBankOrderRefundRequest.getRequestType());
			tlNetBankOrderRefundRequest.setSignMsg(SignMsgBuilder.getSignMsgForOrderRefund(tlNetBankOrderRefundRequest, gateWayConfigDTO.getSecretKey()));
			logger.info("【通联-订单退款】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> params = BeanConvertor.beanToList(tlNetBankOrderRefundRequest);
			logger.info("【通联-订单退款】请求参数：{}", tlNetBankOrderRefundRequest.toString());
			
			HttpResponse httpRes = HttpClient.httpRequest(params, httpPost);
			if (null != httpRes) {
				
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				
				return orderRefundResult(responseStr, gateWayConfigDTO.getSecretKey());
				
			}else{
				logger.error("【通联-订单退款】失败 httpRes为空");
				titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);;
				return titanOrderRefundResponse;
			}
			
		} catch (Exception e) {
			logger.error("【通联-订单退款】发生异常：{}", e);
			titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanOrderRefundResponse;
		}
	}
	
	
	/**
	 * 退款返回结果解析
	 * @author Jerry
	 * @throws UnsupportedEncodingException 
	 * @date 2017年12月4日 下午5:05:00
	 */
	private TitanOrderRefundResponse orderRefundResult(String responseStr, String key) 
			throws UnsupportedEncodingException{
		
		TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
		logger.info("【通联-订单退款】返回信息：{}", responseStr);
		if(!StringUtil.isValidString(responseStr)){
			logger.error("【通联-订单退款】失败：返回消息为空");
			titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanOrderRefundResponse;
		}
		responseStr = URLDecoder.decode(responseStr, "UTF-8");
		
		Map<String, String> result = new HashMap<String, String>();
		String[] parameters = responseStr.split("&");
		for (int i = 0; i < parameters.length; i++) {
			String msg = parameters[i];
			int index = msg.indexOf('=');
			if (index > 0) {
				String name = msg.substring(0, index);
				String value = msg.substring(index + 1);
				result.put(name, value);
			}
		}
		
		if (StringUtil.isValidString(result.get("ERRORCODE"))) {
			
			//订单重复退款会返回：退款金额不能大于可退金额
			logger.error("【通联-订单退款】失败：{}", result.get("ERRORMSG"));
			titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.build(result.get("ERRORMSG")));
			return titanOrderRefundResponse;

		} else {
			
			PaymentResult paymentResult = new PaymentResult();
			paymentResult.setMerchantId(result.get("merchantId").toString());
			paymentResult.setVersion(result.get("version").toString());
			paymentResult.setSignType(result.get("signType").toString());
			paymentResult.setOrderNo(result.get("orderNo").toString());
			paymentResult.setOrderDatetime(result.get("orderDatetime").toString());
			paymentResult.setOrderAmount(result.get("orderAmount").toString());
			paymentResult.setErrorCode(null==result.get("errorCode")?"":result.get("errorCode").toString());
			paymentResult.setRefundAmount(result.get("refundAmount").toString());
			paymentResult.setRefundDatetime(result.get("refundDatetime").toString());
			paymentResult.setRefundResult(result.get("refundResult").toString());
			paymentResult.setMchtRefundOrderNo(null==result.get("mchtRefundOrderNo")?"":result.get("mchtRefundOrderNo").toString());
			paymentResult.setReturnDatetime(result.get("returnDatetime").toString());
			paymentResult.setSignMsg(result.get("signMsg").toString()); 
			paymentResult.setKey(key);
			
			//验证签名：返回true代表验签成功；否则验签失败。
			boolean verifyResult = paymentResult.verify();
			if(!verifyResult){
				logger.info("【通联-订单退款】验签失败，orderNo={}", paymentResult.getOrderNo());
			}else{
				logger.info("【通联-订单退款】验签成功");
			}
			
			if("20".equals(paymentResult.getRefundResult())){
				logger.info("【通联-订单退款】退款成功，orderNo={}", paymentResult.getOrderNo());
				titanOrderRefundResponse.setRefundStatus("2");
			}else{
				logger.info("【通联-订单退款】退款失败，orderNo={}", paymentResult.getOrderNo());
				titanOrderRefundResponse.setRefundStatus("3");
			}
			return titanOrderRefundResponse;
			
		}
	}

}
