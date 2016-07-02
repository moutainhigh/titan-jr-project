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
		this.exceptionCode=exceptionCode.code;
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

}
