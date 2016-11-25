package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanOrg entity. @author MyEclipse Persistence Tools
 */

public class TitanOrg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 234016678702765480L;
	private Integer orgid;
	private String orgcode;
	private String orgname;
	private String constid;
	private String userid;
	private String productid;
	private String role;
	private String username;
	/**
	 * 机构类型：企业，个人
	 */
	private Integer usertype;
	private Integer statusid;
	private String address;
	private String post;
	private String remark;
	private Integer orgtype;
	/***
	 * 证件类型：身份证，士官证
	 */
	private Integer certificatetype;
	/**
	 * 证件号，如：身份证，士官证
	 */
	private String certificatenumber;
	/**
	 * 联系手机
	 */
	private String mobiletel;
	private String bindcompanyname;
	private String orgshortname;
	private String mcc;
	/***
	 * 联系人
	 */
	private String connect;
	/**
	 * 营业执照号
	 */
	private String buslince;
	private String taxregcardf;
	private String taxregcards;
	private String organcertificate;
	private String busplacectf;
	private String loancard;
	private String acuntopnlince;
	private String corporatename;
	private String personengname;
	private String persontype;
	private String personsex;
	private Date birthday;
	private String fixTel;
	/**
	 * 泰坦码
	 */
	private String titancode;
	private String email;
	private Date createTime;
	private Date updateTime;
	/**
	 * 授信金额估算时间
	 */
	private Date lastUpdateDate;
	
	//最大可申请授信额度
	private Long maxLoanAmount;

	// Constructors

	/** default constructor */
	public TitanOrg() {
	}

	// Property accessors

	public Integer getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrgtype() {
		return this.orgtype;
	}

	public void setOrgtype(Integer orgtype) {
		this.orgtype = orgtype;
	}

	public Integer getCertificatetype() {
		return this.certificatetype;
	}

	public void setCertificatetype(Integer certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getCertificatenumber() {
		return this.certificatenumber;
	}

	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}

	public String getMobiletel() {
		return this.mobiletel;
	}

	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}

	public String getBindcompanyname() {
		return this.bindcompanyname;
	}

	public void setBindcompanyname(String bindcompanyname) {
		this.bindcompanyname = bindcompanyname;
	}

	public String getOrgshortname() {
		return this.orgshortname;
	}

	public void setOrgshortname(String orgshortname) {
		this.orgshortname = orgshortname;
	}

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getConnect() {
		return this.connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	public String getBuslince() {
		return this.buslince;
	}

	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}

	public String getTaxregcardf() {
		return this.taxregcardf;
	}

	public void setTaxregcardf(String taxregcardf) {
		this.taxregcardf = taxregcardf;
	}

	public String getTaxregcards() {
		return this.taxregcards;
	}

	public void setTaxregcards(String taxregcards) {
		this.taxregcards = taxregcards;
	}

	public String getOrgancertificate() {
		return this.organcertificate;
	}

	public void setOrgancertificate(String organcertificate) {
		this.organcertificate = organcertificate;
	}

	public String getBusplacectf() {
		return this.busplacectf;
	}

	public void setBusplacectf(String busplacectf) {
		this.busplacectf = busplacectf;
	}

	public String getLoancard() {
		return this.loancard;
	}

	public void setLoancard(String loancard) {
		this.loancard = loancard;
	}

	public String getAcuntopnlince() {
		return this.acuntopnlince;
	}

	public void setAcuntopnlince(String acuntopnlince) {
		this.acuntopnlince = acuntopnlince;
	}

	public String getCorporatename() {
		return this.corporatename;
	}

	public void setCorporatename(String corporatename) {
		this.corporatename = corporatename;
	}

	public String getPersonengname() {
		return this.personengname;
	}

	public void setPersonengname(String personengname) {
		this.personengname = personengname;
	}

	public String getPersontype() {
		return this.persontype;
	}

	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}

	public String getPersonsex() {
		return this.personsex;
	}

	public void setPersonsex(String personsex) {
		this.personsex = personsex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFixTel() {
		return this.fixTel;
	}

	public void setFixTel(String fixTel) {
		this.fixTel = fixTel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitancode() {
		return titancode;
	}

	public void setTitancode(String titancode) {
		this.titancode = titancode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(Long maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
}