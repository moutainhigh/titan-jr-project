package com.fangcang.titanjr.rs.dto;

import com.Rop.api.internal.mapping.ApiField;

/**
 * Created by zhaoshan on 2016/4/12.
 */
public class BankInfo {

    private String bankname;

    private String bankid;

    private String bankcode;

    private String createdtime;

    private String statusid;

    private String updatedtime;

    private String updatebatch;

    private String parentcode;

    private Integer banktype;

    private String bankcity;

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }

    public String getUpdatebatch() {
        return updatebatch;
    }

    public void setUpdatebatch(String updatebatch) {
        this.updatebatch = updatebatch;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public Integer getBanktype() {
        return banktype;
    }

    public void setBanktype(Integer banktype) {
        this.banktype = banktype;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }
}
