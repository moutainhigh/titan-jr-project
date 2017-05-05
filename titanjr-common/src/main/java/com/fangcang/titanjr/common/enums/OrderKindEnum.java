package com.fangcang.titanjr.common.enums;

public enum OrderKindEnum {

	OrderId("1","融数返回的订单号"),UserOrderId("2","本系统生成的订单号"),PayOrderNo("3","外部系统订单号"),TransOrderId("99","id");
	
	private String type;
	
	private String msg;
	
	private OrderKindEnum(String type,String msg){
		this.type=type;
		this.msg=msg;
	}

	public String getType() {
		return type;
	}

	public String getMsg() {
		return msg;
	}
	
}
