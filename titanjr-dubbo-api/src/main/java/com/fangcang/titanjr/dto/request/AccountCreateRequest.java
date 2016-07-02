package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * Created by zhaoshan on 2016/5/9.
 */
public class AccountCreateRequest extends BaseRequestDTO{
    private String userid;
    private String accountcode;
    private String accountname;
    private Integer allownopwdpay;
    private Double nopwdpaylimit;
    private String currency;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Integer getAllownopwdpay() {
        return allownopwdpay;
    }

    public void setAllownopwdpay(Integer allownopwdpay) {
        this.allownopwdpay = allownopwdpay;
    }

    public Double getNopwdpaylimit() {
        return nopwdpaylimit;
    }

    public void setNopwdpaylimit(Double nopwdpaylimit) {
        this.nopwdpaylimit = nopwdpaylimit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
