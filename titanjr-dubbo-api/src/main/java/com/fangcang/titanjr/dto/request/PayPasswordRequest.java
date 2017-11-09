package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class PayPasswordRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tfsuserid;
	
	private String fcuserid;
	
	//付款密码
	private String payPassword;
	
	//原密码
	private String oldPassword;
	
	private String isForget;
	
	//第三方自己的商家编码
	private String merchantCode;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getTfsuserid() {
		return tfsuserid;
	}

	public void setTfsuserid(String tfsuserid) {
		this.tfsuserid = tfsuserid;
	}

	public String getFcuserid() {
		return fcuserid;
	}

	public void setFcuserid(String fcuserid) {
		this.fcuserid = fcuserid;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getIsForget() {
		return isForget;
	}

	public void setIsForget(String isForget) {
		this.isForget = isForget;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
}
