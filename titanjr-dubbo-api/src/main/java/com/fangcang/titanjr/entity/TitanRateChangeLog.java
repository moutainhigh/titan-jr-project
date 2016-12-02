package com.fangcang.titanjr.entity;

import java.util.Date;
// default package


/**
 * TitanRateChangeLogId entity. @author MyEclipse Persistence Tools
 */

public class TitanRateChangeLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3811296104754252524L;
	private Integer rateconfigid;
	private Float beforerate;
	private Float afterrate;
	private Date modifytime;
	private String modifier;

	// Constructors

	/** default constructor */
	public TitanRateChangeLog() {
	}

	/** minimal constructor */
	public TitanRateChangeLog(Integer rateconfigid) {
		this.rateconfigid = rateconfigid;
	}

	/** full constructor */
	public TitanRateChangeLog(Integer rateconfigid, Float beforerate,
			Float afterrate, Date modifytime, String modifier) {
		this.rateconfigid = rateconfigid;
		this.beforerate = beforerate;
		this.afterrate = afterrate;
		this.modifytime = modifytime;
		this.modifier = modifier;
	}

	// Property accessors

	public Integer getRateconfigid() {
		return this.rateconfigid;
	}

	public void setRateconfigid(Integer rateconfigid) {
		this.rateconfigid = rateconfigid;
	}

	public Float getBeforerate() {
		return this.beforerate;
	}

	public void setBeforerate(Float beforerate) {
		this.beforerate = beforerate;
	}

	public Float getAfterrate() {
		return this.afterrate;
	}

	public void setAfterrate(Float afterrate) {
		this.afterrate = afterrate;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
}