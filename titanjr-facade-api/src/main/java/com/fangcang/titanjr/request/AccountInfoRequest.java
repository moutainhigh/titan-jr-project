package com.fangcang.titanjr.request;

import com.fangcang.titanjr.dto.PaySourceEnum;

import java.io.Serializable;

public class AccountInfoRequest  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String merchantCode;

	//支付来源，可选，如果传入，需此来源的收银台是开放状态
	private PaySourceEnum paySourceEnum;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public PaySourceEnum getPaySourceEnum() {
		return paySourceEnum;
	}

	public void setPaySourceEnum(PaySourceEnum paySourceEnum) {
		this.paySourceEnum = paySourceEnum;
	}
}
