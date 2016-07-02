package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class LocalAddTransOrderResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer transid;
	
	//本地生成的单号，用于冻结资金
	private String orderNo;

	public Integer getTransid() {
		return transid;
	}

	public void setTransid(Integer transid) {
		this.transid = transid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
