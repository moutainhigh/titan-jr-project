package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

public class BankCardInfoDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //账户ID
    private String finaccountid;
    private String bankbranch;
    private String bankcity;
    private String currency;
    //账户类型ID（00：银行卡，01：存折，02：信用卡）
    private String account_type_id;
    private String bankheadname;
    private String updatedtime;
    private String bankprovince;
    private String bankbranchname;
    private String errorcode;
    //	账号
    private String account_number;
    //账户目的(1：结算卡，2：其他卡, 3：提现卡, 4：结算提现一体卡)
    private String accountpurpose;
    //账户信息ID
    private String accountid;
    //账户属性(1:对公, 2:对私)
    private String accountproperty;
    //账号用途
    private String open_account_description;
    private String createdtime;
    private String bankhead;
    //账户名称
    private String accountname;
    private String certificatetype;
    //用户真实姓名
    private String accountrealname;
    //状态(1：正常，2：待审核，3：审核中，4：审核失败，0：失效)
    private String status;
    private String certificatenumber;
    //开户日期
    private String open_account_date;

    public String getFinaccountid() {
        return finaccountid;
    }

    public void setFinaccountid(String finaccountid) {
        this.finaccountid = finaccountid;
    }

    public String getBankbranch() {
        return bankbranch;
    }

    public void setBankbranch(String bankbranch) {
        this.bankbranch = bankbranch;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccount_type_id() {
        return account_type_id;
    }

    public void setAccount_type_id(String account_type_id) {
        this.account_type_id = account_type_id;
    }

    public String getBankheadname() {
        return bankheadname;
    }

    public void setBankheadname(String bankheadname) {
        this.bankheadname = bankheadname;
    }

    public String getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince;
    }

    public String getBankbranchname() {
        return bankbranchname;
    }

    public void setBankbranchname(String bankbranchname) {
        this.bankbranchname = bankbranchname;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccountpurpose() {
        return accountpurpose;
    }

    public void setAccountpurpose(String accountpurpose) {
        this.accountpurpose = accountpurpose;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getAccountproperty() {
        return accountproperty;
    }

    public void setAccountproperty(String accountproperty) {
        this.accountproperty = accountproperty;
    }

    public String getOpen_account_description() {
        return open_account_description;
    }

    public void setOpen_account_description(String open_account_description) {
        this.open_account_description = open_account_description;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getBankhead() {
        return bankhead;
    }

    public void setBankhead(String bankhead) {
        this.bankhead = bankhead;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCertificatenumber() {
        return certificatenumber;
    }

    public void setCertificatenumber(String certificatenumber) {
        this.certificatenumber = certificatenumber;
    }

    public String getOpen_account_date() {
        return open_account_date;
    }

    public void setOpen_account_date(String open_account_date) {
        this.open_account_date = open_account_date;
    }
}
