package com.fangcang.titanjr.pay.req;

import com.fangcang.titanjr.common.enums.CashierItemTypeEnum;

public class TitanRateComputeReq 
{
	private String userId;
	private String amount;
	private CashierItemTypeEnum itemTypeEnum;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public CashierItemTypeEnum getItemTypeEnum() {
		return itemTypeEnum;
	}
	public void setItemTypeEnum(CashierItemTypeEnum itemTypeEnum) {
		this.itemTypeEnum = itemTypeEnum;
	}
}
