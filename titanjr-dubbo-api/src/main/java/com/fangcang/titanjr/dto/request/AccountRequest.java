package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 
 * @author luoqinglong
 * @2016年7月4日
 */
public class AccountRequest extends BaseRequestDTO{
    private String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
    
}
