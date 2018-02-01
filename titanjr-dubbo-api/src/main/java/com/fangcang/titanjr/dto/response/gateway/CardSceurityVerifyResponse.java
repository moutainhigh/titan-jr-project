package com.fangcang.titanjr.dto.response.gateway;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卡密校验通知
 * @author jerry
 * 
 * @date 2017.6
 */
public class CardSceurityVerifyResponse extends RSBaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -818636811226340094L;
	
	////卡密校验通知接口：114
	private String busiCode;
	
	//签名的类型，默认1为MD5加签
	private String signType;
	
	//签名的类型，默认1为MD5加签
	private String version;
	
	//签名的类型，默认1为MD5加签
	private String merchantNo;
	
	private String orderNo;
	
	//Pc端：web 移动端：mobile
	private String terminalType;
	
	//银行卡号后4位
	private String cardNo;
	
	//只会返回招商银行
	private String bankName;
	
	//银行编码
	private String bankInfo;
	
	//银行预留手机号
	private String phone;
	
	//鉴权成功:3   鉴权失败:4
	private String statusId;
	
	//说明信息
	private String cardPassCheckMsg;
	
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
	public String getCardPassCheckMsg() {
		return cardPassCheckMsg;
	}

	public void setCardPassCheckMsg(String cardPassCheckMsg) {
		this.cardPassCheckMsg = cardPassCheckMsg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
