package com.fangcang.titanjr.common.enums;

/**
 * 注册或登录来源
 * @author luoqinglong
 * @2016年6月3日
 */
public enum LoginSourceEnum {
	TFS(1,"金融官网"),SAAS(2,"SAAS"),AUTO(3,"后台自动");
	
	private int key;
	private String des; 
	
	private LoginSourceEnum(int key,String des){
		this.key = key;
		this.des = des;
	}
	public static LoginSourceEnum  getEnumByKey(int key){
		LoginSourceEnum entity = null;
		for(LoginSourceEnum item : LoginSourceEnum.values()) {
			if(item.key == key) {
				entity = item;
				break;
			}
		}
		return entity;
	}
	public int getKey() {
		return key;
	}
	public String getDes() {
		return des;
	}
	
}
