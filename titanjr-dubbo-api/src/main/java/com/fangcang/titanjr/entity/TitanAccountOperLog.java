package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanAccountOperLogId entity. @author MyEclipse Persistence Tools
 */

public class TitanAccountOperLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8630300608136522296L;
	private Integer operlogid;
	private String constid;
	private String userid;
	private Integer operatetype;
	private Integer currentstatus;
	private String description;
	private String operator;
	private Date operatetime;

	// Constructors

	/** default constructor */
	public TitanAccountOperLog() {
	}

	/** full constructor */
	public TitanAccountOperLog(Integer operlogid, String constid,
			String userid, Integer operatetype, Integer currentstatus,
			String description, String operator, Date operatetime) {
		this.operlogid = operlogid;
		this.constid = constid;
		this.userid = userid;
		this.operatetype = operatetype;
		this.currentstatus = currentstatus;
		this.description = description;
		this.operator = operator;
		this.operatetime = operatetime;
	}

	// Property accessors

	public Integer getOperlogid() {
		return this.operlogid;
	}

	public void setOperlogid(Integer operlogid) {
		this.operlogid = operlogid;
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

	public Integer getOperatetype() {
		return this.operatetype;
	}

	public void setOperatetype(Integer operatetype) {
		this.operatetype = operatetype;
	}

	public Integer getCurrentstatus() {
		return this.currentstatus;
	}

	public void setCurrentstatus(Integer currentstatus) {
		this.currentstatus = currentstatus;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

}