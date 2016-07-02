package com.fangcang.titanjr.common.enums;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public enum CashierItemTypeEnum {

    B2B_ITEM("1","B2B网银"),B2C_ITEM("2","B2C网银"),
    CREDIT_ITEM("3","信用卡"),BALANCE_ITEM("4","账户余额"),
    FINANCING_ITEM("5","理财"),MOBILE_ITEM("6","移动端");

    public String itemCode;
    public String itemName;

    private CashierItemTypeEnum(String itemCode,String itemName){
        this.itemCode = itemCode;
        this.itemName = itemName;
    }
}
