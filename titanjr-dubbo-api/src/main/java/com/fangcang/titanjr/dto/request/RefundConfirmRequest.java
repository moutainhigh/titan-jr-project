package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class RefundConfirmRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//订单号
	private String orderNo;
	
	//商家
	private String userId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
