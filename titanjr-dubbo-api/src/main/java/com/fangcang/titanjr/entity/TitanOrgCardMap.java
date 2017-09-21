package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 虚拟机构和绑卡关联
 * @author luoqinglong
 * @date 2017年8月31日
 */
public class TitanOrgCardMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -431653072719189070L;
	
	private Integer orgCardId;
	/***
	 * 虚拟证件机构编码
	 */
	private String orgCode;
	/** 800 ,
	 * 卡号
	 */
	private Integer bankcardid;
	private Integer isactive;
	private String creator;
	private Date createTime;
	
	public Integer getOrgCardId() {
		return orgCardId;
	}
	public void setOrgCardId(Integer orgCardId) {
		this.orgCardId = orgCardId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	public Integer getBankcardid() {
		return bankcardid;
	}
	public void setBankcardid(Integer bankcardid) {
		this.bankcardid = bankcardid;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
