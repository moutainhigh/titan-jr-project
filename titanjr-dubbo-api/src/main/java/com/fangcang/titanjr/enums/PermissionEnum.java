package com.fangcang.titanjr.enums;


public enum PermissionEnum {
	Pay("1","PAY"),View("2","VIEW"),Recharge("3","RECHARGE"),Financing("4","FINANCING"),Loan("5","LOAN"),
	Message("6","MESSAGE"),Operation("7","OPERATION");
		
	public String key;
	
	public String value;
	
	private PermissionEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static String getValueByKey (String key) {
		String val = "";
		for (PermissionEnum bte : PermissionEnum.values()) {
			if (bte.key.equals(key)) {
				val = bte.value;
				break;
			}
		}
		return val;
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
