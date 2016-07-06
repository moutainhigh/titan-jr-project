package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanCashierDeskItem entity. @author MyEclipse Persistence Tools
 */

public class TitanCashierDeskItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1517747726056072534L;
	private Integer itemid;
	private Integer deskid;
	private String itemname;
	private Integer itemtype;
	private String creator;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TitanCashierDeskItem() {
	}

	/** full constructor */
	public TitanCashierDeskItem(Integer deskid, String itemname,
			Integer itemtype, String creator, Date createtime) {
		this.deskid = deskid;
		this.itemname = itemname;
		this.itemtype = itemtype;
		this.creator = creator;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getItemid() {
		return this.itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getDeskid() {
		return this.deskid;
	}

	public void setDeskid(Integer deskid) {
		this.deskid = deskid;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Integer getItemtype() {
		return this.itemtype;
	}

	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
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