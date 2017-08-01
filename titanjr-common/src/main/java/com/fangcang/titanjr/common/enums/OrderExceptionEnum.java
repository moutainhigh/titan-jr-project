package com.fangcang.titanjr.common.enums;

/**
 * 异常单记录枚举类
 */
public enum OrderExceptionEnum {
//	TransOrder_Insert("00"," 落单新增失败"),TransOrder_update("01","落单修改失败"),
//	OrderPay_Insert("10","充值新增失败"),OrderPay_Update("11","充值修改失败 "),
//	Transfer_Insert("20","转账新增失败"),Transfer_Update("21","转账修改失败"),
//	Freeze_Insert("31","冻结新增失败 "),
//	Finance_Confirm("41","转账后回调失败 "),NOTIFY_FAILED("42","回调失败"),
//	UNFREEZE_INSERT("50","解冻资金失败"),
//	UNFREEZE_RECORD_INSERT("60","解冻资金记录插入失败"),REFUND_FREEZE_AGAIN("61","重新冻结失败"),REFUND_FREEZE_UPDATE("62","重新冻结修改订单状态失败"),
//	REFUND_UPDATE_TRANSFER("70","更新退款转账失败"),REFUND_UPDATE_TRANSORDER("71","退款请求完成更新本地单失败"),
//	REFUND_INSERT("80","退款单落单保存失败"),REFUND_UPDATE_ORDER("81","退款调用网关失败失败"),REFUND_NOTIFY_FAILED("82","退款回调失败");
//	
	
	//落单
	Save_Order_Insert_Fail("1_1","0","落本地订单失败"),Save_Order_Get_Desk_Url_Fail("1_3","0","获取收银台地址失败"),
	
	//余额支付
	Balance_Pay_Update_Fail("3_1","0","金额未变更新本地订单失败"),Balance_Pay_Insert_Again_Fail("3_3","0","重新罗本地单失败"),
	
	Balance_Pay_Update_TransOrder_Fail("3_5","0","本地落单后更新单失败"),Balance_Pay_Freeze_Fail("3_7","0","余额支付后冻结失败"),
	
	Balance_Pay_Freeze_Update_Fail("3_9","0","余额支付冻结金额后更新订单失败"),
	
	
	//网银支付
	Online_Pay_Add_Rs_Order_Fail("5_1","0","网银支付融数落单失败"),Online_Pay_Save_Order_Fail("5_3","0","网银支付更新或保存修改本地单失败"),
	
	Online_Pay_Insert_PayOrder_Fail("5_5","0","网银支付保存充值单失败"),Online_Pay_Update_PayOrder_Fail("5_6","0","网银支付更新充值单失败"),
	
	Online_Pay_Get_Pay_Url_Fail("5_7","0","微信支付宝支付获取二维码链接失败"),

	//融数回调
	Notify_Update_PayOrder_Fail("7_1","1","充值成功修改充值单失败"),Notify_Money_Not_In_Account_Fail("7_3","1","充值金额未到账户"),
	
	Notify_Freeze_Insert_Fail("7_5","0","转账成功冻结失败"),Online_Freeze_Success_Update_Order_Fail("7_7","0","冻结成功修改订单状态失败"),
	
	Notify_Order_Amount_Execption("7_8","0","收到融数通知，订单支付金额异常"),
	
	//转账
	Transfer_Update_Order_Fail("9_1","0","新增转账单失败或修改转账单失败"),Transfer_Fail("9_3","0","转账失败"),
	
	Transfer_Success_Update_Order_Fail("9_5","0","转账成功修改转账单失败"),
	
	//回调客户端
	Notify_Client_Transfer_Notify_Fail("11_1","0","转账成功回调失败"),Notify_Client_Not_CallBack("11_3","0","转账成功回调没有响应"),
	
	Notify_Fail("11_5","0","回调客户端失败"),

	//中间账户修复
	Repair_Freeze_Order_Fail("13_1","0","修复成功后冻结订单失败"),Repair_Update_Order_Fail("13_3","0","修复成功之后修改订单状态失败"),
	
	
	//解冻
	UnFreeze_Insert_Order_Fail("15_1","0","解冻插入解冻单失败"),UnFreeze_Update_Order_Fail("15_3","0","解冻修改解冻单失败"),
	
	UnFreeze_RS_Fail("15_5","0","调用融数解冻失败"),UnFreeze_Fail("15_7","0","解冻失败"),
	
	
	//退款
	Refund_Delete_Transfer_Order_Fail("17_1","0","删除退款转账单失败"),Refund_Repair_Tranfer_Fail("17_3","0","退款修复性转账失败"),
	
	Refund_Save_Order_Fail("17_5","0","保存退款单"),Refund_Notify_RS_Fail("17_7","0","退款通知融数失败"),
	
	Refund_Update_Order_OrderNo_Fail("17_9","0","退款更新融数单号失败"),Refund_Freeze_Order_Fail("17_11","0","退款失败重新冻结订单失败"),
	
	Refund_Freeze_Update_Order_Fail("17_13","0","退款冻结金额后修改订单失败"),Refund_Timer_Update_Order("17_15","0","退款定时器更新订单失败"),
	
	ReFund_Notify_Fail("11_17","0","退款回调失败"),Refund_Success_Update_Order_Fail("17_19","0","退款成功更新订单状态失败"),
	
	Refund_RS_Fail("11_20","0","融数退款失败");
	
	public String msg;
	
	public String type;
	
	public String failState;
	
	private OrderExceptionEnum(String type,String failState, String msg){
		this.type=type;
		this.msg = msg;
		this.failState =failState;
	}

	public String getMsg() {
		return msg;
	}

	public String getType() {
		return type;
	}

	public String getFailState() {
		return failState;
	}
	
}
