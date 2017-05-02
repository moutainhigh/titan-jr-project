package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

import com.fangcang.titanjr.common.enums.BusinessLog;
import com.fangcang.titanjr.common.enums.BusinessLog.PayStep;
import com.fangcang.titanjr.common.enums.OrderKindEnum;

public class AddPayLogRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5876081923420165557L;
	private BusinessLog.PayStep payStep;
	private OrderKindEnum orEnum;
	private String orderId;
	private String extraInfo;
	
	public AddPayLogRequest(PayStep payStep, OrderKindEnum orEnum,
			String orderId) {
		super();
		this.payStep = payStep;
		this.orEnum = orEnum;
		this.orderId = orderId;
	}
	
	public AddPayLogRequest(PayStep payStep, OrderKindEnum orEnum,
			String orderId, String extraInfo) {
		super();
		this.payStep = payStep;
		this.orEnum = orEnum;
		this.orderId = orderId;
		this.extraInfo = extraInfo;
	}

	public BusinessLog.PayStep getPayStep() {
		return payStep;
	}

	public void setPayStep(BusinessLog.PayStep payStep) {
		this.payStep = payStep;
	}

	public OrderKindEnum getOrEnum() {
		return orEnum;
	}

	public void setOrEnum(OrderKindEnum orEnum) {
		this.orEnum = orEnum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	
}
