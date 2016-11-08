package com.fangcang.titanjr.common.enums;

public enum BusTypeEnum {

	B2B_RATE(3, "企业网银"), B2C_RATE(4, "个人网银"), CREDIT_RATE(5, "信用卡"), QR_RATE(6, "第三方支付"),WITHDRAW_RATE(7,"提现手续费");
	
	public int type;
	
	public String msg;
	
	private BusTypeEnum(int type,String msg){
		this.type=type;
		this.msg=msg;
	}
	
	public boolean isB2B(){
		return this.type==B2B_RATE.type;
	}
	
	public boolean isB2C(){
		return this.type==B2C_RATE.type;
	}
	
	public boolean isCREDIT(){
		return this.type==CREDIT_RATE.type;
	}
	
	public boolean isQR(){
		return this.type==QR_RATE.type;
	}
	
	public boolean isWITHDRAW(){
		return this.type==WITHDRAW_RATE.type;
	}
	
}
