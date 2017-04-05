package com.fangcang.titanjr.response;

import com.fangcang.titanjr.dto.OrganStatusEnum;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public class OrganStatusResponse extends BaseResponse {


    private String titanOrgId;

    private String titanCode;

    private OrganStatusEnum organStatusEnum;

    public OrganStatusEnum getOrganStatusEnum() {
        return organStatusEnum;
    }

    public void setOrganStatusEnum(OrganStatusEnum organStatusEnum) {
        this.organStatusEnum = organStatusEnum;
    }

    public String getTitanOrgId() {
        return titanOrgId;
    }

    public void setTitanOrgId(String titanOrgId) {
        this.titanOrgId = titanOrgId;
    }

    public String getTitanCode() {
        return titanCode;
    }

    public void setTitanCode(String titanCode) {
        this.titanCode = titanCode;
    }
}
