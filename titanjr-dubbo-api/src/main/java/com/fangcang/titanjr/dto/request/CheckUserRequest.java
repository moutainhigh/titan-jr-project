package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class CheckUserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5129760921239796708L;
	/***
	 * 金融用户id
	 */
	private Integer tfsUserId;

	public Integer getTfsUserId() {
		return tfsUserId;
	}

	public void setTfsUserId(Integer tfsUserId) {
		this.tfsUserId = tfsUserId;
	}
	
	
}
