/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanjrVersionEnum.java
 * @author Jerry
 * @date 2017年8月3日 下午2:07:16  
 */
package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

/**
 * 泰坦金融版本号
 * @author Jerry
 * @date 2017年8月3日 下午2:07:16  
 */
public enum TitanjrVersionEnum {
	
	VERSION_1("v1.0", "金融一期"),//老版本
	VERSION_2("v1.1", "金融二期");//支持新版收银台，快捷支付，账户升级
	
	private String key;
	private String value;
	
	private TitanjrVersionEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 检查传入的key是否在枚举中
	 * @author Jerry
	 * @date 2017年8月3日 下午2:58:53
	 * @param key
	 * @return
	 */
	public static boolean isExist(String key){
		
		if(!StringUtil.isValidString(key)){
			return false;
		}
		
		for (TitanjrVersionEnum e : TitanjrVersionEnum.values()) {
			
			if(e.getKey().equals(key)){
				
				return true;
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * 金融一期
	 * @author Jerry
	 * @date 2017年8月4日 下午2:30:38
	 * @param key
	 * @return
	 */
	public static boolean isVersion1(String key){
		
		if(!StringUtil.isValidString(key)){
			return false;
		}
		
		if(TitanjrVersionEnum.VERSION_1.getKey().equals(key)){
			
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * 金融二期（快捷支付，账户升级，新版收银台）
	 * @author Jerry
	 * @date 2017年8月4日 下午2:30:48
	 * @param key
	 * @return
	 */
	public static boolean isVersion2(String key){
		
		if(!StringUtil.isValidString(key)){
			return false;
		}
		
		if(TitanjrVersionEnum.VERSION_2.getKey().equals(key)){
			
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
