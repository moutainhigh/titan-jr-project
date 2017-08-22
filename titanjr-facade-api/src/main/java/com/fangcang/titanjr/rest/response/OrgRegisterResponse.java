package com.fangcang.titanjr.rest.response;

import com.fangcang.titanjr.response.BaseResponse;

/**
 * Created by zhaoshan on 2017/8/1.
 */
public class OrgRegisterResponse extends BaseResponse {

    private String coopOrgCode;
    private String jrOrgCode;

    private String coopUserId;
    private String jrUserId;

    public String getCoopOrgCode() {
        return coopOrgCode;
    }

    public void setCoopOrgCode(String coopOrgCode) {
        this.coopOrgCode = coopOrgCode;
    }

    public String getCoopUserId() {
        return coopUserId;
    }

    public void setCoopUserId(String coopUserId) {
        this.coopUserId = coopUserId;
    }

    public String getJrOrgCode() {
        return jrOrgCode;
    }

    public void setJrOrgCode(String jrOrgCode) {
        this.jrOrgCode = jrOrgCode;
    }

    public String getJrUserId() {
        return jrUserId;
    }

    public void setJrUserId(String jrUserId) {
        this.jrUserId = jrUserId;
    }
}
