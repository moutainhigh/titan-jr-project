package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class BindBankCardRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bankCardName;
	
	private String bankCardCode;
	
	private String userName;
	
	//银行编码
	private String bankCode;
	
	//修改还是绑定 0:绑定 1：修改
	private String modifyOrBind;

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getBankCardCode() {
		return bankCardCode;
	}

	public void setBankCardCode(String bankCardCode) {
		this.bankCardCode = bankCardCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getModifyOrBind() {
		return modifyOrBind;
	}

	public void setModifyOrBind(String modifyOrBind) {
		this.modifyOrBind = modifyOrBind;
	}

}
