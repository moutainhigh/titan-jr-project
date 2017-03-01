package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

/**
 * 通知合作方机构信息
 * @author luoqinglong
 * @date   2017年3月1日
 */
public class SynOrgInfoRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6736966858149875254L;
	/**
	 * 通知地址,格式：http://fangcang.com/TUS/index.action
	 */
	private String notifyUrl;
	private Integer coopType;
	/**
	 * 参数,格式：aaaa=1111&bbbb=22222&ccc=33
	 */
	private String kvparam;
	
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
	
}
