package com.fangcang.titanjr.entity;

import java.util.Date;

public class TitanOrgMapInfo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5569840372471123855L;
	
	private Integer orgMapid;
	private String orgCode;
	private String orgSubcode;
	private Integer isactive;
	private Date createTime;
	private Date updateTime;
	
	public Integer getOrgMapid() {
		return orgMapid;
	}
	public void setOrgMapid(Integer orgMapid) {
		this.orgMapid = orgMapid;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgSubcode() {
		return orgSubcode;
	}
	public void setOrgSubcode(String orgSubcode) {
		this.orgSubcode = orgSubcode;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
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
