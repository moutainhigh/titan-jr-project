package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class BankCardQueryRequest extends BaseRequest {
    //角色 可选
    private String role;
    //1：结算卡，2：所有绑定卡
    private String objorlist;
    //	用户类型(1：商户，2：普通用户)
    private String usertype;

    @Override
    public void check() throws RSValidateException {
        //校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.objorlist,"objorlist");
        RequestValidationUtil.checkNotEmpty(this.usertype,"usertype");
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getObjorlist() {
        return objorlist;
    }

    public void setObjorlist(String objorlist) {
        this.objorlist = objorlist;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
