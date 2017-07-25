/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName CashierDeskVersionEnum.java
 * @author Jerry
 * @date 2017年7月18日 下午4:53:14  
 */
package com.fangcang.titanjr.common.enums;

/** 
 * @Description: 收银台版本枚举
	
 * @author Jerry
 * @date 2017年7月18日 下午4:53:14  
 */
public enum CashierDeskVersionEnum {
	
	VERSION_1("1.0", "第一期收银台"),
	VERSION_2("1.1", "第二期收银台");
	
	public String key;
	public String value;
	
	private CashierDeskVersionEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 判断key是否在当前枚举的属性中
	 */
	public static boolean isExist(String key){
		if(key == null){
			return false;
		}
		
		for(CashierDeskVersionEnum e : CashierDeskVersionEnum.values()){
			if(key.equals(e.getKey())){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isVersion1(String key){
		if(key == null){
			return false;
		}
		
		if(key.equals(VERSION_1.key)){
			return true;
		}
		
		return false;
	}
	
	public static boolean isVersion2(String key){
		if(key == null){
			return false;
		}
		
		if(key.equals(VERSION_2.key)){
			return true;
		}
		
		return false;
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
