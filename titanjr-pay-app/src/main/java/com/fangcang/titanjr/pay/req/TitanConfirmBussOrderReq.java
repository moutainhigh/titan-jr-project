package com.fangcang.titanjr.pay.req;

public class TitanConfirmBussOrderReq {
	// 业务地址
	private String url;
	// 业务订单号
	private String bussOrderNo;
	// 金额
	private String amount;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBussOrderNo() {
		return bussOrderNo;
	}

	public void setBussOrderNo(String bussOrderNo) {
		this.bussOrderNo = bussOrderNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
