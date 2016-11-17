package com.fangcang.titanjr.enums;

public enum VersionEnum {
	
    Version_1("v1.0","版本1");
	
	public String key;
	
	public String value;
	
	private VersionEnum(String key, String value) {
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
