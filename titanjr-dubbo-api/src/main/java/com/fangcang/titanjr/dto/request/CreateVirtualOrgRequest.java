package com.fangcang.titanjr.dto.request;

public class CreateVirtualOrgRequest implements java.io.Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	//创建人组织编号
	private String orgCode;
	
	//虚拟组织名称
	private String virtualOrgName;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getVirtualOrgName() {
		return virtualOrgName;
	}

	public void setVirtualOrgName(String virtualOrgName) {
		this.virtualOrgName = virtualOrgName;
	}
}
