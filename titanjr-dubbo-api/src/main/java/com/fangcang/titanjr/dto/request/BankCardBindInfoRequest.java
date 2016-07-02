package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import org.hibernate.validator.constraints.NotEmpty;


public class BankCardBindInfoRequest extends BaseRequestDTO {

    //角色可选
    private String role;

    //1：结算卡，2：所有绑定卡
    @NotEmpty
    private String objorlist;
    //用户类型(1：商户，2：普通用户)
    @NotEmpty
    private String usertype;
    //机构id
    @NotEmpty
    private String userid;
    //机构码
    @NotEmpty
    private String constid;
    //产品号
    @NotEmpty
    private String productid;

    //账户用途
    private Integer accountPurpose;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getConstid() {
        return constid;
    }

    public void setConstid(String constid) {
        this.constid = constid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public Integer getAccountPurpose() {
        return accountPurpose;
    }

    public void setAccountPurpose(Integer accountPurpose) {
        this.accountPurpose = accountPurpose;
    }
}
