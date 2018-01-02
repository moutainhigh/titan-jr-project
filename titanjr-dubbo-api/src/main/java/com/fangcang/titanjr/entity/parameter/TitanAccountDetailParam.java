package com.fangcang.titanjr.entity.parameter;

import java.io.Serializable;

public class TitanAccountDetailParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6918260201444166742L;
	private Long transOrderId;
	private Integer tradeType;
	private String orgCode;
	private String productId;
	
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
