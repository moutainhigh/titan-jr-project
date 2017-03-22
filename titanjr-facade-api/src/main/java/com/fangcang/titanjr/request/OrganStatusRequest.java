package com.fangcang.titanjr.request;

import com.fangcang.titanjr.dto.OrganTypeEnum;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public class OrganStatusRequest extends BaseRequest {

    /**
     * 机构类型，必须传
     */
    private OrganTypeEnum organTypeEnum;

    /**
     * 合作方编码。可以是saas商家编码，也可以是ttmall机构编码
     */
    private String partnerCode;
    /**
     * 金融机构编码
     */
    private String titanOrgCode;

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getTitanOrgCode() {
        return titanOrgCode;
    }

    public void setTitanOrgCode(String titanOrgCode) {
        this.titanOrgCode = titanOrgCode;
    }

    public OrganTypeEnum getOrganTypeEnum() {
        return organTypeEnum;
    }

    public void setOrganTypeEnum(OrganTypeEnum organTypeEnum) {
        this.organTypeEnum = organTypeEnum;
    }
}
