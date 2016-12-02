package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanUser entity. @author MyEclipse Persistence Tools
 */

public class TitanUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 227879673055025073L;
	// Fields

	private Integer tfsuserid;
	private String userid;
	private String orgcode;
	private String username;
	private String userloginname;
	private String password;
	private String department;
	private String mobilephone;
	private String email;
	private String merchantcode;
	private String paypassword;
	private String paySalt;
	private Integer status;
	private Integer isadmin;
	private Integer isoperator;
	private String creator;
	private Date createtime;
	private String modifier;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public TitanUser() {
	}

	/** minimal constructor */
	public TitanUser(String userid) {
		this.userid = userid;
	}

	 
	// Property accessors
	public String getPaySalt() {
		return paySalt;
	}

	public Integer getTfsuserid() {
		return tfsuserid;
	}

	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}

	public void setPaySalt(String paySalt) {
		this.paySalt = paySalt;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}

	public Integer getIsoperator() {
		return isoperator;
	}

	public void setIsoperator(Integer isoperator) {
		this.isoperator = isoperator;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserloginname() {
		return this.userloginname;
	}

	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMerchantcode() {
		return this.merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
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

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}