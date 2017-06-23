package com.fangcang.titanjr.dto;

import java.io.Serializable;

public class FreezeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7021053459682474382L;
	
	private String userId;
	
	private Integer freezereqId;
	
	private String merchantCode;
	
	private String authCode;
	
	private String requestNo;
	
	private String amount;
	
	private String orderNo;
	
	private String requestTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getFreezereqId() {
		return freezereqId;
	}

	public void setFreezereqId(Integer freezereqId) {
		this.freezereqId = freezereqId;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

}
