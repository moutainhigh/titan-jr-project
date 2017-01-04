package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.fangcang.titanjr.rs.dto.AccountFlow;
import com.fangcang.titanjr.rs.request.RSPayOrderRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
public class RSPayOrderResponse extends BaseResponse {
	
	private String payPage;
	
	private RSPayOrderRequest rsPayOrderRequest;

	public String getPayPage() {
		return payPage;
	}

	public void setPayPage(String payPage) {
		this.payPage = payPage;
	}

	public RSPayOrderRequest getRsPayOrderRequest() {
		return rsPayOrderRequest;
	}

	public void setRsPayOrderRequest(RSPayOrderRequest rsPayOrderRequest) {
		this.rsPayOrderRequest = rsPayOrderRequest;
	}
	
}
