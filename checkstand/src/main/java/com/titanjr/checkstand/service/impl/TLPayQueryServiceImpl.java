/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLPayQueryServiceImpl.java
 * @author Jerry
 * @date 2017年12月1日 上午11:49:12  
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
import com.titanjr.checkstand.constants.RSPayStatusEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.request.TLNetBankPayQueryRequest;
import com.titanjr.checkstand.respnse.TitanPayQueryResponse;
import com.titanjr.checkstand.service.TLPayQueryService;
import com.titanjr.checkstand.util.SignMsgBuilder;

/**
 * @author Jerry
 * @date 2017年12月1日 上午11:49:12  
 */
@Service("tlPayQueryService")
public class TLPayQueryServiceImpl implements TLPayQueryService {
	
	private final static Logger logger = LoggerFactory.getLogger(TLPayQueryServiceImpl.class);

	@Override
	public TitanPayQueryResponse netBankPayQuery(TLNetBankPayQueryRequest 
			tlNetBankPayQueryRequest) {

		TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
		String responseStr ="";
		
		try {
			//查询订单，获取支付方式
			
			GateWayConfigDTO gateWayConfigDTO = SysConstant.gateWayConfigMap.get(tlNetBankPayQueryRequest.getMerchantId()+"_1_01_"+tlNetBankPayQueryRequest.getRequestType());
			tlNetBankPayQueryRequest.setSignMsg(SignMsgBuilder.getSignMsgForOrderQuery(tlNetBankPayQueryRequest, gateWayConfigDTO.getSecretKey()));
			logger.info("【通联-网银支付查询】网关地址：{}", gateWayConfigDTO.getGateWayUrl());
			
			HttpPost httpPost = new HttpPost(gateWayConfigDTO.getGateWayUrl());
			List<NameValuePair> params = BeanConvertor.beanToList(tlNetBankPayQueryRequest);
			logger.info("【通联-网银支付查询】请求参数：{}", tlNetBankPayQueryRequest.toString());
			
			HttpResponse httpRes = HttpClient.httpRequest(params, httpPost);
			if (null != httpRes) {
				HttpEntity entity = httpRes.getEntity();
				responseStr = EntityUtils.toString(entity, "UTF-8");
				return payQueryResult(responseStr, gateWayConfigDTO.getSecretKey());
				
			}else{
				logger.error("【通联-网银支付查询】失败 httpRes为空");
				titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanPayQueryResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【通联-网银支付查询】发生异常：", e);
			titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanPayQueryResponse;
			
		}
		
	}
	
	/**
	 * 解析返回结果
	 * @author Jerry
	 * @date 2017年12月1日 下午5:13:55
	 */
	private TitanPayQueryResponse payQueryResult(String responseStr, String key) throws UnsupportedEncodingException{
		
		TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
		logger.info("【通联-网银支付查询】返回信息：{}", responseStr);
		if(!StringUtil.isValidString(responseStr)){
			logger.error("【通联-网银支付查询】失败：返回消息为空");
			titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanPayQueryResponse;
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
		
		if (null != result.get("ERRORCODE")) {
			
			logger.error("【通联-网银支付查询】失败：{}", result.get("ERRORMSG"));
			titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.build(result.get("ERRORMSG")));
			return titanPayQueryResponse;

		} else {
			
			String payResult = result.get("payResult");
			if (payResult.equals("1")) {
				titanPayQueryResponse.setPayStatsu(RSPayStatusEnum.SUCCESS.getStatus());
				logger.info("【通联-网银支付查询】查询结果为:付款成功，payResult：{}", payResult);
				// 支付成功，验证签名
				PaymentResult paymentResult = new PaymentResult();
				paymentResult.setMerchantId(result.get("merchantId"));
				paymentResult.setVersion(result.get("version"));
				paymentResult.setLanguage(result.get("language"));
				paymentResult.setSignType(result.get("signType"));
				paymentResult.setPayType(result.get("payType"));
				paymentResult.setIssuerId(result.get("issuerId"));
				paymentResult.setPaymentOrderId(result
						.get("paymentOrderId"));
				paymentResult.setOrderNo(result.get("orderNo"));
				paymentResult.setOrderDatetime(result
						.get("orderDatetime"));
				paymentResult.setOrderAmount(result.get("orderAmount"));
				paymentResult.setPayAmount(result.get("payAmount"));
				paymentResult.setPayDatetime(result.get("payDatetime"));
				paymentResult.setExt1(result.get("ext1"));
				paymentResult.setExt2(result.get("ext2"));
				paymentResult.setPayResult(result.get("payResult"));
				paymentResult.setErrorCode(result.get("errorCode"));
				paymentResult.setReturnDatetime(result
						.get("returnDatetime"));
				paymentResult.setKey(key);
				paymentResult.setSignMsg(result.get("signMsg"));

				boolean verifyResult = paymentResult.verify();

				if (verifyResult) {
					logger.info("【通联-网银支付查询】订单支付成功，验签成功，orderNo={}", paymentResult.getOrderNo());
				} else {
					logger.error("【通联-网银支付查询】订单支付成功，验签失败，orderNo={}", paymentResult.getOrderNo());
				}

			} else {//后续关注支付失败是怎么返回的
				logger.info("【通联-网银支付查询】查询结果为：订单尚未付款");
				titanPayQueryResponse.setPayStatsu(RSPayStatusEnum.PROCESS.getStatus());
				/*titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.build("订单尚未付款"));
				return titanPayQueryResponse;*/
			}
			
			return titanPayQueryResponse;
		}
	}

}
