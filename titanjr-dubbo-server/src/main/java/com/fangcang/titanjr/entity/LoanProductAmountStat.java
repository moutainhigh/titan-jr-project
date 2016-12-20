package com.fangcang.titanjr.entity;

public class LoanProductAmountStat {
	private int productId;
	
	//本金
	private long amount;
	
	//本金+利息
	private long actualAmount;
	
	public long getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(long actualAmount) {
		this.actualAmount = actualAmount;
	}


	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
