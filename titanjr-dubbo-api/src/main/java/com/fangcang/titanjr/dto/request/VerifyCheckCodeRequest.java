package com.fangcang.titanjr.dto.request;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 校验验证码
 * @author luoqinglong
 * @2016年7月21日
 */
public class VerifyCheckCodeRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6879131938237219866L;
	
	/**
	 * 验证码接收地址
	 */
	@NotBlank
	private String receiveAddress;
	/**
	 * 输入的验证码
	 */
	@NotBlank
	private String inputCode;

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
}
