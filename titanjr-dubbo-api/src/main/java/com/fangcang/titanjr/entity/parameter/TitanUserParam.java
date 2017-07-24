package com.fangcang.titanjr.entity.parameter;
// default package

import com.fangcang.titanjr.entity.TitanUser;

/**
 * TitanUser entity. @author MyEclipse Persistence Tools
 */

public class TitanUserParam extends TitanUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1411172859476328630L;
	/***
	 * 条件
	 */
	private String clauseMerchantCode;
	
	public String getClauseMerchantCode() {
		return clauseMerchantCode;
	}
	public void setClauseMerchantCode(String clauseMerchantCode) {
		this.clauseMerchantCode = clauseMerchantCode;
	}
	
	
	
}