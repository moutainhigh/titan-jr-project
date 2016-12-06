package com.fangcang.titanjr.common.enums;

public enum LoanOrderStatusEnum {
	// 0 无效贷款 1 贷款申请中 2 待放款 3 已放款 4 放款失败 5 贷款失败 6 已逾期 7 已结清

	INVALID(0, "无效贷款"),

	LOAN_REQ_ING(1, "贷款申请中"),

	LENDING_ING(2, " 待放款"),

	HAVE_LOAN(3, " 已放款"),

	LENDING_FAIL(4, "放款失敗"),

	LOAN_FAIL(5, "放款失敗"),

	LOAN_EXPIRY(6, "已逾期"),

	LOAN_FINISH(7, "已結清");

	public int getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}

	int key;

	String desc;
	
	public static LoanOrderStatusEnum  getEnumByStatus(int status){
		LoanOrderStatusEnum entity = null;
		for(LoanOrderStatusEnum item : LoanOrderStatusEnum.values()) {
			if(item.key == status) {
				entity = item;
				break;
			}
		}
		return entity;
	}

	LoanOrderStatusEnum(int key, String desc) {
		this.key = key;
		this.desc = desc;
	}

}
