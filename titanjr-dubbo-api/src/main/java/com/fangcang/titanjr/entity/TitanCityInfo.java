package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanCityInfo entity. @author MyEclipse Persistence Tools
 */

public class TitanCityInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 795939388985677228L;
	private Integer cityinfoid;
	private String country;
	private String citycode;
	private String cityname;
	private Integer datatype;
	private String parentcode;
	private String fcdatacode;
	private Date createtime;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public TitanCityInfo() {
	}

	/** minimal constructor */
	public TitanCityInfo(String citycode) {
		this.citycode = citycode;
	}

	/** full constructor */
	public TitanCityInfo(String country, String citycode, String cityname,
			Integer datatype, String parentcode, String fcdatacode,
			Date createtime, Date modifytime) {
		this.country = country;
		this.citycode = citycode;
		this.cityname = cityname;
		this.datatype = datatype;
		this.parentcode = parentcode;
		this.fcdatacode = fcdatacode;
		this.createtime = createtime;
		this.modifytime = modifytime;
	}

	// Property accessors

	public Integer getCityinfoid() {
		return this.cityinfoid;
	}

	public void setCityinfoid(Integer cityinfoid) {
		this.cityinfoid = cityinfoid;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Integer getDatatype() {
		return this.datatype;
	}

	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
	}

	public String getParentcode() {
		return this.parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getFcdatacode() {
		return this.fcdatacode;
	}

	public void setFcdatacode(String fcdatacode) {
		this.fcdatacode = fcdatacode;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}