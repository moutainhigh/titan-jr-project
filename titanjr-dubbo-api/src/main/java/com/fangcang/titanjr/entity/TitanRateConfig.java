package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanRateChange entity. @author MyEclipse Persistence Tools
 */

public class TitanRateConfig implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4760794103759089824L;

	private Integer rateconfigid;
	private String userid;
	private String deskId;
	private String usedfor;
	private Integer ratetype;
	private Float standrate;
	private Float executionrate;
	private Float minrate;
	private Float maxrate;
	private String description;
	private String creator;
	private Date createtime;
	private Date expiration;

	// Property accessors
	public Integer getRateconfigid() {
		return this.rateconfigid;
	}

	public void setRateconfigid(Integer rateconfigid) {
		this.rateconfigid = rateconfigid;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public String getUsedfor() {
		return usedfor;
	}

	public void setUsedfor(String usedfor) {
		this.usedfor = usedfor;
	}

	public Float getStandrate() {
		return this.standrate;
	}

	public void setStandrate(Float standrate) {
		this.standrate = standrate;
	}

	public Float getExecutionrate() {
		return this.executionrate;
	}

	public void setExecutionrate(Float executionrate) {
		this.executionrate = executionrate;
	}

	public Float getMinrate() {
		return minrate;
	}

	public void setMinrate(Float minrate) {
		this.minrate = minrate;
	}

	public Float getMaxrate() {
		return maxrate;
	}

	public void setMaxrate(Float maxrate) {
		this.maxrate = maxrate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getRatetype() {
		return ratetype;
	}

	public void setRatetype(Integer ratetype) {
		this.ratetype = ratetype;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}