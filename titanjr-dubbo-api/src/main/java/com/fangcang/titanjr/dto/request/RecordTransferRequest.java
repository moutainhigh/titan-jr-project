package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class RecordTransferRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 35599605006900247L;
	//交易单号
	private long transOrderId;
	//发起方
	private String 	userId;
	//接收方
	private String userrelateid;
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
	public String getUserrelateid() {
		return userrelateid;
	}
	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
}
