package com.fangcang.titanjr.rs.entity;

/**
 * Created by zhaoshan on 2016/7/21.
 */
public class TitanCallBackConfig {

    private long id;
    private String paySource;
    private String callBackURL;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public String getCallBackURL() {
        return callBackURL;
    }

    public void setCallBackURL(String callBackURL) {
        this.callBackURL = callBackURL;
    }
}
