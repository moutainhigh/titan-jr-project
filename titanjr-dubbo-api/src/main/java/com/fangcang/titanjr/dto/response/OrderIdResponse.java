package com.fangcang.titanjr.dto.response;

public class OrderIdResponse {
    
	private String orderNo;
	
	//是生成新的订单号还是在数据库查询出的订单号
	private String isNewOrOld;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIsNewOrOld() {
		return isNewOrOld;
	}

	public void setIsNewOrOld(String isNewOrOld) {
		this.isNewOrOld = isNewOrOld;
	}
	
}
