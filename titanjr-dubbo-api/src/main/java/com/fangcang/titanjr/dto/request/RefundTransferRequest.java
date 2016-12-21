package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class RefundTransferRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String payOrderNo;
	
	private String userRelateId;
	
	private String interMerchantCode;
	
	private String interProductId;
	
	private String userId;
	
	private String productId;
	
	private String merchantCode;
	
	private String requestNo;

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getUserRelateId() {
		return userRelateId;
	}

	public void setUserRelateId(String userRelateId) {
		this.userRelateId = userRelateId;
	}

	public String getInterMerchantCode() {
		return interMerchantCode;
	}

	public void setInterMerchantCode(String interMerchantCode) {
		this.interMerchantCode = interMerchantCode;
	}

	public String getInterProductId() {
		return interProductId;
	}

	public void setInterProductId(String interProductId) {
		this.interProductId = interProductId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

}
