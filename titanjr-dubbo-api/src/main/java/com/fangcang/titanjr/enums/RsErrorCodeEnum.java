package com.fangcang.titanjr.enums;

import com.fangcang.util.StringUtil;

/**
 * 融数错误代码
 * @author Jerry
 */
public enum RsErrorCodeEnum {
	
    VALIFYCODE_NOT_SEND("40007","请先获取验证码"),
    REQUEST_MORE_TIMIE("11012","操作频繁，请一分钟后再试");
	
	public String key;
	
	public String value;
	
	private RsErrorCodeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	
	/**
	 * @author Jerry
	 * @date 2017年10月25日 下午3:27:27
	 */
	public static String getValueByKey(String key){
		
		if(!StringUtil.isValidString(key)){
			return null;
		}
		
		for (RsErrorCodeEnum e : RsErrorCodeEnum.values()) {
			if(e.key.equals(key)){
				return e.value;
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
}
