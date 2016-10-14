package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class QueryPageHelpWordRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5994230612835833404L;
	/**
	 * 搜索关键词
	 */
	private String word;

	private Integer isShow = 1;
	
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
}
