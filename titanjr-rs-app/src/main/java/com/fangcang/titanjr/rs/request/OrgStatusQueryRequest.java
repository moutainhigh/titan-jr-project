package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class OrgStatusQueryRequest extends BaseRequest {

    //用户类型(1：商户，2：普通用户)
    private String usertype;
    //角色
    private String role;
    //账户状态（1：生效，2：冻结，3：注销）不填默认为1：生效
    private String statusid;
    //第三方账户Id 如台长用户ID，不填为主账户
    private String referuserid;
    

    @Override
    public void check() throws RSValidateException {
        //校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.usertype,"usertype");
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getRole() {
        return role;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getReferuserid() {
        return referuserid;
    }

    public void setReferuserid(String referuserid) {
        this.referuserid = referuserid;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
