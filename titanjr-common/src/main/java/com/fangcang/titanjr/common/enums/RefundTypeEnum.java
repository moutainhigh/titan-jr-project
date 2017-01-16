package com.fangcang.titanjr.common.enums;

public enum RefundTypeEnum {

	REFUND_BANKCARD("0","退款到银行"),REFUND_ACCOUNT("1","退款到账户"),REFUND_BANKCARD_ACCOUNT("2","退款到银行和账户");
	
	public String type;
	
	public String msg;
	
	private RefundTypeEnum(String type,String msg){
		this.type = type;
		this.msg =msg;
	}
}
