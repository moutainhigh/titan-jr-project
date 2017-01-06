package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

/**
 * 密码登录
 * @author luoqinglong
 * @date   2016年12月23日
 */
public class PassLoginResponse extends BaseResponseDTO {
	
	/**
	 * 金融用户id
	 */
	private Integer tfsuserId;
	/**
	 * 金融登录用户名
	 */
	private String userLoginName;
	
	public Integer getTfsuserId() {
		return tfsuserId;
	}
	public void setTfsuserId(Integer tfsuserId) {
		this.tfsuserId = tfsuserId;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	
}
