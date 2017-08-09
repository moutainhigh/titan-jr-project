package com.fangcang.titanjr.dto.response.gateway;

import java.io.Serializable;

public class RSBaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5296789513045124528L;
	
	private String errCode;
	
	private String errMsg;
	
	private boolean isSuccess;
	
	private String remark;
	
	
	public void putSuccess(String remark){
		this.isSuccess = true;
		this.remark = remark;
	}
	
	public void putError(String msg) {
		this.isSuccess = false;
		this.errMsg = msg;
	}
	

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
