package com.fangcang.titanjr.pay.req;

import com.fangcang.titanjr.dto.request.TitanPaymentRequest;

public class TitanRateComputeReq 
{
	private String userId;
	private String amount;
	private String deskId;
	private String payType;
	private String cardType;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDeskId() {
		return deskId;
	}
	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public void buildRequest(TitanPaymentRequest titanPaymentRequest){
		String payType = titanPaymentRequest.getLinePayType();
		String payerAccountType = titanPaymentRequest.getPayerAccountType();
		if("11".equals(payType)){//快捷支付
			this.cardType = payerAccountType;
		}
	}
	
}
