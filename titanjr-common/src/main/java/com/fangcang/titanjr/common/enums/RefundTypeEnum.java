package com.fangcang.titanjr.common.enums;

public enum RefundTypeEnum {

	REFUND_BANKCARD("0","~{MK?n5=RxPP?(~}"),REFUND_ACCOUNT("1","~{MK?n5=UK;'~}"),REFUND_BANKCARD_ACCOUNT("2","~{MK?n5=UK;':MRxPP?(~}");
	
	public String type;
	
	public String msg;
	
	private RefundTypeEnum(String type,String msg){
		this.type = type;
		this.msg =msg;
	}
}
