package com.fangcang.titanjr.common.enums;

public enum OrderExceptionEnum {
	TransOrder_Insert("00"," 落单新增失败"),TransOrder_update("01","落单修改失败"),
	OrderPay_Insert("10","充值新增失败"),OrderPay_Update("11","充值修改失败 "),
	Transfer_Insert("20","转账新增失败"),Transfer_Update("21","转账修改失败"),
	Freeze_Insert("31","冻结新增失败 "),
	Finance_Confirm("41","转账后回调失败 "),NOTIFY_FAILED("42","回调失败"),
	UNFREEZE_INSERT("50","解冻资金失败"),
	UNFREEZE_RECORD_INSERT("60","解冻资金记录插入失败"),REFUND_FREEZE_AGAIN("61","重新冻结失败"),REFUND_FREEZE_UPDATE("62","重新冻结修改订单状态失败"),
	REFUND_UPDATE_TRANSFER("70","更新退款转账失败"),REFUND_UPDATE_TRANSORDER("71","退款请求完成更新本地单失败"),
	REFUND_INSERT("80","退款单落单保存失败"),REFUND_UPDATE_ORDER("81","退款调用网关失败失败"),REFUND_NOTIFY_FAILED("82","退款回调失败");
	
	
	public String code;
	
	public String msg;
	
	
	
	
	private OrderExceptionEnum(String code,String msg){
		this.code=code;
		this.msg = msg;
	}
	
}
