package com.fangcang.titanjr.rs.entity;

import java.io.Serializable;

public class Sysconfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8145028715232537337L;
	private String cfgKey;
	private String cfgValue; 
	private String objKey;
	private Integer isactive;
	
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
	
	
}
