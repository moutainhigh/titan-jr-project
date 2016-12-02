package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.RSPayOrderRequest;
import com.fangcang.titanjr.rs.response.PayResultResponse;
import com.fangcang.titanjr.rs.response.RSPayOrderResponse;

public interface RSPayOrderManager {
	
	/**
	 * 获取支付的页面
	 * @param rsPayOrderRequest
	 * @return
	 * @author fangdaikang
	 */
	public RSPayOrderResponse getPayPage(RSPayOrderRequest rsPayOrderRequest) ;
	
	/**
	 * 查询支付结果
	 * @param rsPayOrderRequest
	 * @return
	 * @author fangdaikang
	 */
	public PayResultResponse queryPayResult(RSPayOrderRequest rsPayOrderRequest);
	
	public String notifyResult(RSPayOrderRequest rsPayOrderRequest);
}
