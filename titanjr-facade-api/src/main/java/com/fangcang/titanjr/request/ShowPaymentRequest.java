package com.fangcang.titanjr.request;

public class ShowPaymentRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//房仓的商家编码
	private String merchantcode;
	
	//支付单
	private String payOrderNo;

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}
	
}
