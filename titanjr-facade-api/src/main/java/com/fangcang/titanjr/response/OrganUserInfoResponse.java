package com.fangcang.titanjr.response;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public class OrganUserInfoResponse extends BaseResponse {

    private String titanOrgCode;
    private Integer titanUserId;
    private String titanUserName;
    private String titanUserLoginName;

    public String getTitanOrgCode() {
        return titanOrgCode;
    }

    public void setTitanOrgCode(String titanOrgCode) {
        this.titanOrgCode = titanOrgCode;
    }

    public Integer getTitanUserId() {
        return titanUserId;
    }

    public void setTitanUserId(Integer titanUserId) {
        this.titanUserId = titanUserId;
    }

    public String getTitanUserName() {
        return titanUserName;
    }

    public void setTitanUserName(String titanUserName) {
        this.titanUserName = titanUserName;
    }

    public String getTitanUserLoginName() {
        return titanUserLoginName;
    }

    public void setTitanUserLoginName(String titanUserLoginName) {
        this.titanUserLoginName = titanUserLoginName;
    }
}
