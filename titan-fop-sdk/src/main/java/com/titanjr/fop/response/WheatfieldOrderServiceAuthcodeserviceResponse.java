package com.titanjr.fop.response;

/**
 * Created by zhaoshan on 2017/12/28.
 */
public class WheatfieldOrderServiceAuthcodeserviceResponse extends FopResponse {


    private String is_success;

    private String authcode;

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }
}
