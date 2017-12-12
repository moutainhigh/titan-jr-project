package com.fangcang.titanjr.web.pojo;

/**
 * Created by zhaoshan on 2017/9/7.
 * 收银台开关
 */
public class CashierSwitchPO {

    //收银台类型，1：SaaS分销工具GDP，2：商家联盟交易平台PUS
    private  Integer cashierType;

    //1:打开，0：关闭
    private Integer open;

    //操作人
    private String operator;

    public Integer getCashierType() {
        return cashierType;
    }

    public void setCashierType(Integer cashierType) {
        this.cashierType = cashierType;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
