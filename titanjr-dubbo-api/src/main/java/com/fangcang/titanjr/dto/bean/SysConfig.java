package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

public class SysConfig implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//融数调用回话key
    private String sessionKey;
    //支付网关地址
    private String gateWayURL;
    //融数给出构造加密数据的签名
    private String rsCheckKey;
    //房仓给融数构造加密数据的签名
    private String titanjrCheckKey ;
    //checkstand付结果前台回调pay-app的地址
    private String csPayConfirmPageURL;
    //checkstand支付结果后台通知pay-app的地址
    private String csPayNoticeURL;
    //checkstand卡密鉴权前台回调pay-app的地址
    private String csCardAuthPageURL;
    //checkstand卡密鉴权后台通知pay-app的地址
    private String csCardAuthNoticeURL;
   
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getGateWayURL() {
		return gateWayURL;
	}
	public void setGateWayURL(String gateWayURL) {
		this.gateWayURL = gateWayURL;
	}
	public String getRsCheckKey() {
		return rsCheckKey;
	}
	public void setRsCheckKey(String rsCheckKey) {
		this.rsCheckKey = rsCheckKey;
	}
	public String getTitanjrCheckKey() {
		return titanjrCheckKey;
	}
	public void setTitanjrCheckKey(String titanjrCheckKey) {
		this.titanjrCheckKey = titanjrCheckKey;
	}
	public String getCsPayConfirmPageURL() {
		return csPayConfirmPageURL;
	}
	public void setCsPayConfirmPageURL(String csPayConfirmPageURL) {
		this.csPayConfirmPageURL = csPayConfirmPageURL;
	}
	public String getCsPayNoticeURL() {
		return csPayNoticeURL;
	}
	public void setCsPayNoticeURL(String csPayNoticeURL) {
		this.csPayNoticeURL = csPayNoticeURL;
	}
	public String getCsCardAuthPageURL() {
		return csCardAuthPageURL;
	}
	public void setCsCardAuthPageURL(String csCardAuthPageURL) {
		this.csCardAuthPageURL = csCardAuthPageURL;
	}
	public String getCsCardAuthNoticeURL() {
		return csCardAuthNoticeURL;
	}
	public void setCsCardAuthNoticeURL(String csCardAuthNoticeURL) {
		this.csCardAuthNoticeURL = csCardAuthNoticeURL;
	}

}
