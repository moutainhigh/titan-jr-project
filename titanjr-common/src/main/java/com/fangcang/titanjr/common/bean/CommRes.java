package com.fangcang.titanjr.common.bean;

import java.io.Serializable;


public class CommRes implements Serializable{
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -2088188891028427416L;

	private String returnCode;
	
	private String returnMessage;
	
	private boolean isSuccess;
	
	public void putSuccess() {
		setSuccess(true);
	}
	public void putError(String code,String msg) {
		setSuccess(false);
		setReturnCode(code);
		setReturnMessage(msg);
	}
	public void putError(String msg) {
		setSuccess(false);
		setReturnMessage(msg);
	}

	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
