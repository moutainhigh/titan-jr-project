package com.fangcang.titanjr.common.enums;

/**
 * Created by zhaoshan on 2016/5/9.
 */
public enum FinancialRoleEnum {

    PAY("PAY","付款权限"),VIEW("VIEW","查看权限"),RECHARGE("RECHARGE","充值和提现权限"),
    FINANCING("FINANCING","理财权限"),LOAN("LOAN","贷款权限"),
    MESSAGE("MESSAGE","消息提醒权限"),OPERATION("OPERATION","系统运营员权限");

    public String roleCode;
    public String roleName;

    private FinancialRoleEnum(String roleCode,String roleName){
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

}
