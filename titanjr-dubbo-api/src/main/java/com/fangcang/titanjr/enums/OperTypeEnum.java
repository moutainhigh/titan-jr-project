package com.fangcang.titanjr.enums;
/**
 * 订单操作类型 （修改：2,新增：1,取消4,查询3）
 * @author fangdaikang,
 *
 */
public enum OperTypeEnum {
	 Add_Order("1","新增"), Update_Order("2","修改"), Cancel_Order("4","取消"), Query_Order("3","查询"),;
		
	public String key;
	
	public String value;
	
	private OperTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
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
