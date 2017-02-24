package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanCashierItemBank entity. @author MyEclipse Persistence Tools
 */

public class TitanCashierItemBank implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2773602879446283130L;
	// Fields
	private Integer itemid;
	private String bankmark;
	private String bankname;
	private String bankimage;
	private String creator;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TitanCashierItemBank() {
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

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getBankmark() {
		return bankmark;
	}

	public void setBankmark(String bankmark) {
		this.bankmark = bankmark;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankimage() {
		return bankimage;
	}

	public void setBankimage(String bankimage) {
		this.bankimage = bankimage;
	}
}