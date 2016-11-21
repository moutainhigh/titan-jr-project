package com.fangcang.titanjr.enums;

public enum SignTypeEnum {
   
	MD5("1","md5加密");
		
	public String key;
	
	public String value;
	
	private SignTypeEnum(String key, String value) {
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
