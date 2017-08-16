package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/***
 * 
 * @author luoqinglong
 * @date 2017年8月16日
 */
public class OrgSubDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7308799564598256150L;
	
	private Integer  orgSubId;
	private String  orgCode;
	private String  orgName;
	private Integer  userType;
	private Integer certificateType;
    private String certificateNumber;
    private String mobileTel;
	private String  connect;
	private String  buslince;
	
	public Integer getOrgSubId() {
		return orgSubId;
	}
	public void setOrgSubId(Integer orgSubId) {
		this.orgSubId = orgSubId;
	}
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
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	
}
