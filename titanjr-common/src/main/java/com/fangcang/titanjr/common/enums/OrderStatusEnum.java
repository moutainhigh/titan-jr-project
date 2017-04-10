package com.fangcang.titanjr.common.enums;

public enum OrderStatusEnum {
//	Status_1("1","处理中"), Status_2("2","交易成功"),
//    Status_3("3","已冻结"), Status_4("4","付款失败"),Status_5("5","失效");
	ORDER_IN_PROCESS("0","处理中"),
	RECHARGE_SUCCESS("1","充值成功"),RECHARGE_FAIL("2","充值失败"),RECHARGE_IN_PROCESS("3","充值处理中"),
	TRANSFER_SUCCESS("4","转账成功"),TRANSFER_FAIL("5","转账失败"),
	FREEZE_SUCCESS("6","冻结成功"),FREEZE_FAIL("7","冻结失败"),
	ORDER_SUCCESS("8","成功"),ORDER_FAIL("9","失败"),
	ORDER_NO_EFFECT("10","订单失效"),
	ORDER_DELAY("11","支付延迟到账"),
	REFUND_IN_PROCESS("12","退款中"),
	REFUND_SUCCESS("13","退款成功"),
	LOAN_ING("15","贷款处理中"),
	REFUND_FAIL("14","退款失败");
  
    private String status;
    
    private String statusMsg;

    private OrderStatusEnum(String status,String statusMsg){
        this.status = status;
        this.statusMsg = statusMsg;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public static String getStatusMsgByKey(String key){
		for (OrderStatusEnum statusEnum : OrderStatusEnum.values()){
			if (statusEnum.status.equals(key)){
				return statusEnum.statusMsg;
			}
		}
		return null;
	}

	public static boolean isRepeatedPay(String status){
		if(status.equals(OrderStatusEnum.ORDER_IN_PROCESS.getStatus()) 
				||status.equals(OrderStatusEnum.RECHARGE_IN_PROCESS.getStatus())
				||status.equals(OrderStatusEnum.RECHARGE_FAIL.getStatus())
				||status.equals(OrderStatusEnum.ORDER_FAIL.getStatus())
				||status.equals(OrderStatusEnum.TRANSFER_FAIL.getStatus())
				||status.equals(OrderStatusEnum.ORDER_NO_EFFECT.getStatus())
				){
			return true;
		}
        return false;
	}
	
	public static boolean isPaySuccess(String status){
		if(status.equals(OrderStatusEnum.TRANSFER_SUCCESS.getStatus())
				||status.equals(OrderStatusEnum.FREEZE_SUCCESS.getStatus())
				||status.equals(OrderStatusEnum.FREEZE_FAIL.getStatus())
				||status.equals(OrderStatusEnum.ORDER_SUCCESS.getStatus())
				||status.equals(OrderStatusEnum.ORDER_DELAY.getStatus())
				||status.equals(OrderStatusEnum.REFUND_IN_PROCESS.getStatus())
				||status.equals(OrderStatusEnum.REFUND_SUCCESS.getStatus())
				||status.equals(OrderStatusEnum.REFUND_FAIL.getStatus())){
			return true;
		}
		return false;
	}
	
	public static boolean isRefund(String status){
		if(status.equals(OrderStatusEnum.REFUND_IN_PROCESS.status)
				|| status.equals(REFUND_SUCCESS.REFUND_SUCCESS.status)){
			return true;
		}
		return false;
	}
}
