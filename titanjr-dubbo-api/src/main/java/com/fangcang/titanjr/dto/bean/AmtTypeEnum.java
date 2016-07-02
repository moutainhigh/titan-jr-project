package com.fangcang.titanjr.dto.bean;

import com.fangcang.util.StringUtil;

public enum AmtTypeEnum {
	
	 RMB("01","人民币","1");
		
	public String key;
	
	public String value;
	
	public String code;
	
	private AmtTypeEnum(String key, String value,String code) {
		this.key = key;
		this.value = value;
		this.code = code;
	}
	
	public static AmtTypeEnum getAmtTypeEnum(String atmcode){
		if(StringUtil.isValidString(atmcode)){
			for(AmtTypeEnum ate:AmtTypeEnum.values()){
				if(ate.getCode().equals(atmcode)){
					return ate;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
