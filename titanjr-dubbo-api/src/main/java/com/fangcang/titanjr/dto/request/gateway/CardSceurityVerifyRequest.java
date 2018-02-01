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
	//身份证号
	private String idCode;
	//绑卡ID
	private String bindCardId;
	
	//终端类型	PC端：web 移动端：mobile
	private String terminalType;
	
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

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
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

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
