package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class RepaymentAmountComputeResponse extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
	private long realoverdueinterestAmount;// 逾期利息罰息
	private long realoverduecapitalAmount;// 逾期本金罰息
	private long realinterestAmount; //利息还款金额
	private long realcapitalAmount;//本金还款金额
	
	public long getRealoverdueinterestAmount() {
		return realoverdueinterestAmount;
	}
	public void setRealoverdueinterestAmount(long realoverdueinterestAmount) {
		this.realoverdueinterestAmount = realoverdueinterestAmount;
	}
	public long getRealoverduecapitalAmount() {
		return realoverduecapitalAmount;
	}
	public void setRealoverduecapitalAmount(long realoverduecapitalAmount) {
		this.realoverduecapitalAmount = realoverduecapitalAmount;
	}
	public long getRealinterestAmount() {
		return realinterestAmount;
	}
	public void setRealinterestAmount(long realinterestAmount) {
		this.realinterestAmount = realinterestAmount;
	}
	public long getRealcapitalAmount() {
		return realcapitalAmount;
	}
	public void setRealcapitalAmount(long realcapitalAmount) {
		this.realcapitalAmount = realcapitalAmount;
	}

}
