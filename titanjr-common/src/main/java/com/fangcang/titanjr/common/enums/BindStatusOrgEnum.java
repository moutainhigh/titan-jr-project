package com.fangcang.titanjr.common.enums;


/**
 * 机构绑定状态
 * @author luoqinglong
 *
 */
public enum BindStatusOrgEnum {
	UNBING(0,"取消绑定"),BING(1,"已绑定");
	private int bindStatus;
	private String des;
	
	private BindStatusOrgEnum(int bindStatus,String des){
		this.bindStatus = bindStatus;
		this.des = des;
	}

	public static BindStatusOrgEnum  getEnumByFileType(int bindStatus){
		BindStatusOrgEnum entity = null;
		for(BindStatusOrgEnum item : BindStatusOrgEnum.values()) {
			if(item.getBindStatus() == bindStatus) {
				entity = item;
				break;
			}
		}
		return entity;
	}

	public int getBindStatus() {
		return bindStatus;
	}

	public String getDes() {
		return des;
	}
	
	
}
