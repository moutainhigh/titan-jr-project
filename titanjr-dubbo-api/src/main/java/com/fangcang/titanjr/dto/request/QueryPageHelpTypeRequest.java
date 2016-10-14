package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class QueryPageHelpTypeRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7687986833804630127L;
	private Integer helpType;
	private Integer isShow = 1;
	
	public Integer getHelpType() {
		return helpType;
	}
	public void setHelpType(Integer helpType) {
		this.helpType = helpType;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
}
