package com.fangcang.titanjr.common.enums;

public enum RegchannelEnum {

	OFFIAIAL_WEBSITE(1,"金融官网"),SASA(2,"SAAS注册"),TTM(4,"TTM");
	
	
	public Integer source;
	
	public String msg;
	
	private RegchannelEnum(Integer source,String msg){
		this.source=source;
		this.msg = msg;
	}
}
