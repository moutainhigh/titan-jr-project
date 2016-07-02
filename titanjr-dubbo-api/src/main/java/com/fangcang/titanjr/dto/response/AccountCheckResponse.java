package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class AccountCheckResponse extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean checkResult;
	
	private String userid;

	public boolean isCheckResult() {
		return checkResult;
	}

	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
