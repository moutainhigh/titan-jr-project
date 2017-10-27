package com.fangcang.titanjr.dto.bean;

public class RSOrg {
	private String orgname;
	private String userid;
	/**
	 * 机构类型：企业:1，个人:2
	 */
	private Integer usertype;
	/***
	 * 证件类型：身份证，士官证
	 */
	private Integer certificatetype;
	/**
	 * 证件号，如：身份证，士官证
	 */
	private String certificatenumber; 
	
	/**
	 * 营业执照号
	 */
	private String buslince;


	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(Integer certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getCertificatenumber() {
		return certificatenumber;
	}

	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}

	public String getBuslince() {
		return buslince;
	}

	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	
	
	
}
