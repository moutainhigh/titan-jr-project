package com.fangcang.titanjr.entity.parameter;

import java.io.Serializable;

public class TitanAccountDetailParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6918260201444166742L;
	private Long transOrderId;
	private String accountCode;
	private Integer tradeType;
	private String orgCode;
	private String productId;
	/***
	 * 状态:1-有效，２－无效
	 */
	private Integer status;
	
	
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getTransOrderId() {
		return transOrderId;
	}
	public void setTransOrderId(Long transOrderId) {
		this.transOrderId = transOrderId;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
