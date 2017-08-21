package com.fangcang.titanjr.common.enums;

/**
 * 用户注册或登录来源
 * @author luoqinglong
 * @2016年6月3日
 */
public enum CoopTypeEnum {
	
	TFS(1,"TFS,暂无用"),SAAS(2,"SAAS"),AUTO(3,"后台自动,暂无用"),TTM(4,"TTM"),TWS(5,"泰坦钱包"),SAAS_INTERFACE(6,"SaaS无审核接口注册"),TTM_GONGYING_INTERFACE(7,"TTM供应商无审核接口注册"),TTM_FENXIAO_INTERFACE(8,"TTM分销商无审核接口注册");
	
	/**
	 * 注册渠道
	 */
	private int key;
	
	private String des; 
	
	private CoopTypeEnum(int key,String des){
		this.key = key;
		this.des = des;
	}
	public static CoopTypeEnum  getEnumByKey(int key){
		CoopTypeEnum entity = null;
		for(CoopTypeEnum item : CoopTypeEnum.values()) {
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
