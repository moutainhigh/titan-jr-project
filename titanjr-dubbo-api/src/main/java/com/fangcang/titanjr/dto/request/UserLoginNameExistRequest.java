package com.fangcang.titanjr.dto.request;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/***
 * 
 * @author luoqinglong
 * @2016年5月30日
 */
public class UserLoginNameExistRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -350007987718005096L;
	/**
	 * 登录用户名
	 */
	@NotBlank
	private String userLoginName;
	/**
	 * 是否为运营员:0-不是，1-是
	 */
	private int isOperator;
	 
	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public int getIsOperator() {
		return isOperator;
	}

	public void setIsOperator(int isOperator) {
		this.isOperator = isOperator;
	}
	
}
