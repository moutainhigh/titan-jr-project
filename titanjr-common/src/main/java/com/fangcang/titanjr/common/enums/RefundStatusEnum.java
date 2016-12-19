package com.fangcang.titanjr.common.enums;

public enum RefundStatusEnum {

	REFUND_IN_PROCESS(0,"处理中"),REFUND_SUCCESS(1,"处理成功"),REFUND_FAILURE(2,"处理失败");
	
	public Integer status;
	
	public String msg;
	
	private RefundStatusEnum(Integer status,String msg ){
		this.status = status;
		this.msg = msg;
	}
	
	
	
}
