package com.titanjr.fop.enums;

/***
 * 机构状态
 * @author luoqinglong
 * @date 2018年1月26日
 */
public enum StatusIdMainOrgEnum {
	NORMAL(1,"正常"),FREEZE(2,"冻结"),CANCEL(3,"注销");
	
	private Integer type;
	private String des;
	
	private StatusIdMainOrgEnum(Integer type,String des){
		this.type = type;
		this.des = des;
	}

	public Integer getType() {
		return type;
	}

	public String getDes() {
		return des;
	}
}
