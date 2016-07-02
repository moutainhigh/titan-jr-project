package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class GDPOrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal orderSum;
	
	private String currency;
	
	private String hotelName;
	
	private String goodName;
	
	private String orderCode;

	public BigDecimal getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(BigDecimal orderSum) {
		this.orderSum = orderSum;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}
