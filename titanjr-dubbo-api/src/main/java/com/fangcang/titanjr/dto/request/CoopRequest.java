package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class CoopRequest extends BaseRequestDTO {
	
	private Integer coopType;
	
	private String mixcode;
	
	
	public Integer getCoopType() {
		return coopType;
	}
	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
	public String getMixcode() {
		return mixcode;
	}
	public void setMixcode(String mixcode) {
		this.mixcode = mixcode;
	}
	
}
