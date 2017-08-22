package com.fangcang.titanjr.enums;

/**
 * 融数版本号
 * @author Jerry
 */
public enum RsVersionEnum {
	
    Version_1("v1.0","版本1"),
    Version_2("v1.1","版本2");
	
	public String key;
	
	public String value;
	
	private RsVersionEnum(String key, String value) {
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
