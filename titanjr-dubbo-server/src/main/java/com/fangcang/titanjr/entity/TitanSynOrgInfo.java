package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 同步机构信息
 * @author luoqinglong
 * @date   2017年2月28日
 */
public class TitanSynOrgInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4200404910304732445L;
	private Integer synId;
	private String notifyUrl;
	private Integer coopType;
	/**
	 * 是否通知：0-不通知，1-通知
	 */
	private Integer isActive;
	private String kvparam;
	private Date createTime;
	public Integer getSynId() {
		return synId;
	}
	public void setSynId(Integer synId) {
		this.synId = synId;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public Integer getCoopType() {
		return coopType;
	}
	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
	public String getKvparam() {
		return kvparam;
	}
	public void setKvparam(String kvparam) {
		this.kvparam = kvparam;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
	
}
