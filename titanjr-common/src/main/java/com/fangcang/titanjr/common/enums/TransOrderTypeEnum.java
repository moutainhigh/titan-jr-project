package com.fangcang.titanjr.common.enums;

public enum TransOrderTypeEnum {
	
	RECHARGE(1,"充值"),WITHDRAW(2,"提现"),PAYMENT(3,"支付");
	
	public Integer type;
	
	public String msg;
	
	private TransOrderTypeEnum(Integer type,String msg){
		this.type=type;
		this.msg=msg;
	}

}
