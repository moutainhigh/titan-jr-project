package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 身份信息
 * @author fangdaikang
 *
 */
public class IdentityInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//身份证号
	private String certificateNumber;
	
	//真实姓名
	private String realName;
	
	//用户名
	private String userName;
	
	//所属商家
	private String merchantCode;
	

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

}
