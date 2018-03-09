package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class RecordTransferRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 35599605006900247L;
	//交易单号
	private long transOrderId;
	
	private String userOrderId;
	//发起方
	private String 	userId;
	
	private String productId;
	//接收方
	private String relateUserId;
	
	private String relateProductId;
	// 单位：分
	private long amount;
	
	public String getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRelateUserId() {
		return relateUserId;
	}
	public void setRelateUserId(String relateUserId) {
		this.relateUserId = relateUserId;
	}
	public String getRelateProductId() {
		return relateProductId;
	}
	public void setRelateProductId(String relateProductId) {
		this.relateProductId = relateProductId;
	}
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
	
}
