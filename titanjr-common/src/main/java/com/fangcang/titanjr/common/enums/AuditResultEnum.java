package com.fangcang.titanjr.common.enums;
/**
 * 授信单审核结果
 * @author wengxitao
 */
public enum AuditResultEnum {
	PASS(1, "通过"), NO_PASS(1, "通过");

	int status;
	String desc;

	AuditResultEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}
}
