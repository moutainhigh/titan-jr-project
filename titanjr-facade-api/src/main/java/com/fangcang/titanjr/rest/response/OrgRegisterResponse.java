package com.fangcang.titanjr.rest.response;

import com.fangcang.titanjr.response.BaseResponse;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by zhaoshan on 2017/8/1.
 */
@ApiModel(value = "OrgRegisterResponse", description = "机构注册返回结果")
public class OrgRegisterResponse extends BaseResponse {
    @ApiModelProperty(value = "合作方机构编码",required = true)
    private String coopOrgCode;
    @ApiModelProperty(value = "金融注册的机构编码",required = true)
    private String jrOrgCode;
    @ApiModelProperty(value = "合作方用户id",required = true)
    private String coopUserId;
    @ApiModelProperty(value = "金融系统用户id",required = true)
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
