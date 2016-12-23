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
	private String password;
	
	public String getLoginUsername() {
		return loginUsername;
	}
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
