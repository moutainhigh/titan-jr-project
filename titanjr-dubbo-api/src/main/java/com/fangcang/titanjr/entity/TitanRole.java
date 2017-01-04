package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanRole entity. @author MyEclipse Persistence Tools
 */

public class TitanRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6558637749355985853L;
	private Long roleid;
	private String rolecode;
	private Long fcroleid;
	private String rolename;
	private String roleremark;
	private Integer isactive;
	private String creator;
	private Date createtime;
	private String modifier;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public TitanRole() {
	}

	/** full constructor */
	public TitanRole(String rolecode, Long fcroleid, String fcrolecode,
			String rolename, String roleremark, String orgcode,
			Integer isactive, String creator, Date createtime, String modifier,
			Date modifytime) {
		this.rolecode = rolecode;
		this.fcroleid = fcroleid;
		this.rolename = rolename;
		this.roleremark = roleremark;
		this.isactive = isactive;
		this.creator = creator;
		this.createtime = createtime;
		this.modifier = modifier;
		this.modifytime = modifytime;
	}

	// Property accessors

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public Long getFcroleid() {
		return this.fcroleid;
	}

	public void setFcroleid(Long fcroleid) {
		this.fcroleid = fcroleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoleremark() {
		return this.roleremark;
	}

	public void setRoleremark(String roleremark) {
		this.roleremark = roleremark;
	}

	public Integer getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
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