package com.fangcang.titanjr.common.enums;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public enum CashierDeskTypeEnum {

    B2B_DESK("1","B2B"),SUPPLY_DESK("2","财务供应商"),
    ALLIANCE_DESK("3","商家联盟"),MOBILE_DESK("4","移动端"),
    RECHARGE("5","充值"),OPEN_ORG("6","对外开放"),TT_MALL("7","TTMALL");

    public String deskCode;
    public String deskName;

    private CashierDeskTypeEnum(String deskCode,String deskName){
        this.deskCode = deskCode;
        this.deskName = deskName;
    }
}
