package com.fangcang.titanjr.service;

import java.util.List;

import org.apache.http.NameValuePair;

import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.dto.request.NotifyClientRequest;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;

public interface TitanFinancialUtilService {

	/**
	 * 获取支付的url，并加密
	 * @param paymentUrlRequest
	 * @return
	 * @author fangdaikang
	 */
	public PaymentUrlResponse getPaymentUrl(PaymentUrlRequest paymentUrlRequest);
	
	
	/**
	 * 组装相关的回调参数
	 * @param payMethodConfigRequest
	 * @return
	 * @author fangdaikang
	 */
	public PayMethodConfigDTO getPayMethodConfigDTO(PayMethodConfigRequest payMethodConfigRequest);
	
	
	/**
	 * 回调客户端的util
	 * @param request
	 * @author fangdaikang
	 */
	public void notifyClient(NotifyClientRequest request);
	
	/**
	 * 获取系统的相关参数
	 * @return
	 */
	public SysConfig querySysConfig();
	
	
}
