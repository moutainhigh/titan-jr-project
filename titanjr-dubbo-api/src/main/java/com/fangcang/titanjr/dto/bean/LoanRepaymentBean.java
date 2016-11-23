package com.fangcang.titanjr.dto.bean;

import java.util.Date;

public class LoanRepaymentBean 
{
	//還款單號
	private String orderNo;
	//還款日期
	private Date repaymentDate;
	//還款總金額
	private long repaymentTotalAmount;
	//還款本金
	private long repaymentAmount;
	//還款利息
	private long repaymentInterest;
	//剩餘本金
	private long remainAmount;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	public long getRepaymentTotalAmount() {
		return repaymentTotalAmount;
	}
	public void setRepaymentTotalAmount(long repaymentTotalAmount) {
		this.repaymentTotalAmount = repaymentTotalAmount;
	}
	public long getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(long repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public long getRepaymentInterest() {
		return repaymentInterest;
	}
	public void setRepaymentInterest(long repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}
	public long getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(long remainAmount) {
		this.remainAmount = remainAmount;
	}
	
}
