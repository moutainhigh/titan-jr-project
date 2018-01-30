package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class BalanceInfoRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7234831450034808385L;
	private String userId;
	private String productId;
	
	private String finaccountId;

	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFinaccountId() {
		return finaccountId;
	}

	public void setFinaccountId(String finaccountId) {
		this.finaccountId = finaccountId;
	}
	
	
}
