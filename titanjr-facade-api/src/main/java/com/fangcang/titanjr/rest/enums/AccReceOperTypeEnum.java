/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AccReceOperTypeEnum.java
 * @author Jerry
 * @date 2017年8月14日 下午2:23:24  
 */
package com.fangcang.titanjr.rest.enums;

/**
 * @author Jerry
 * @date 2017年8月14日 下午2:23:24  
 */
public enum AccReceOperTypeEnum {
	
	RECEIVE_ONLY(1, "直接收款"),
	RECEIVE_FREEZE(2, "收款并冻结"),
	UNRECEIVE_UNFREEZE(3, "不收款并解冻");
	
	private int key;
	private String value;
	
	private AccReceOperTypeEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	
	/**
	 * 是否存在
	 * @author Jerry
	 * @date 2017年8月14日 下午4:43:20
	 */
	public static boolean isExist(int key){
		
		for (AccReceOperTypeEnum e : AccReceOperTypeEnum.values()) {
			
			if(e.key == key){
				return true;
			}
			
		}
		
		return false;
		
	}
	
	
	/**
	 * 校验是否收款和操作方式是否匹配
	 * @author Jerry
	 * @date 2017年8月14日 下午4:43:33
	 */
	public static boolean checkAccReceOperType(boolean isReceive, int accReceOperType){
		
		if(isReceive){
			
			if(accReceOperType == AccReceOperTypeEnum.UNRECEIVE_UNFREEZE.key){
				return false;
			}
			
		}else{
			
			if(accReceOperType != AccReceOperTypeEnum.UNRECEIVE_UNFREEZE.key){
				return false;
			}
			
		}
		
		return true;
		
	}
	

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
