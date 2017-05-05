package com.fangcang.titanjr.entity.parameter;

import java.io.Serializable;
import java.util.Date;

public class TitanOrderExceptionParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5743560349884726632L;

	private Integer id;

	// 异常单号
	private String orderId;
	
	// 更新时间
	private Date beginUpdateTime;
	
	private String orderNo;
	
	private Date endUpdateTime;
	
	//'第一次失败-0；成功-1；第二次失败-2；第三次失败-3；第四次失败-4'
	private String beginFailState;
	
	private String endFailState;
	private String failState;
	// 错误类型
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBeginUpdateTime() {
		return beginUpdateTime;
	}

	public void setBeginUpdateTime(Date beginUpdateTime) {
		this.beginUpdateTime = beginUpdateTime;
	}

	public Date getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setEndUpdateTime(Date endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}

	public String getBeginFailState() {
		return beginFailState;
	}

	public void setBeginFailState(String beginFailState) {
		this.beginFailState = beginFailState;
	}

	public String getEndFailState() {
		return endFailState;
	}

	public void setEndFailState(String endFailState) {
		this.endFailState = endFailState;
	}

	public String getFailState() {
		return failState;
	}

	public void setFailState(String failState) {
		this.failState = failState;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
