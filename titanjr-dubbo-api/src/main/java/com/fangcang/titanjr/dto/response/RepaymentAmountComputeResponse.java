package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class RepaymentAmountComputeResponse extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
	private long realoverdueinterestAmount;// 逾期利息罰息
	private long realoverduecapitalAmount;// 逾期本金罰息
	private long realinterestAmount; // 利息还款金额
	private long realcapitalAmount;// 本金还款金额

	/**
	 * 用户应还本金(分)
	 */
	private String usershouldcapital;
	/**
	 * 用户应还利息(分)
	 */
	private String usershouldinterest;

	/**
	 * 用户逾期罚金(分)
	 */
	private String useroverduefine;
	/**
	 * 用户逾期利息(分)
	 */
	private String useroverdueinterest;

	public String getUsershouldcapital() {
		return usershouldcapital;
	}

	public void setUsershouldcapital(String usershouldcapital) {
		this.usershouldcapital = usershouldcapital;
	}

	public String getUsershouldinterest() {
		return usershouldinterest;
	}

	public void setUsershouldinterest(String usershouldinterest) {
		this.usershouldinterest = usershouldinterest;
	}

	public String getUseroverduefine() {
		return useroverduefine;
	}

	public void setUseroverduefine(String useroverduefine) {
		this.useroverduefine = useroverduefine;
	}

	public String getUseroverdueinterest() {
		return useroverdueinterest;
	}

	public void setUseroverdueinterest(String useroverdueinterest) {
		this.useroverdueinterest = useroverdueinterest;
	}

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
