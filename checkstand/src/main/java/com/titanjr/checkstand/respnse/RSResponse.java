package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.RSErrorCodeEnum;

/**
 * 融数格式
 * @author Jerry
 * @date 2017年12月6日 下午8:26:12
 */
public class RSResponse implements Serializable {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 6412286510362365207L;

	private String errCode;
	private String errMsg;
	public void putErrorResult(RSErrorCodeEnum rsErrorCodeEnum){
		this.errCode = rsErrorCodeEnum.getErrorCode();
		this.errMsg = rsErrorCodeEnum.getErrorMsg();
	}
	
	@NotBlank
	private String merchantNo;
	@NotBlank
	private String orderNo;

	
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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
