package com.fangcang.titanjr.web.pojo;

import java.io.Serializable;

/**
 * 登录参数
 * @author luoqinglong
 * @date   2016年12月23日
 */
public class LoginPojo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5419102457657447551L;
	/***
	 * 登录用户名
	 */
	private String loginUserName;
	/***
	 * 登录密码或者动态验证码
	 */
	private String password;
	
	
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
