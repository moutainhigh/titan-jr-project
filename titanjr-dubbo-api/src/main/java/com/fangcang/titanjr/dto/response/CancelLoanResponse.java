package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class CancelLoanResponse extends BaseResponseDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//贷款单号
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
