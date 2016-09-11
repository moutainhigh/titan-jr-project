package com.fangcang.titanjr.request;

import java.io.Serializable;

public class PayPasswordRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fcuserid;
	
	private String payPassword;
	
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
	
	
}
