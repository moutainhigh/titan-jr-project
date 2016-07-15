package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanBankinfo entity. @author MyEclipse Persistence Tools
 */

public class TitanBankinfo implements java.io.Serializable {

    private Integer bankid;
    private String bankcode;
    private String bankname;
    private String bankcity;
    private String updatebatch;
    private Integer banktype;
    private Integer statusid;
    private String parentcode;
    private Date createtime;
    private Date updatetime;

    public Integer getBankid() {
        return this.bankid;
    }

    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    public String getBankcode() {
        return this.bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankname() {
        return this.bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankcity() {
        return this.bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }

    public String getUpdatebatch() {
        return this.updatebatch;
    }

    public void setUpdatebatch(String updatebatch) {
        this.updatebatch = updatebatch;
    }

    public Integer getBanktype() {
        return this.banktype;
    }

    public void setBanktype(Integer banktype) {
        this.banktype = banktype;
    }

    public Integer getStatusid() {
        return this.statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getParentcode() {
        return this.parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}