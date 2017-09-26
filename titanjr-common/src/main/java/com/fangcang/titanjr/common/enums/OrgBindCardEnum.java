package com.fangcang.titanjr.common.enums;

public enum OrgBindCardEnum {
	INVALID("0", "失效/审核失败"),
	NORMAL("1", "正常"), 
	CHECKING("2", "审核中"),
	NO_ORGSUB("10","未关联真实机构"),
	NO_BANKCARD("20","未绑卡"),
	
	BANKCARDSTATUS_EXCEPTION("99","绑卡状态不存在");
	
	private String status;
	private String des;
	
	private OrgBindCardEnum(String status,String des){
		this.status = status;
		this.des = des;
	}

	public String getStatus() {
		return status;
	}

	public String getDes() {
		return des;
	}
	
	
	
}
