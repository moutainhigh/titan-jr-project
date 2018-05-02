package com.fangcang.titanjr.request;

import com.fangcang.titanjr.dto.OrganTypeEnum;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public class OrganInfoQueryRequest extends BaseRequest {

    /**
     * 机构类型，必须传
     */
    private OrganTypeEnum organTypeEnum;
    /**
     * partnerUserId partnerLoginName 查金融用户时至少一个不为空
     * 关联方saas或者ttmall系统用户登录名，根据用户登录名查询机构
     */
    private String partnerLoginName;

    /**
     * partnerUserId partnerLoginName 查金融用户时至少一个不为空
     * 关联方saas或者ttmall系统用户id，根据用户id查询机构
     */
    private String partnerUserId;

    /**
     * 必传不能为空
     * 关联方机构编码。saas商家编码或者ttmall机构编码,
     */
    private String partnerCode;

    public OrganTypeEnum getOrganTypeEnum() {
        return organTypeEnum;
    }

    public void setOrganTypeEnum(OrganTypeEnum organTypeEnum) {
        this.organTypeEnum = organTypeEnum;
    }

    public String getPartnerLoginName() {
        return partnerLoginName;
    }

    public void setPartnerLoginName(String partnerLoginName) {
        this.partnerLoginName = partnerLoginName;
    }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public void setPartnerUserId(String partnerUserId) {
        this.partnerUserId = partnerUserId;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }
}
