package com.fangcang.titanjr.dto.bean;
// default package

import java.util.Date;

/**
 * TitanCityInfo entity. @author MyEclipse Persistence Tools
 */

public class CityInfoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 795939388985677228L;
	
	private String country;
	
	private String cityCode;
	
	private String cityName;
	
	private Integer dataType;
	
	private String parentCode;
	
	private String fcdataCode;
	
	private Date createTime;
	
	private Date modifyTime;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getFcdataCode() {
		return fcdataCode;
	}
	public void setFcdataCode(String fcdataCode) {
		this.fcdataCode = fcdataCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


}