package com.fangcang.titanjr.rest.response;

import com.fangcang.titanjr.response.BaseResponse;

/**
 * Created by zhaoshan on 2017/8/1.
 */
public class OrgRegisterResponse extends BaseResponse {

    private String jrOrgCode;
    private String jrUserId;

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
