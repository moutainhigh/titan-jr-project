package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class DeletePersonCardRequest extends BaseRequest {

    //第三方账户Id 如台长用户ID，不填为主账户
    //可选，暂不关注
    private Long referuserid;
    //角色可选
    private String role;
    //用户类型(1：商户，2：普通用户)
    private String usertype;
    //银行卡号
    private String accountnumber;

    @Override
    public void check() throws RSValidateException {
        //校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.accountnumber,"accountnumber");
        RequestValidationUtil.checkNotEmpty(this.usertype,"usertype");
    }

    public Long getReferuserid() {
        return referuserid;
    }

    public void setReferuserid(Long referuserid) {
        this.referuserid = referuserid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }
}
