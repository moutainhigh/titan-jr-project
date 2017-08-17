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
	/************** 虚拟证件 *************************/
	private String orgId;
	private String orgcode;
	private Integer tfsuserid;
	private String userloginname;
	private String merchantcode;
	private String orgname;
	private String buslince;	
	
	private Integer certificateType;
	private String certificateNumber;	
	private String checkuser;	
	private String resultkey ;
	
	/************* 真实证件 ***************************/
	private String orgSubId;
	private String subOrgCode;
	private String subOrgname;
	private Integer subCertificateType;
	private String subCertificateNumber;
	private String subBuslince;
	private String subConnect;	
	private String subMobiletel;
	
	
	public String getOrgSubId() {
		return orgSubId;
	}
	public void setOrgSubId(String orgSubId) {
		this.orgSubId = orgSubId;
	}
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
	
	public String getSubConnect() {
		return subConnect;
	}
	public void setSubConnect(String subConnect) {
		this.subConnect = subConnect;
	}
	public String getSubMobiletel() {
		return subMobiletel;
	}
	public void setSubMobiletel(String subMobiletel) {
		this.subMobiletel = subMobiletel;
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
	public String getSubOrgCode() {
		return subOrgCode;
	}
	public void setSubOrgCode(String subOrgCode) {
		this.subOrgCode = subOrgCode;
	}
	public String getSubOrgname() {
		return subOrgname;
	}
	public void setSubOrgname(String subOrgname) {
		this.subOrgname = subOrgname;
	}
	public Integer getSubCertificateType() {
		return subCertificateType;
	}
	public void setSubCertificateType(Integer subCertificateType) {
		this.subCertificateType = subCertificateType;
	}
	public String getSubCertificateNumber() {
		return subCertificateNumber;
	}
	public void setSubCertificateNumber(String subCertificateNumber) {
		this.subCertificateNumber = subCertificateNumber;
	}
	public String getSubBuslince() {
		return subBuslince;
	}
	public void setSubBuslince(String subBuslince) {
		this.subBuslince = subBuslince;
	}
	
}
