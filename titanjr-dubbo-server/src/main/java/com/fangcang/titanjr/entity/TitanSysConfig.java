package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;

public class TitanSysConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8136163212405460390L;
	private Integer cfgId;
	private String cfgKey;
	private String cfgValue; 
	private String objKey;
	private Integer isactive;
	private String remark;
	private Date updateTime;
	
	public Integer getCfgId() {
		return cfgId;
	}
	public void setCfgId(Integer cfgId) {
		this.cfgId = cfgId;
	}
	public String getCfgKey() {
		return cfgKey;
	}
	public void setCfgKey(String cfgKey) {
		this.cfgKey = cfgKey;
	}
	public String getCfgValue() {
		return cfgValue;
	}
	public void setCfgValue(String cfgValue) {
		this.cfgValue = cfgValue;
	}
	public String getObjKey() {
		return objKey;
	}
	public void setObjKey(String objKey) {
		this.objKey = objKey;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
