package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

public class OrgDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1870194981651203462L;
	private Integer orgid;
	private String orgcode;
	private String orgname;
	private String constid;
	private String userid;
	private String productid;
	private String titancode;
	
	
	public Integer getOrgid() {
		return orgid;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getConstid() {
		return constid;
	}
	public void setConstid(String constid) {
		this.constid = constid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getTitancode() {
		return titancode;
	}
	public void setTitancode(String titancode) {
		this.titancode = titancode;
	}
	
}
