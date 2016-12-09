package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.fangcang.titanjr.rs.dto.Transorderinfo;


public class OrdernQueryResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Transorderinfo> transorderinfo;

	public List<Transorderinfo> getTransorderinfo() {
		return transorderinfo;
	}

	public void setTransorderinfo(List<Transorderinfo> transorderinfo) {
		this.transorderinfo = transorderinfo;
	}
	
}
