package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class LoanCreditSaveResponse  extends BaseResponseDTO{

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	//授信单号
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	
}
