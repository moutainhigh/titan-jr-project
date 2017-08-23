package com.fangcang.titanjr.common.enums;

/**
 * 注册来源，用于统计,一个合作方可能有多个来源
 * @author luoqinglong
 * @date 2017年8月21日
 */
public enum RegSourceEnum {
	SAAS(2,"SAAS普通页面"),TTM(4,"TTM普通页面"),TWS(5,"泰坦钱包页面注册"),SAAS_INTERFACE(6,"SaaS无审核接口注册"),TTM_GONGYINGSHANG_INTERFACE(7,"TTM供应商无审核接口注册"),TTM_FENXIAOSHANG_INTERFACE(8,"TTM分销商无审核接口注册");
	private int type;
	private String des;
	
	private RegSourceEnum(int type,String des){
		this.type = type;
		this.des = des;
	}
	
	
	public int getType() {
		return type;
	}


	public String getDes() {
		return des;
	}

	public static RegSourceEnum  getEnumByKey(int type){
		RegSourceEnum entity = null;
		for(RegSourceEnum item : RegSourceEnum.values()) {
			if(item.type == type) {
				entity = item;
				break;
			}
		}
		return entity;
	}
	/**
	 * 是否为接口注册，接口注册，无需审核，自动通过
	 * @param coopType
	 * @return
	 */
	public static boolean isInterfaceReg(int registerSource){
		return registerSource==SAAS_INTERFACE.getType()||registerSource==TTM_GONGYINGSHANG_INTERFACE.getType()||registerSource==TTM_GONGYINGSHANG_INTERFACE.getType();
	}
}
