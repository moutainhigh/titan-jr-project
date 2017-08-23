package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

/**
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganRegisterResponse extends BaseResponseDTO {
	private String orgCode;
	private Integer tfsUserId;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Integer getTfsUserId() {
		return tfsUserId;
	}
	public void setTfsUserId(Integer tfsUserId) {
		this.tfsUserId = tfsUserId;
	}
	
	
}
