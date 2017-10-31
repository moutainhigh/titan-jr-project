package com.fangcang.titanjr.entity.parameter;

import com.fangcang.titanjr.entity.TitanOrgMapInfo;

public class TitanOrgMapInfoParam extends TitanOrgMapInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8223359599080661824L;
	
	private String newOrgCode;
	private String newOrgSubCode;
	
	public String getNewOrgCode() {
		return newOrgCode;
	}
	public void setNewOrgCode(String newOrgCode) {
		this.newOrgCode = newOrgCode;
	}
	public String getNewOrgSubCode() {
		return newOrgSubCode;
	}
	public void setNewOrgSubCode(String newOrgSubCode) {
		this.newOrgSubCode = newOrgSubCode;
	}
	
}
