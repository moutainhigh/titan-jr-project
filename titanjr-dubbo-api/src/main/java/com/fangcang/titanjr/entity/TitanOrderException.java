package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 异常单
 * @author fangdaikang
 *
 */

public class TitanOrderException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer id;

	// 异常单号
	private String orderId;

	// 错误信息
	private String exceptionMsg;

	// 错误类型
	private String type;

	// 异常时间
	private Date exceptionTime;

	// 异常内容
	private String exceptionContent;
	
	//失败 0 中间过程 1
	private String failState;
	
	//orderId:1 userOrderId :2 payOrderNo:3
	private String orderType;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public String getExceptionContent() {
		return exceptionContent;
	}

	public void setExceptionContent(String exceptionContent) {
		this.exceptionContent = exceptionContent;
	}

	public String getFailState() {
		return failState;
	}

	public void setFailState(String failState) {
		this.failState = failState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	

}
