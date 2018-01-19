package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class RecordRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128165321123698829L;
	//交易单号
	private long transOrderId;
	//充值用户
	private String 	userId;
	//充值的产品
	private String productId;
	// 单位：分
	private long amount;
	
	
	public long getTransOrderId() {
		return transOrderId;
	}
	public void setTransOrderId(long transOrderId) {
		this.transOrderId = transOrderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
