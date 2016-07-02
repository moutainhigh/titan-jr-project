package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class AccountBalanceRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//机构标识
	@NotNull
	private String userid;
	
	//和constid一样
	@NotNull
	private String rootinstcd;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRootinstcd() {
		return rootinstcd;
	}

	public void setRootinstcd(String rootinstcd) {
		this.rootinstcd = rootinstcd;
	}
	
}
