package com.fangcang.titanjr.response;

public class ShowPaymentResponse extends BaseResponse {

	/**
	 * 显示状态，1：显示在线支付，2.显示支付中
	 */
	private Integer showStatus;

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}
}
