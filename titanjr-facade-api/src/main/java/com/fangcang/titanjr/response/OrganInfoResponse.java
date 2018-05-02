package com.fangcang.titanjr.response;

import com.fangcang.titanjr.dto.OrganStatusEnum;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public class OrganInfoResponse extends BaseResponse {

    private String titanOrgId;

    private String titanOrgCode;

    private String titanOrgName;

    private String titanCode;

    private OrganStatusEnum organStatusEnum;

    public OrganStatusEnum getOrganStatusEnum() {
        return organStatusEnum;
    }

    public void setOrganStatusEnum(OrganStatusEnum organStatusEnum) {
        this.organStatusEnum = organStatusEnum;
    }

    public String getTitanOrgCode() {
        return titanOrgCode;
    }

    public void setTitanOrgCode(String titanOrgCode) {
        this.titanOrgCode = titanOrgCode;
    }

    public String getTitanOrgId() {
        return titanOrgId;
    }

    public void setTitanOrgId(String titanOrgId) {
        this.titanOrgId = titanOrgId;
    }

    public String getTitanOrgName() {
        return titanOrgName;
    }

    public void setTitanOrgName(String titanOrgName) {
        this.titanOrgName = titanOrgName;
    }

    public String getTitanCode() {
        return titanCode;
    }

    public void setTitanCode(String titanCode) {
        this.titanCode = titanCode;
    }
}
