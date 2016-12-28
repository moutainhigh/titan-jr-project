package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 动态验证码登录
 * @author luoqinglong
 * @date   2016年12月23日
 */
public class SmsLoginRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3028481273756786967L;
	private String loginUsername;
	/**
	 * 验证码
	 */
	private String smsCode;
	
	public String getLoginUsername() {
		return loginUsername;
	}
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	
	
}
