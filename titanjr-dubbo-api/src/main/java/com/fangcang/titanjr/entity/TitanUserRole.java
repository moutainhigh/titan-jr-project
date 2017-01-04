package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanUserRole entity. @author MyEclipse Persistence Tools
 */

public class TitanUserRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5833244225380401352L;
	private Integer tfsuserid;
	private Integer roleid;
	private Integer isactive;
	private String creator;
	private Date createtime;
	private String modifier;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public TitanUserRole() {
	}


	public String getCreator() {
		return this.creator;
	}
	

	public Integer getIsactive() {
		return isactive;
	}


	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
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

	public Integer getTfsuserid() {
		return tfsuserid;
	}

	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}