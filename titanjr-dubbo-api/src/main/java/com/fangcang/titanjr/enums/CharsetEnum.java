package com.fangcang.titanjr.enums;

public enum CharsetEnum {
	UTF_8("1","utf-8编码");
	
	public String key;
	
	public String value;
	
	private CharsetEnum(String key, String value) {
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
