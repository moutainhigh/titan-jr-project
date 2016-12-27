package com.fangcang.titanjr.common.bean;

import java.io.Serializable;

public class NotifyBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//单号
	private String payOrderNo;
	
	//状0:退款中,2:退款成功,其他状态为退款失败;

	private String code;
	
	//回调地址
	private String notifyUrl;
	
	//金融的单号
	private String userOrderId;
	
	//扩展字段
	private String expend;

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}

	public String getExpend() {
		return expend;
	}

	public void setExpend(String expend) {
		this.expend = expend;
	}
	
}
