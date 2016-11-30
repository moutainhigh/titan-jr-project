package com.fangcang.titanjr.common.enums;
/**
 * 授信单审核结果
 * @author wengxitao
 */
public enum AuditResultEnum {
	PASS(1, "通过"), NO_PASS(2, "不通过");

	int status;
	String desc;

	AuditResultEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	public static AuditResultEnum  getEnumByStatus(int status){
		AuditResultEnum entity = null;
		for(AuditResultEnum item : AuditResultEnum.values()) {
			if(item.getStatus() == status) {
				entity = item;
				break;
			}
		}
		return entity;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
