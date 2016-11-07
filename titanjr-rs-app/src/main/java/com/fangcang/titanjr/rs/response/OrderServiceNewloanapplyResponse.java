package com.fangcang.titanjr.rs.response;

/**
 * 个人贷款申请
 * @author luoqinglong
 * @2016年10月31日
 */
public class OrderServiceNewloanapplyResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929658149188973025L;
	/**
	 * 	申请成功后返回的订单id
	 */
	private String orderid;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
}
