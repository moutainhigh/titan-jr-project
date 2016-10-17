package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public class RSInvokeConfig implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8844161461085568046L;
	private String appKey;
    private String appSecret;
    private String invokeURL;
    private String sessionKey;
    private String defaultMerchant;
    private Long defaultRoleId;
    private Date updateTime;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
