package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanCashierDesk entity. @author MyEclipse Persistence Tools
 */

public class TitanCashierDesk implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2336339520502500464L;
	private Integer deskid;
	private String constid;
	private String userid;
	private String deskname;
	private Integer usedfor;
	private Integer paytype;
	private String creator;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TitanCashierDesk() {
	}

	/** full constructor */
	public TitanCashierDesk(String constid, String userid, String deskname,
			Integer usedfor, Integer paytype, String creator, Date createtime) {
		this.constid = constid;
		this.userid = userid;
		this.deskname = deskname;
		this.usedfor = usedfor;
		this.paytype = paytype;
		this.creator = creator;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getDeskid() {
		return this.deskid;
	}

	public void setDeskid(Integer deskid) {
		this.deskid = deskid;
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

	public String getDeskname() {
		return this.deskname;
	}

	public void setDeskname(String deskname) {
		this.deskname = deskname;
	}

	public Integer getUsedfor() {
		return this.usedfor;
	}

	public void setUsedfor(Integer usedfor) {
		this.usedfor = usedfor;
	}

	public Integer getPaytype() {
		return this.paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
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