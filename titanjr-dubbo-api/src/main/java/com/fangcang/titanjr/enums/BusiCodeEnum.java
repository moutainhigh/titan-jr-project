package com.fangcang.titanjr.enums;

public enum BusiCodeEnum {
    MerchantOrder("101","商户订单"),QueryOrder("102","查询订单"),MerchantRefund("103","商户退款"),QueryRefund("104","查询退款");
	
	public String key;
	
	public String value;
	
	private BusiCodeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
