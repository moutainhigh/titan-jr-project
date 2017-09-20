package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class OrgBaseInfoRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2886065053323418366L;
	
	private String orgCode;
	private String orgName;
	//机构类型：1-企业，2-企业
    private int userType;
	private String certificatenumber;
    //证件类型,0身份证;1护照;2军官证;3士兵证;4回乡证;
    // 5户口本;6外国护照;7其它（当操作类型是2：修改时，此项目为可选）
    private String certificatetype;
    //注册个人机构时必须，营业执照号
    private String buslince;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getCertificatenumber() {
		return certificatenumber;
	}
	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}
	public String getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
    
    
    
}
