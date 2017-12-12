package com.fangcang.titanjr.rest.dto;

import com.sun.javafx.css.converters.BooleanConverter;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by zhaoshan on 2017/8/14.
 */
@ApiModel(value = "AccountBalanceDTO", description = "子账户余额详情")
public class AccountBalanceDTO {

    @ApiModelProperty(value = "账户编号")
    private String productId;

    @ApiModelProperty(value = "账户ID")
    private String accountId;

    @ApiModelProperty(value = "账户余额")
    private String amount;

    @ApiModelProperty(value = "可用余额")
    private String balanceUsable;

    @ApiModelProperty(value = "冻结金额")
    private String balanceFrozen;

    @ApiModelProperty(value = "账户状态1.正常，2.冻结中")
    private Integer status;

    @ApiModelProperty(value = "是否是主账户")
    private boolean mainAccount = false;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalanceUsable() {
        return balanceUsable;
    }

    public void setBalanceUsable(String balanceUsable) {
        this.balanceUsable = balanceUsable;
    }

    public String getBalanceFrozen() {
        return balanceFrozen;
    }

    public void setBalanceFrozen(String balanceFrozen) {
        this.balanceFrozen = balanceFrozen;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isMainAccount() {
        return mainAccount;
    }

    public void setMainAccount(boolean mainAccount) {
        this.mainAccount = mainAccount;
    }
}
