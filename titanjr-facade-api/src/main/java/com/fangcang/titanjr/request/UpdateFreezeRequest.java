package com.fangcang.titanjr.request;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateFreezeRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2713305741738131122L;
	
	/**
	 * 支付订单号
	 */
	@NotBlank
	private String payOrderNo;
	/**
	 * 操作类型 1、直接解冻  2、修改解冻日期
	 */
	@NotBlank
	private String operationType;
	/**
	 * 解冻日期
	 */
	private String uNFreezeDate;
	
	public String getPayOrderNo() {
		return payOrderNo;
	}
	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getuNFreezeDate() {
		return uNFreezeDate;
	}
	public void setuNFreezeDate(String uNFreezeDate) {
		this.uNFreezeDate = uNFreezeDate;
	}

}
