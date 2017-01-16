package com.fangcang.titanjr.dto.response;

import java.io.Serializable;

public class GetCreditOrderCountResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9145814188621072634L;
	private int count ;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
