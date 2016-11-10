package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class GetOrgLoanStatInfoRequest extends BaseRequestDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
