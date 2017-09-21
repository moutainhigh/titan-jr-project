package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanBankcard entity. @author MyEclipse Persistence Tools
 */

public class TitanBankcard implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -390281380527827703L;
	private Integer bankcardid;
	private String constid;
	private String userid;
	private Integer usertype;
	private String productid;
	private String accountid;
	private String role;
	private String accountnumber;
	private String accounttypeid;
	private String bankbranchname;
	private String bankheadname;
	private String currency;
	private Date openaccountdate;
	private String reqsn;
	private Date submittime;
	private String openaccountdescription;
	private String accountpurpose;
	private String bindid;
	private String accountproperty;
	private String relatid;
	private String certificatetype;
	private String certificatenumnumber;
	private String accountname;
	private String relatedcard;
	private String tel;
	private String merrem;
	private String remark;
	private String referuserid;
	private String bankcode;
	private String bankbranch;
	private String bankprovince;
	private String bankcity;
	private Integer status;
	private String creator;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TitanBankcard() {
	}

	/** full constructor */
	public TitanBankcard(String constid, String userid, Integer usertype,
			String productid, String role, String accountnumber,
			String accounttypeid, String bankbranchname, String bankheadname,
			String currency, Date openaccountdate, String reqsn,
			Date submittime, String openaccountdescription,
			String accountpurpose, String bindid, String accountproperty,
			String relatid, String certificatetype,
			String certificatenumnumber, String accountname,
			String relatedcard, String tel, String merrem, String remark,
			String referuserid, String bankcode, String bankbranch,
			String bankprovince, String bankcity, Integer status,
			String creator, Date createtime) {
		this.constid = constid;
		this.userid = userid;
		this.usertype = usertype;
		this.productid = productid;
		this.role = role;
		this.accountnumber = accountnumber;
		this.accounttypeid = accounttypeid;
		this.bankbranchname = bankbranchname;
		this.bankheadname = bankheadname;
		this.currency = currency;
		this.openaccountdate = openaccountdate;
		this.reqsn = reqsn;
		this.submittime = submittime;
		this.openaccountdescription = openaccountdescription;
		this.accountpurpose = accountpurpose;
		this.bindid = bindid;
		this.accountproperty = accountproperty;
		this.relatid = relatid;
		this.certificatetype = certificatetype;
		this.certificatenumnumber = certificatenumnumber;
		this.accountname = accountname;
		this.relatedcard = relatedcard;
		this.tel = tel;
		this.merrem = merrem;
		this.remark = remark;
		this.referuserid = referuserid;
		this.bankcode = bankcode;
		this.bankbranch = bankbranch;
		this.bankprovince = bankprovince;
		this.bankcity = bankcity;
		this.status = status;
		this.creator = creator;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getBankcardid() {
		return this.bankcardid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public void setBankcardid(Integer bankcardid) {
		this.bankcardid = bankcardid;
	}

	public String getConstid() {
		return this.constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccounttypeid() {
		return this.accounttypeid;
	}

	public void setAccounttypeid(String accounttypeid) {
		this.accounttypeid = accounttypeid;
	}

	public String getBankbranchname() {
		return this.bankbranchname;
	}

	public void setBankbranchname(String bankbranchname) {
		this.bankbranchname = bankbranchname;
	}

	public String getBankheadname() {
		return this.bankheadname;
	}

	public void setBankheadname(String bankheadname) {
		this.bankheadname = bankheadname;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getOpenaccountdate() {
		return this.openaccountdate;
	}

	public void setOpenaccountdate(Date openaccountdate) {
		this.openaccountdate = openaccountdate;
	}

	public String getReqsn() {
		return this.reqsn;
	}

	public void setReqsn(String reqsn) {
		this.reqsn = reqsn;
	}

	public Date getSubmittime() {
		return this.submittime;
	}

	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}

	public String getOpenaccountdescription() {
		return this.openaccountdescription;
	}

	public void setOpenaccountdescription(String openaccountdescription) {
		this.openaccountdescription = openaccountdescription;
	}

	public String getAccountpurpose() {
		return this.accountpurpose;
	}

	public void setAccountpurpose(String accountpurpose) {
		this.accountpurpose = accountpurpose;
	}

	public String getBindid() {
		return this.bindid;
	}

	public void setBindid(String bindid) {
		this.bindid = bindid;
	}

	public String getAccountproperty() {
		return this.accountproperty;
	}

	public void setAccountproperty(String accountproperty) {
		this.accountproperty = accountproperty;
	}

	public String getRelatid() {
		return this.relatid;
	}

	public void setRelatid(String relatid) {
		this.relatid = relatid;
	}

	public String getCertificatetype() {
		return this.certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getCertificatenumnumber() {
		return this.certificatenumnumber;
	}

	public void setCertificatenumnumber(String certificatenumnumber) {
		this.certificatenumnumber = certificatenumnumber;
	}

	public String getAccountname() {
		return this.accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getRelatedcard() {
		return this.relatedcard;
	}

	public void setRelatedcard(String relatedcard) {
		this.relatedcard = relatedcard;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMerrem() {
		return this.merrem;
	}

	public void setMerrem(String merrem) {
		this.merrem = merrem;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReferuserid() {
		return this.referuserid;
	}

	public void setReferuserid(String referuserid) {
		this.referuserid = referuserid;
	}

	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getBankbranch() {
		return this.bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}

	public String getBankprovince() {
		return this.bankprovince;
	}

	public void setBankprovince(String bankprovince) {
		this.bankprovince = bankprovince;
	}

	public String getBankcity() {
		return this.bankcity;
	}

	public void setBankcity(String bankcity) {
		this.bankcity = bankcity;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}