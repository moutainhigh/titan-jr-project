package com.fangcang.titanjr.entity;

public class LoanSevenDaysStat 
{
	// 七天還款筆數
	private int sevenDaysNum;
	// 七天待還款金額
	private long sevenDaysAmount;
	
	public int getSevenDaysNum() {
		return sevenDaysNum;
	}
	public void setSevenDaysNum(int sevenDaysNum) {
		this.sevenDaysNum = sevenDaysNum;
	}
	public long getSevenDaysAmount() {
		return sevenDaysAmount;
	}
	public void setSevenDaysAmount(long sevenDaysAmount) {
		this.sevenDaysAmount = sevenDaysAmount;
	}
}
