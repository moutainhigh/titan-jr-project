package com.titanjr.fop.dto;

import java.io.Serializable;

public class MainOrgDTO implements Serializable {
	private static final long serialVersionUID = 7106534836829550967L;
	private String orgCode;
	private String orgName;
	private String productId;
	//账户类型：1-企业，２-个人
	private Integer userType;
	//证件类型：0身份证;1护照;2军官证;3士兵证;4回乡证;5户口本;6外国护照;7其它
	private Integer certificatetype;
	private String certificateNumber;
	//营业执照
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(Integer certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	
}
