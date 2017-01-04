package com.fangcang.titanjr.web.pojo;

import java.io.Serializable;

/**
 * 注册时登录用户信息
 * @author luoqinglong
 * @2016年6月2日
 */
public class RegUserLoginInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4393507448450353857L;
	/**
	 * 登录用户名
	 */
	private String userLoginName;
	private String password;
	/**
	 * 确认密码
	 */
	private String passwordConfirm;
	/**
	 * 登录信息的验证码
	 */
	private String regCode;
	
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
}
