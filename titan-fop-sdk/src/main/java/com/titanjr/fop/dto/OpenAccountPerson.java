package com.titanjr.fop.dto;

import java.io.Serializable;

public class OpenAccountPerson implements Serializable {
	private static final long serialVersionUID = 1609367219344291785L;
	
	private String mobiletel;
	private String constid;
	private String personchnname;
	private String updatedtime;
	private String remark;
	private String certificatetype;
	private String persontype;
	private String certificatenumber;
	private String statusid;
	private String createdtime;
	private String userid;
	
	public String getMobiletel() {
		return mobiletel;
	}
	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}
	public String getConstid() {
		return constid;
	}
	public void setConstid(String constid) {
		this.constid = constid;
	}
	public String getPersonchnname() {
		return personchnname;
	}
	public void setPersonchnname(String personchnname) {
		this.personchnname = personchnname;
	}
	public String getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(String updatedtime) {
		this.updatedtime = updatedtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getPersontype() {
		return persontype;
	}
	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}
	public String getCertificatenumber() {
		return certificatenumber;
	}
	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
