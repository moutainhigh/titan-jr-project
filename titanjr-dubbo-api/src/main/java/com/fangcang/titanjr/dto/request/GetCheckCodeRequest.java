package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 获取(生成验证码)
 * @author luoqinglong
 * @2016年7月21日
 */
public class GetCheckCodeRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2642749848962407947L;
	@NotBlank
	private String receiveAddress;
	/**
	 * 验证码类型：1-注册，2-修改付款密码，3-修改登录密码
	 */
	@NotNull
	private Integer msgType;
	
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	
}
