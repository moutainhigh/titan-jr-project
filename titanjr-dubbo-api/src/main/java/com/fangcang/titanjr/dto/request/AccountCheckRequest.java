package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class AccountCheckRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//机构名称
	@NotNull
	private String orgName;
	//泰坦码
	@NotNull
	private String titanCode;
	
	private Integer statusId;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getTitanCode() {
		return titanCode;
	}
	public void setTitanCode(String titanCode) {
		this.titanCode = titanCode;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
}
