package com.fangcang.titanjr.dto.bean;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class ForgetPayPassword extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//用户名
	private String userName;
	
	//验证码
	private String code;
	
	//支付密码
	private String payPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

}
