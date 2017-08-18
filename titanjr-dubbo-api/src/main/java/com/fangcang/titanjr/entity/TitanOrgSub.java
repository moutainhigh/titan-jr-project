package com.fangcang.titanjr.entity;

import java.util.Date;

/**
 * 子机构 
 * @author luoqinglong
 *
 */
public class TitanOrgSub implements java.io.Serializable {

	private static final long serialVersionUID = 234016678702765480L;
	private Integer orgsubid;
	private String orgcode;
	private String orgname;
	/**
	 * 机构类型：企业:1，个人:2
	 */
	private Integer usertype;
	/***
	 * 证件类型：身份证，士官证
	 */
	private Integer certificatetype;
	/**
	 * 证件号，如：身份证，士官证
	 */
	private String certificatenumber;
	/**
	 * 联系手机
	 */
	private String mobiletel;
	/***
	 * 联系人
	 */
	private String connect;
	/**
	 * 营业执照号
	 */
	private String buslince;
	private Date createTime;
	private Date updateTime;
	public Integer getOrgsubid() {
		return orgsubid;
	}
	public void setOrgsubid(Integer orgsubid) {
		this.orgsubid = orgsubid;
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
	 
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public Integer getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(Integer certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getCertificatenumber() {
		return certificatenumber;
	}
	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}
	public String getMobiletel() {
		return mobiletel;
	}
	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}