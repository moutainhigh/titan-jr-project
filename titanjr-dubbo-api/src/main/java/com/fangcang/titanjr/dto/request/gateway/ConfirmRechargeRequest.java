package com.fangcang.titanjr.dto.request.gateway;

import java.io.Serializable;

public class ConfirmRechargeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4600697286588206353L;
	
	//充值确认接口：109
	private String busiCode;
	
	//签名的类型，默认1为MD5加签
	private String signType;
	
	//新版本：v1.1（含快捷支付）
	private String version;
	
	//商户号
	private String merchantNo;
	
	//订单号
	private String orderNo;
	
	//41：新快捷支付
	private String payType;
	
	//验证码
	private String checkCode;
	
	private String signMsg;

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

}
