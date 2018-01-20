package com.titanjr.fop.dto;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public class SHBalanceInfo implements Serializable {

    private String balancecredit;
    private String balancesettle;

    private String balancefrozon;
    private String balanceoverlimit;

    private String productid;
    private String amount;

    private String balanceusable;
    private String userid;

    public String getBalancecredit() {
        return balancecredit;
    }

    public void setBalancecredit(String balancecredit) {
        this.balancecredit = balancecredit;
    }

    public String getBalancesettle() {
        return balancesettle;
    }

    public void setBalancesettle(String balancesettle) {
        this.balancesettle = balancesettle;
    }

    public String getBalancefrozon() {
        return balancefrozon;
    }

    public void setBalancefrozon(String balancefrozon) {
        this.balancefrozon = balancefrozon;
    }

    public String getBalanceoverlimit() {
        return balanceoverlimit;
    }

    public void setBalanceoverlimit(String balanceoverlimit) {
        this.balanceoverlimit = balanceoverlimit;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalanceusable() {
        return balanceusable;
    }

    public void setBalanceusable(String balanceusable) {
        this.balanceusable = balanceusable;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
