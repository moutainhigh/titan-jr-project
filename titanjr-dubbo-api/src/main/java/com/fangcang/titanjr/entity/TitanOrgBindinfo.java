package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanOrgBindinfo entity. @author MyEclipse Persistence Tools
 */

public class TitanOrgBindinfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6129071874116954087L;
	private String userid;
	private String merchantcode;
	private String orgcode;
	private String merchantname;
	private int cooptype;
	private Integer bindstatus;
	
	private String creator;
	private Date createtime;
	private String modifier;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public TitanOrgBindinfo() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getMerchantname() {
		return this.merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	public Integer getBindstatus() {
		return this.bindstatus;
	}

	public void setBindstatus(Integer bindstatus) {
		this.bindstatus = bindstatus;
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

	public int getCooptype() {
		return cooptype;
	}

	public void setCooptype(int cooptype) {
		this.cooptype = cooptype;
	}

}