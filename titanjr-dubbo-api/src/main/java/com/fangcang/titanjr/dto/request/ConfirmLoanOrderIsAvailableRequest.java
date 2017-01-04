package com.fangcang.titanjr.dto.request;

public class ConfirmLoanOrderIsAvailableRequest implements java.io.Serializable{

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderNo;
	
	private String orgCode;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
