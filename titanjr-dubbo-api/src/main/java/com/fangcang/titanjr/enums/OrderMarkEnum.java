package com.fangcang.titanjr.enums;

public enum OrderMarkEnum {
    InsideOrder("1","内部订单"),OutsideOrder("0","外部订单");
	
	public String key;
	
	public String value;
	
	private OrderMarkEnum(String key, String value) {
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
