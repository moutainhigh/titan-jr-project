package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
/***
 * 用户审核
 * @author luoqinglong
 * @2016年5月19日
 */
public class OrgCheckDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6346796661307189288L;
	private String orgId;
	private String orgcode;
	private Integer tfsuserid;
	private String userloginname;
	private String merchantcode;
	private String orgname;
	private String buslince;	
	private String connect;	
	private String mobiletel;
	private Integer certificateType;
	private String certificateNumber;	
	private String checkuser;	
	private String resultkey ;
	
	
	public Integer getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public Integer getTfsuserid() {
		return tfsuserid;
	}
	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}
	public String getUserloginname() {
		return userloginname;
	}
	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getMobiletel() {
		return mobiletel;
	}
	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getCheckuser() {
		return checkuser;
	}
	public void setCheckuser(String checkuser) {
		this.checkuser = checkuser;
	}
	public String getResultkey() {
		return resultkey;
	}
	public void setResultkey(String resultkey) {
		this.resultkey = resultkey;
	}
	public String getMerchantcode() {
		return merchantcode;
	}
	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}
	
	
}
