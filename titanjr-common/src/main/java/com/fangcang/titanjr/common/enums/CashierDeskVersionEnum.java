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
	VERSION_2("2.0", "第二期收银台");
	
	public String key;
	public String value;
	
	private CashierDeskVersionEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 判断key是否在指定枚举集合中
	 */
	public static boolean between(String key,CashierDeskVersionEnum... enums){
		if(key == null){
			return false;
		}
		
		if(enums == null){
			return false;
		}
		
		for(CashierDeskVersionEnum e : enums){
			if(key.equals(e.getKey())){
				return true;
			}
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
