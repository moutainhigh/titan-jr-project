/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanCommonServiceImpl.java
 * @author Jerry
 * @date 2018年1月15日 下午3:15:17  
 */
package com.titanjr.checkstand.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.request.TLNetBankPayCallbackRequest;
import com.titanjr.checkstand.respnse.TLPayCallBackResponse;
import com.titanjr.checkstand.service.TitanCommonService;
import com.titanjr.checkstand.util.SignMsgBuilder;

/**
 * @author Jerry
 * @date 2018年1月15日 下午3:15:17  
 */
@Service("titanCommonServiceImpl")
public class TitanCommonServiceImpl implements TitanCommonService {

	private final static Logger logger = LoggerFactory.getLogger(TitanCommonServiceImpl.class);
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	

	@Override
	public TLPayCallBackResponse payConfirmCallback(TLNetBankPayCallbackRequest tlNetBankPayCallbackRequest) {
		
		TLPayCallBackResponse payCallBackResponse = new TLPayCallBackResponse();
		
		try {
			
			//获取系统配置
			SysConfig config = titanFinancialUtilService.querySysConfig();
			
			//构建参数
			payCallBackResponse = buildPayCallbackRequest(tlNetBankPayCallbackRequest);
			if(!payCallBackResponse.isSuccess()){
				return payCallBackResponse;
			}
			payCallBackResponse.putError();
			
			RechargeResultConfirmRequest confirmRequest = payCallBackResponse.getRechargeResultConfirmRequest();
			confirmRequest.setSignMsg(SignMsgBuilder.getpayCallbackSignMsg(confirmRequest, config.getRsCheckKey()));
			
			payCallBackResponse.setPayConfirmPageUrl(config.getCsPayConfirmPageURL());
			payCallBackResponse.setRechargeResultConfirmRequest(confirmRequest);
			logger.info("【网银支付前台回调】地址：{}", config.getCsPayConfirmPageURL());
			
			payCallBackResponse.putSuccess();
			return payCallBackResponse;
			
		} catch (Exception e) {
			
			logger.error("【网银支付前台回调】发生异常：", e);
			return payCallBackResponse;
			
		}
		
	}
	
	
	@Override
	public TLPayCallBackResponse PayNotice(TLNetBankPayCallbackRequest tlNetBankPayCallbackRequest) {
		
		TLPayCallBackResponse payCallBackResponse = new TLPayCallBackResponse();
		
		try {
			
			//获取系统配置
			SysConfig config = titanFinancialUtilService.querySysConfig();
			
			//构建参数
			payCallBackResponse = buildPayCallbackRequest(tlNetBankPayCallbackRequest);
			if(!payCallBackResponse.isSuccess()){
				return payCallBackResponse;
			}
			payCallBackResponse.putError();
			
			RechargeResultConfirmRequest confirmRequest = payCallBackResponse.getRechargeResultConfirmRequest();
			confirmRequest.setSignMsg(SignMsgBuilder.getpayCallbackSignMsg(confirmRequest, config.getRsCheckKey()));
			
			logger.info("【网银支付后台通知】地址：{}", config.getCsPayNoticeURL());
			HttpPost httpPost = new HttpPost(config.getCsPayNoticeURL());
			List<NameValuePair> params = BeanConvertor.beanToList(confirmRequest);
			logger.info("【网银支付后台通知】请求参数：{}" ,confirmRequest.toString());
			
			HttpResponse httpRes = HttpClient.httpRequest(params, httpPost);
			if (null != httpRes) {
				HttpEntity entity = httpRes.getEntity();
				String resStr = EntityUtils.toString(entity, "UTF-8");
				//RSConvertFiled2ObjectUtil.convertField2ObjectSuper(QuickPaymentResponse.class, HttpRes);
				logger.info("【网银支付后台通知】返回信息：{}" ,resStr);
				Map<String, String> result = new HashMap<String, String>();
				String[] parameters = resStr.split("&");
				for (int i = 0; i < parameters.length; i++) {
					String msg = parameters[i];
					int index = msg.indexOf('=');
					if (index > 0) {
						String name = msg.substring(0, index);
						String value = msg.substring(index + 1);
						result.put(name, value);
					}
				}
				if (!StringUtil.isValidString(result.get("returnCode")) || !result.get("returnCode").equals("000000")) {
					return payCallBackResponse;
				}
				
				payCallBackResponse.putSuccess();
				return payCallBackResponse;
				
			}else{
				logger.error("【网银支付后台通知】失败，httpRes为空，参数params:"+Tools.gsonToString(params));
				return payCallBackResponse;
			}
			
		} catch (Exception e) {
			
			logger.error("【网银支付后台通知】发生异常：", e);
			return payCallBackResponse;
			
		}
		
	}
	
	
	private TLPayCallBackResponse buildPayCallbackRequest(TLNetBankPayCallbackRequest tlNetBankPayCallbackRequest){
		
		TLPayCallBackResponse payCallBackResponse = new TLPayCallBackResponse();
		payCallBackResponse.putError();
		
		//查询交易单
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setOrderid(tlNetBankPayCallbackRequest.getOrderNo());
		TransOrderDTO transOrderDTO  = titanOrderService.queryTransOrderDTO(transOrderRequest);
		if(transOrderDTO == null){
			logger.error("【支付路由-支付回调】失败，查询交易单为空，orderNo={}", tlNetBankPayCallbackRequest.getOrderNo());
			return payCallBackResponse;
		}
		//查询充值单
		TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
		titanOrderPayDTO.setMerchantNo(transOrderDTO.getUserid());
		titanOrderPayDTO.setOrderNo(tlNetBankPayCallbackRequest.getOrderNo());
		titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
		if(titanOrderPayDTO == null){
			logger.error("【支付路由-支付回调】失败，查询充值单为空，orderNo={}", tlNetBankPayCallbackRequest.getOrderNo());
			return payCallBackResponse;
		}
		
		RechargeResultConfirmRequest confirmRequest = new RechargeResultConfirmRequest();
		confirmRequest.setMerchantNo(SysConstant.RS_MERCHANT_NO);
		confirmRequest.setOrderNo(tlNetBankPayCallbackRequest.getOrderNo());
		confirmRequest.setOrderAmount(tlNetBankPayCallbackRequest.getOrderAmount().toString());
		confirmRequest.setPayAmount(tlNetBankPayCallbackRequest.getPayAmount().toString());
		confirmRequest.setOrderTime(tlNetBankPayCallbackRequest.getOrderDatetime());
		confirmRequest.setPayStatus("0");
		confirmRequest.setPayMsg("支付失败");
		if("1".equals(tlNetBankPayCallbackRequest.getPayResult())){
			confirmRequest.setPayStatus("3");
			confirmRequest.setPayMsg("支付成功");
		}
		confirmRequest.setOrderNo(tlNetBankPayCallbackRequest.getOrderNo());
		confirmRequest.setPayOrderNo(transOrderDTO.getPayorderno());
		confirmRequest.setOrderPayTime(tlNetBankPayCallbackRequest.getPayDatetime());
		confirmRequest.setPayType(titanOrderPayDTO.getPayType());
		confirmRequest.setVersion(SysConstant.RS_VERSION);
		confirmRequest.setSignType(SysConstant.RS_SIGN_TYPE);
		payCallBackResponse.setRechargeResultConfirmRequest(confirmRequest);
		
		payCallBackResponse.putSuccess();
		return payCallBackResponse;
		
	}

}
