package com.fangcang.titanjr.entity.parameter;
// default package

import com.fangcang.titanjr.entity.TitanRateConfig;

/**
 * TitanRateChange entity. @author MyEclipse Persistence Tools
 */

public class TitanRateConfigParam extends TitanRateConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1001993757818329077L;
	private int cashierItemType;


	public int getCashierItemType() {
		return cashierItemType;
	}


	public void setCashierItemType(int cashierItemType) {
		this.cashierItemType = cashierItemType;
	}	
}