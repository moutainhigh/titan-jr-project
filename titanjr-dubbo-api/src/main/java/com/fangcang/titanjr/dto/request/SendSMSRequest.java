package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class SendSMSRequest extends BaseRequestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//手机号
	private String mobilePhone;
	
	//短信内容
	private String content;
	
	//选用服务号
	private String providerkey;
	
	//商户号
	private String merchantCode;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProviderkey() {
		return providerkey;
	}

	public void setProviderkey(String providerkey) {
		this.providerkey = providerkey;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
}
