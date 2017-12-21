package com.titanjr.fop.response;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class ExternalSessionGetResponse extends FopResponse {

    private String session;

    public ExternalSessionGetResponse() {
    }

    public String getSession() {
        return this.session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}