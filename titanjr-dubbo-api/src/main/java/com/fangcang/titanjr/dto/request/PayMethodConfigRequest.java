package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class PayMethodConfigRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
