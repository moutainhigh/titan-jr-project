package com.fangcang.titanjr.web.pojo;

/***
 * TTM合作方注册信息
 * @author luoqinglong
 * @date   2017年2月15日
 */
public class CoopRegInfo {
	/**
	 * 合作方机构编码
	 */
	private String coopOrgCode;
	/**
	 * 合作方机构名称
	 */
	private String coopOrgName;
	/**
	 * 合作方登录用户
	 */
	private String coopLoginName;
	/**
	 * 合作方用户id
	 */
	private String coopUserId;
	/**
	 * 注册完成后通知第三方地址
	 */
	private String notifyurl;
	
	
	public String getCoopOrgCode() {
		return coopOrgCode;
	}
	public void setCoopOrgCode(String coopOrgCode) {
		this.coopOrgCode = coopOrgCode;
	}
	public String getCoopOrgName() {
		return coopOrgName;
	}
	public void setCoopOrgName(String coopOrgName) {
		this.coopOrgName = coopOrgName;
	}
	public String getCoopLoginName() {
		return coopLoginName;
	}
	public void setCoopLoginName(String coopLoginName) {
		this.coopLoginName = coopLoginName;
	}
	public String getCoopUserId() {
		return coopUserId;
	}
	public void setCoopUserId(String coopUserId) {
		this.coopUserId = coopUserId;
	}
	public String getNotifyurl() {
		return notifyurl;
	}
	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}
}
