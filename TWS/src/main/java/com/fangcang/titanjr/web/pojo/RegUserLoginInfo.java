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
	/***
	 * 密码
	 */
	private String password;
	/**
	 * 确认密码
	 */
	private String passwordConfirm;
	/**
	 * 登录信息的验证码
	 */
	private String regCode;
	/***
	 * 合作平台
	 */
	private String channel;
	/**
	 * RSA加密信息
	 */
	private String info;
	/**
	 * 加密方式
	 */
	private String encrypt_type;
	/**
	 * md5签名
	 */
	private String sign;
	
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
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getEncrypt_type() {
		return encrypt_type;
	}
	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
