package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class OrgSubRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4932569249194268213L;
	/**
	 * 虚拟机构编码
	 */
	private String orgCode;
	/**
	 * 真实机构编码
	 */
	private String orgSubCode;
	
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgSubCode() {
		return orgSubCode;
	}
	public void setOrgSubCode(String orgSubCode) {
		this.orgSubCode = orgSubCode;
	}
	
	
}
