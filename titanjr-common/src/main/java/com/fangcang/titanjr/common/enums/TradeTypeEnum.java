package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

public enum TradeTypeEnum {

	TRADE_RECORD("0", "交易记录"), PAYMENT_RECORD("1", "付款记录"), PAYEE_RECORD("2", "收款记录"),
	RECHARGE_RECORD("3", "充值"), WITHDRAW_RECORD("4", "提现"), FREEZE_RECORD("5", "冻结记录");

	private String key;
	private String msg;

	private TradeTypeEnum(String key, String msg) {
		this.key = key;
		this.msg = msg;
	}

	public static TradeTypeEnum getTradeTypeEnumByKey(String key) {
		if(StringUtil.isValidString(key)){
			for (TradeTypeEnum queryTypeEnum : TradeTypeEnum.values()) {
				if (key.equals(queryTypeEnum.getKey())) {
					return queryTypeEnum;
				}
			}
		}
		return null;
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
