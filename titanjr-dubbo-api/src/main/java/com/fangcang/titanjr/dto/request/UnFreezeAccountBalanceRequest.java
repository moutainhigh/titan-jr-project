package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class UnFreezeAccountBalanceRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//冻结资金时的请求号
	private String orderNo;
	
	private String requesttime ;
	
	private String userid;
	
	private Integer tfsuserid;
	
	private String requestno;
	
	public String getRequestno() {
		return requestno;
	}

	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}

	public Integer getTfsuserid() {
		return tfsuserid;
	}

	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

}
