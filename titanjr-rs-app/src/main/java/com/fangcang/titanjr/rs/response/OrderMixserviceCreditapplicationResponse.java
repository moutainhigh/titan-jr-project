package com.fangcang.titanjr.rs.response;

/**
 * 授信申请
 * @author luoqinglong
 * @2016年10月31日
 */
public class OrderMixserviceCreditapplicationResponse extends BaseResponse{

	private static final long serialVersionUID = 4435695758834392010L;
	/**
	 * 订单id
	 */
	private String orderid ;
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	
}
