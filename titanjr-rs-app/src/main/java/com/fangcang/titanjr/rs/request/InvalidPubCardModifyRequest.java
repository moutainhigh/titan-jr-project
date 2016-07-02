package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class InvalidPubCardModifyRequest extends BaseRequest{

    //===============必填信息===============
    //信息主键
    private String accountid;
    //用户类型(1：商户，2：普通用户)
    private String usertype;

    //==============可选参数=======
    private String role;
    private String certificatetype;
    //	持卡人真实姓名
    private String accountrealname;
    private String hankheadname;
    private String hankbranchname;
    private String hankbranch;
    private String bankcity;
    private String bankprovinec;
    private String accountnumber;
    private String bankhead;

    private String certificatenumber;


    @Override
    public void check() throws RSValidateException {
        //校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.accountid,"accountid");
        RequestValidationUtil.checkNotEmpty(this.usertype,"usertype");

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCertificatetype() {
        return certificatetype;
    }

    public void setCertificatetype(String certificatetype) {
        this.certificatetype = certificatetype;
    }

    public String getAccountrealname() {
        return accountrealname;
    }

    public void setAccountrealname(String accountrealname) {
        this.accountrealname = accountrealname;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getHankheadname() {
        return hankheadname;
    }

    public void setHankheadname(String hankheadname) {
        this.hankheadname = hankheadname;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }

    public String getBankprovinec() {
        return bankprovinec;
    }

    public void setBankprovinec(String bankprovinec) {
        this.bankprovinec = bankprovinec;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getBankhead() {
        return bankhead;
    }

    public void setBankhead(String bankhead) {
        this.bankhead = bankhead;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getCertificatenumber() {
        return certificatenumber;
    }

    public void setCertificatenumber(String certificatenumber) {
        this.certificatenumber = certificatenumber;
    }

    public String getHankbranchname() {
        return hankbranchname;
    }

    public void setHankbranchname(String hankbranchname) {
        this.hankbranchname = hankbranchname;
    }

    public String getHankbranch() {
        return hankbranch;
    }

    public void setHankbranch(String hankbranch) {
        this.hankbranch = hankbranch;
    }
}
