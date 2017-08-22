package com.fangcang.titanjr.common.enums;


/**
 * 合作方(系统方)
 * @author luoqinglong
 * @2016年6月3日
 */
public enum CoopTypeEnum {
	
	SAAS(2,"SAAS") ,TTM(4,"TTM"),TWS(5,"泰坦钱包");
	
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
	/**
	 * 来源与合作方转换
	 * @param registerSourceEnum
	 * @return
	 */
	public CoopTypeEnum getCoopTypeEnum(RegSourceEnum registerSourceEnum){
		switch (registerSourceEnum) {
		case SAAS:
		case SAAS_INTERFACE:
			return  CoopTypeEnum.SAAS;
		case TTM:
		case TTM_GONGYINGSHANG_INTERFACE:
		case TTM_FENXIAOSHANG_INTERFACE:
			return  CoopTypeEnum.TTM;
		default:
			return  CoopTypeEnum.TWS;
		}
	}
	
	
}

