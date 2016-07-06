package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class BankCardRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	/**
	 * 对公还是对私用户  账户属性（1：对公，2：对私）
	 */
	private String accountproperty;
	
	/**
	 * 是否有效 无效:0   有效:1
	 */
	private Integer status;
	
	//账户目的1：结算卡，2：其他卡, 3：提现卡, 4：结算提现一体卡) 目前只绑定提现卡
	private String accountpurpose;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountproperty() {
		return accountproperty;
	}

	public void setAccountproperty(String accountproperty) {
		this.accountproperty = accountproperty;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAccountpurpose() {
		return accountpurpose;
	}

	public void setAccountpurpose(String accountpurpose) {
		this.accountpurpose = accountpurpose;
	}
	
}
