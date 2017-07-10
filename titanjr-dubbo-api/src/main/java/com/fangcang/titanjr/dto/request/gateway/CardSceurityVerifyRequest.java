package com.fangcang.titanjr.dto.request.gateway;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CardSceurityVerifyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205452059328574433L;
	
	//卡密校验请求接口：114
	private String busiCode;
	
	private String signType;
	
	private String version;
	
	private String merchantNo;
	
	//业务订单号
	private String orderNo;
	
	//支付方式   41：新快捷支付
	private String payType;
	
	//终端类型	Pc端：web 移动端：mobile
	private String terminalType;
	
	//银行卡账号
	private String cardNo;
	
	//页面返回地址
	private String cardCheckPageUrl;
	
	//结果通讯地址
	private String cardChecknotifyUrl;
	
	private String signMsg;
	
	private String gateWayURL;

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

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardCheckPageUrl() {
		return cardCheckPageUrl;
	}

	public void setCardCheckPageUrl(String cardCheckPageUrl) {
		this.cardCheckPageUrl = cardCheckPageUrl;
	}

	public String getCardChecknotifyUrl() {
		return cardChecknotifyUrl;
	}

	public void setCardChecknotifyUrl(String cardChecknotifyUrl) {
		this.cardChecknotifyUrl = cardChecknotifyUrl;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
	public String getGateWayURL() {
		return gateWayURL;
	}

	public void setGateWayURL(String gateWayURL) {
		this.gateWayURL = gateWayURL;
	}

}
