package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class FinancialOrderRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//商家编号
	private String merchantcode;
	
	//单号
	private String orderNo;

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
