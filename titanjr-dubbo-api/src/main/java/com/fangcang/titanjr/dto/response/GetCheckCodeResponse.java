package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

/**
 * 获取验证码
 * @author luoqinglong
 * @2016年7月21日
 */
public class GetCheckCodeResponse extends BaseResponseDTO{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 991351374678332179L;
	/**
	 * 验证码
	 */
	private String checkCode;
	
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
}
