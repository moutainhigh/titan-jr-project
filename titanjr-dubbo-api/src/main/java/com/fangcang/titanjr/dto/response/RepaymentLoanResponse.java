package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class RepaymentLoanResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderNo;

	private long amount; // 本金

	private long interest; // 利息

	private long forfeit;// 罚金

	private long forfeitInterest; // 罚金利息

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getInterest() {
		return interest;
	}

	public void setInterest(long interest) {
		this.interest = interest;
	}

	public long getForfeit() {
		return forfeit;
	}

	public void setForfeit(long forfeit) {
		this.forfeit = forfeit;
	}

	public long getForfeitInterest() {
		return forfeitInterest;
	}

	public void setForfeitInterest(long forfeitInterest) {
		this.forfeitInterest = forfeitInterest;
	}

}
