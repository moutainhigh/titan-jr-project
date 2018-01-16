package com.fangcang.titanjr.enums;

/***
 * 记账交易类型
 * @author luoqinglong
 * @date 2018年1月15日
 */
public enum TradeTypeAccountDetailEnum {
	RECHARGE(1,"充值"),TRANSFER(2,"转账"),FREEZEE(3,"冻结"),UNFREEZEE(4,"解冻"),WITHDRAW(5,"提现"),REFUND(6,"退款");
	
	private int tradeType;
	private String des;
	
	private TradeTypeAccountDetailEnum(int tradeType,String des){
		this.tradeType = tradeType;
		this.des = des;
	}
	
	public int getTradeType() {
		return tradeType;
	}

	public String getDes() {
		return des;
	}
	
}
