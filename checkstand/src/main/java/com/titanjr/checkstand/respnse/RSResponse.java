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
	
	@NotBlank
	private String merchantNo;
	@NotBlank
	private String orderNo;

	private String errCode;
	private String errMsg;
	public void putErrorResult(RSErrorCodeEnum rsErrorCodeEnum){
		this.errCode = rsErrorCodeEnum.getErrorCode();
		this.errMsg = rsErrorCodeEnum.getErrorMsg();
	}
	
	/**
	 * 版本   固定值：v1.0   新版本：v1.1（含快捷支付）
	 */
	@NotBlank
	private String version;
	/**
	 * 签名类型   默认1为MD5加签
	 */
	@NotBlank
	private String signType;
	@NotBlank
	private String signMsg;

	
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

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
