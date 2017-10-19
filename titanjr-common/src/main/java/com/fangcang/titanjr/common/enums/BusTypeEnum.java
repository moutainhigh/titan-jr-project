package com.fangcang.titanjr.common.enums;

import com.fangcang.util.StringUtil;

public enum BusTypeEnum {

	B2B_RATE(3, "企业网银","1"), B2C_RATE(4, "个人网银","2"), CREDIT_RATE(5, "信用卡","3"), QR_RATE(6, "第三方支付","9"),
	WX_PUBLIC(8, "微信公众号支付","8"),WITHDRAW_RATE(7,"提现手续费","0"),QUICKPAY_RATE(9,"快捷支付","11"),BALANCE_RATE(10,"余额支付","4");
	
	public int type;
	
	public String msg;
	
	public String cashierItemType;
	
	private BusTypeEnum(int type,String msg,String cashierItemType){
		this.type=type;
		this.msg=msg;
		this.cashierItemType= cashierItemType;
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
	
	public boolean isWxPublic(){
		return this.type==WX_PUBLIC.type;
	}
	
	public static Integer getBusTypeByItemType(String cashierItemType){
		if(!StringUtil.isValidString(cashierItemType)){
			return null;
		}
		
		for(BusTypeEnum bus :BusTypeEnum.values()){
			if(bus.cashierItemType.equals(cashierItemType)){
				return bus.type;
			}
		}
		return null;
	}
}
