package com.fangcang.titanjr.common.enums;

/**
 * 贷款产品枚举定义
 * 
 * @author wengxitao
 *
 */
public enum LoanProductEnum {
	ROOM_PACK(10001, "包房贷"), OPERACTION(10002, "运营贷");

	int code;
	String desc;

	LoanProductEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static LoanProductEnum getEnumByKey(int key) {
		LoanProductEnum entity = null;
		for (LoanProductEnum item : LoanProductEnum.values()) {
			if (item.code == key) {
				entity = item;
				break;
			}
		}
		return entity;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
