package com.titanjr.fop.entity;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/12/26.
 * 生成所有session需要保存
 */
public class RequestSession {

    private String appKey;
    private String appSecret;
    private String session;
    private Date createTime;

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

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
