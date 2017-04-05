package com.fangcang.titanjr.response;

/**
 * Created by zhaoshan on 2016/12/27.
 */
public class BalanceQueryResponse extends BaseResponse{

    private String accountId;
    //可用余额
    private String balanceUsable;
    //冻结金额
    private String balanceFrozon;
    /**
     * 账户状态1.正常，2.冻结中
     */
    private Integer status;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBalanceUsable() {
        return balanceUsable;
    }

    public void setBalanceUsable(String balanceUsable) {
        this.balanceUsable = balanceUsable;
    }

    public String getBalanceFrozon() {
        return balanceFrozon;
    }

    public void setBalanceFrozon(String balanceFrozon) {
        this.balanceFrozon = balanceFrozon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
