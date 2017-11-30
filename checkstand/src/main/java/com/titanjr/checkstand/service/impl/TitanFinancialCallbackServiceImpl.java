/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanFinancialCallbackServiceImpl.java
 * @author Jerry
 * @date 2017年11月25日 上午9:51:27  
 */
package com.titanjr.checkstand.service.impl;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.BeanConvertor;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.titanjr.checkstand.request.titanjr.TitanPayCallbackRequest;
import com.titanjr.checkstand.service.TitanFinancialCallbackService;

/**
 * @author Jerry
 * @date 2017年11月25日 上午9:51:27  
 */
@Service
public class TitanFinancialCallbackServiceImpl implements TitanFinancialCallbackService {
	
	private final static Logger logger = LoggerFactory.getLogger(TitanFinancialCallbackServiceImpl.class);

	@Override
	public void PayConfirmPageCallback(TitanPayCallbackRequest payCallbackRequest) {
		
		String response ="";
		String gateWayURL = "http://192.168.0.77:8084/titanjr-pay-app/payment/payConfirmPage.action";
		logger.info("【网关接口】为：{}", gateWayURL);
		logger.info("【泰坦金融页面回调】请求参数：{}" + payCallbackRequest.toString());
		
		try {
			HttpPost httpPost = new HttpPost(gateWayURL);
			List<NameValuePair> confirmPageParams = BeanConvertor.beanToList(payCallbackRequest);
			
			HttpResponse confirmPageResp = HttpClient.httpRequest(confirmPageParams, httpPost);
			if (null != confirmPageResp) {
				HttpEntity entity = confirmPageResp.getEntity();
				response = EntityUtils.toString(entity, "UTF-8");
				//quickPaymentResponse = RSConvertFiled2ObjectUtil.convertField2ObjectSuper(QuickPaymentResponse.class, response);
				logger.info("【泰坦金融页面回调】返回信息：{}", response.toString());
				
			}else{
				logger.error("【泰坦金融页面回调】失败, confirmPageResp为空");
			}
		} catch (Exception e) {
			logger.error("【泰坦金融页面回调】发生异常：", e);
		}

	}

	@Override
	public void PayNotifyCallback(TitanPayCallbackRequest payCallbackRequest) {
		// TODO Auto-generated method stub

	}

}
