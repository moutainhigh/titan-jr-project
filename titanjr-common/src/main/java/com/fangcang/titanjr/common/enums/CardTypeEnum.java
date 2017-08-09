package com.fangcang.titanjr.common.enums;

/**
 * @Description: 支付银行所支持的卡类型
	
 * @author Jerry
 * @date 2017年7月19日 上午11:53:22
 */
public enum CardTypeEnum {
	
	DEPOSIT(1, "储蓄卡", 10),
	CREDIT(2, "信用卡", 21),
	BOTH(3, "两者皆有", 0);
	
	public int key;
	public String value;
	public int payerAccountType;
	
	private CardTypeEnum(int key, String value, int payerAccountType) {
		this.key = key;
		this.value = value;
		this.payerAccountType = payerAccountType;
	}
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public int getPayerAccountType() {
		return payerAccountType;
	}

	public void setPayerAccountType(int payerAccountType) {
		this.payerAccountType = payerAccountType;
	}

}
