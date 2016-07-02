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
	private String exceptionCode;

	// 异常时间
	private Date exceptionTime;

	// 异常内容
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
