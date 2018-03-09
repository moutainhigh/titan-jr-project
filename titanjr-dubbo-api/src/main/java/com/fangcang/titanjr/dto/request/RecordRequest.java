package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class RecordRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128165321123698829L;
	//交易单号
	private long transOrderId;
	private String userOrderId;
	//充值用户
	private String 	userId;
	//充值的产品
	private String productId;
	// 操作金额，单位：分
	private long amount;
	// 操作手续费 单位：分
	private long fee;
	
	
	public String getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
