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
	/**
	 * 机构类型：企业:1，个人:2
	 */
	private Integer usertype;
	private Integer statusid;
	private String remark;
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
	/***
	 * 联系人
	 */
	private String connect;
	/**
	 * 营业执照号
	 */
	private String buslince;
	/**
	 * 泰坦码
	 */
	private String titancode;
	private String email;
	private Date createTime;
	private Date updateTime;
	/**
	 * 合作方系统类型: 2-SAAS,4-TTM,5-钱包官网'
	 */
	private Integer regChannel;
	
	private Integer regSource;
	/**
	 * 授信金额估算时间
	 */
	private Date lastUpdateDate;
	
	/***
	 * 最大可申请授信额度
	 */
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

	 

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getRegSource() {
		return regSource;
	}

	public void setRegSource(Integer regSource) {
		this.regSource = regSource;
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


	public Integer getRegChannel() {
		return regChannel;
	}

	public void setRegChannel(Integer regChannel) {
		this.regChannel = regChannel;
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