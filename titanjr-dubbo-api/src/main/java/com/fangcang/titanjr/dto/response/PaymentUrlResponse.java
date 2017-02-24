package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class PaymentUrlResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
