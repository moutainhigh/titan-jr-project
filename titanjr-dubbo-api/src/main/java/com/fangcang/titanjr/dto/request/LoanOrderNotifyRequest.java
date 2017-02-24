package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 贷款订单申请通知
 * @author luoqinglong
 * @date   2016年12月20日
 */
public class LoanOrderNotifyRequest extends BaseRequestDTO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2587072180426802313L;

	private String orderNo;
	
	private int state;
	
	private String msg;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
