package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

import com.fangcang.titanjr.common.enums.OrderExceptionEnum;

public class OrderExceptionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderExceptionDTO(){}	
	
    public OrderExceptionDTO(String orderId,String exceptionMsg,OrderExceptionEnum exceptionCode,String exceptionContent){
		this.orderId = orderId;
		this.exceptionMsg = exceptionMsg;
		this.exceptionContent =exceptionContent;
		this.exceptionTime=new Date();
	}
	
	private Integer id;

	// 异常单号
	private String orderId;

	// 错误信息
	private String exceptionMsg;

	// 错误类型
	private String exceptionCode;

	// 异常时间
	private Date exceptionTime;

	// 异常时间
	private String exceptionContent;
	
	// 更新时间
	private Date updateTime;
	
	//失败- 0 ;成功-1
	private String failState;

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

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFailState() {
		return failState;
	}

	public void setFailState(String failState) {
		this.failState = failState;
	}
	
}
