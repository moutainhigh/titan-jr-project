package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 删除用户的绑定关系
 * @author luoqinglong
 *
 */
public class DeleteBindUserRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8510614131234909711L;
	private String merchantCode;

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	
}
