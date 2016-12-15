package com.fangcang.titanjr.common.enums;

public enum LoanOrderStatusEnum {
	// 0 无效贷款  1 贷款申请中  2 待终审 3 终审通过 4 终审失败
	//5 待放款   6 已放款  7 放款失败  8 贷款失败  9 已逾期 10 已结清 11 已撤销

	INVALID(0, "无效贷款"),

	LOAN_REQ_ING(1, "贷款申请中"),
	
	WAIT_AUDIT(2, "待终审"),
	
	AUDIT_PASS(3 , "终审通过"),
	
	AUDIT_FIAL(4 , "终审失败"),

	LENDING_ING(5, "等待放款"),

	HAVE_LOAN(6, " 已放款"),

	LENDING_FAIL(7, "放款失敗"),

	LOAN_FAIL(8, "贷款失敗"),

	LOAN_EXPIRY(9, "已逾期"),

	LOAN_FINISH(10, "已結清"),
	
	LOAN_UNDO(11, "已撤销");

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
