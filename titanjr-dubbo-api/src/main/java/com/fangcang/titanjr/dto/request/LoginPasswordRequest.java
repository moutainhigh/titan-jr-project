package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 登录密码
 * @author luoqinglong
 * @2016年7月8日
 */
public class LoginPasswordRequest extends BaseRequestDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1214260002279149602L;

	/**
	 * 金融id
	 */
	@NotNull
	private Integer tfsuserid;
	
	//新登录密码
	@NotBlank
	private String newLoginPassword;

	public Integer getTfsuserid() {
		return tfsuserid;
	}

	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}

	public String getNewLoginPassword() {
		return newLoginPassword;
	}

	public void setNewLoginPassword(String newLoginPassword) {
		this.newLoginPassword = newLoginPassword;
	}
	
}
