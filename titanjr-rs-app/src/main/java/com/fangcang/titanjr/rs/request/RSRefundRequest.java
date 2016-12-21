package com.fangcang.titanjr.rs.request;

import java.io.Serializable;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

public class RSRefundRequest extends BaseRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderid;
	
	private String orderitemid;
	
	private String amount;
	
	private String userorderid;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(String orderitemid) {
		this.orderitemid = orderitemid;
	}

	public String getUserorderid() {
		return userorderid;
	}

	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public void check() throws RSValidateException {
		  RequestValidationUtil.checkNotEmpty(this.getOrderid(), "orderid");
	      RequestValidationUtil.checkNotEmpty(this.getAmount(), "amount");		
	}
	
}
