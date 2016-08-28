package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class InvokeConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 870765011110634337L;
	private String appKey;
	private String appSecret;
	private String invokeURL;
	private String sessionKey;
	private String defaultMerchant;
	private Long defaultRoleId;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getInvokeURL() {
		return invokeURL;
	}

	public void setInvokeURL(String invokeURL) {
		this.invokeURL = invokeURL;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getDefaultMerchant() {
		return defaultMerchant;
	}

	public void setDefaultMerchant(String defaultMerchant) {
		this.defaultMerchant = defaultMerchant;
	}

	public Long getDefaultRoleId() {
		return defaultRoleId;
	}

	public void setDefaultRoleId(Long defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}
}
