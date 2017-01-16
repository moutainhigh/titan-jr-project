package com.fangcang.titanjr.entity;


public class LoanExpiryStat {
	// 逾期筆數
	private int expiryNum;
	// 逾期本金金額
	private int expiryAmount;
	
	
	public int getExpiryNum() {
		return expiryNum;
	}
	public void setExpiryNum(int expiryNum) {
		this.expiryNum = expiryNum;
	}
	public int getExpiryAmount() {
		return expiryAmount;
	}
	public void setExpiryAmount(int expiryAmount) {
		this.expiryAmount = expiryAmount;
	}
}
