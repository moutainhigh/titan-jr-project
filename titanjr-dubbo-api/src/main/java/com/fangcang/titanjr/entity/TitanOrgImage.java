package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanOrgImage entity. @author MyEclipse Persistence Tools
 */

public class TitanOrgImage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 755299695283500900L;
	private Integer imageid;
	private String userid;
	private String orgcode;
	private String imageName;
	private Integer imagetype;
	private Integer sizetype;
	private String imageurl;
	private Integer isactive;
	private String creator;
	private Date createtime;
	private String modifier;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public TitanOrgImage() {
	}

	/** minimal constructor */
	public TitanOrgImage(String userid, String orgcode) {
		this.userid = userid;
		this.orgcode = orgcode;
	}

	/** full constructor */
	public TitanOrgImage(String userid, String orgcode, String imageName,
			Integer imagetype, Integer sizetype, String imageurl,
			Integer isactive, String creator, Date createtime, String modifier,
			Date modifytime) {
		this.userid = userid;
		this.orgcode = orgcode;
		this.imageName = imageName;
		this.imagetype = imagetype;
		this.sizetype = sizetype;
		this.imageurl = imageurl;
		this.isactive = isactive;
		this.creator = creator;
		this.createtime = createtime;
		this.modifier = modifier;
		this.modifytime = modifytime;
	}

	// Property accessors

	public Integer getImageid() {
		return this.imageid;
	}

	public void setImageid(Integer imageid) {
		this.imageid = imageid;
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

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getImagetype() {
		return this.imagetype;
	}

	public void setImagetype(Integer imagetype) {
		this.imagetype = imagetype;
	}

	public Integer getSizetype() {
		return this.sizetype;
	}

	public void setSizetype(Integer sizetype) {
		this.sizetype = sizetype;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
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