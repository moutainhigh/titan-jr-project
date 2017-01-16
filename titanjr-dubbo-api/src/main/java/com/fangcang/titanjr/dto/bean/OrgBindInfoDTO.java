package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

public class OrgBindInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String merchantCode;
	private String merchantName;
	//1.已绑定，0.未绑定
	private Integer bindStatus;
	private String orgcode;
	private String userid;
	private String resultKey;
	private String checkUser;
	private String titanCode;
	private String busline;
	private String userType;
	private String statusId;
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Integer getBindStatus() {
		return bindStatus;
	}
	public void setBindStatus(Integer bindStatus) {
		this.bindStatus = bindStatus;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getResultKey() {
		return resultKey;
	}
	public void setResultKey(String resultKey) {
		this.resultKey = resultKey;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public String getTitanCode() {
		return titanCode;
	}
	public void setTitanCode(String titanCode) {
		this.titanCode = titanCode;
	}
	public String getBusline() {
		return busline;
	}
	public void setBusline(String busline) {
		this.busline = busline;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
}
