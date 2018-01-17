package com.titanjr.fop.dto;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2018/1/16.
 */
public class Transorderinfo implements Serializable {

    private String amount;
    private String orderno;
    private String createdtime;
    private String funccode;
    private String errorcode;
    private String requestno;
    private String orderpackageno;
    private String updatedtime;
    private String errormsg;
    private String transsumid;
    private String orderstatus;
    private String merchantcode;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getFunccode() {
        return funccode;
    }

    public void setFunccode(String funccode) {
        this.funccode = funccode;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public String getOrderpackageno() {
        return orderpackageno;
    }

    public void setOrderpackageno(String orderpackageno) {
        this.orderpackageno = orderpackageno;
    }

    public String getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getTranssumid() {
        return transsumid;
    }

    public void setTranssumid(String transsumid) {
        this.transsumid = transsumid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
    }
}
