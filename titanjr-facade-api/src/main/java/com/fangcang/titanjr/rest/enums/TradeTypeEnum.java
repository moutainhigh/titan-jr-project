package com.fangcang.titanjr.rest.enums;

public enum TradeTypeEnum {

    TRADE_RECORD("0", "交易记录"), PAYMENT_RECORD("1", "付款记录"), PAYEE_RECORD("2", "收款记录"),
    RECHARGE_RECORD("3", "充值记录"), WITHDRAW_RECORD("4", "提现记录");

    private String key;
    private String msg;

    private TradeTypeEnum(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
