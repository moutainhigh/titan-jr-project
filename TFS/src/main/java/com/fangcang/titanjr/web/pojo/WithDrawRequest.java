package com.fangcang.titanjr.web.pojo;

/**
 * 账户提现请求参数
 * Created by zhaoshan on 2016/6/15.
 */
public class WithDrawRequest {

    private Integer useNewBankCard;
    private Integer hasBindBanCard;
    private String bankCode;
    private String bankName;
    private String accountNum;
    private String accountName;

    private String password;

    private String originalAccount;

    private String amount;

    public Integer getUseNewBankCard() {
        return useNewBankCard;
    }

    public void setUseNewBankCard(Integer useNewBankCard) {
        this.useNewBankCard = useNewBankCard;
    }

    public Integer getHasBindBanCard() {
        return hasBindBanCard;
    }

    public void setHasBindBanCard(Integer hasBindBanCard) {
        this.hasBindBanCard = hasBindBanCard;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalAccount() {
        return originalAccount;
    }

    public void setOriginalAccount(String originalAccount) {
        this.originalAccount = originalAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
