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
    //融数支付结果前台回调的地址
    private String LocalPayConfirmPageURL;
    //融数支付结果后台通知的地址
    private String LocalPayNoticeURL;
   
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
	public String getLocalPayConfirmPageURL() {
		return LocalPayConfirmPageURL;
	}
	public void setLocalPayConfirmPageURL(String localPayConfirmPageURL) {
		LocalPayConfirmPageURL = localPayConfirmPageURL;
	}
	public String getLocalPayNoticeURL() {
		return LocalPayNoticeURL;
	}
	public void setLocalPayNoticeURL(String localPayNoticeURL) {
		LocalPayNoticeURL = localPayNoticeURL;
	}

}
