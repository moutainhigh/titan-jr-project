package com.fangcang.titanjr.dto.request;

import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class PaymentItemRequest<T> extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	private T item;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

}
