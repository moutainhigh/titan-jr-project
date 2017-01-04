package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class GetCreditOrderCountRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1657073458692339520L;
	/**
	 * 状态
	 */
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
