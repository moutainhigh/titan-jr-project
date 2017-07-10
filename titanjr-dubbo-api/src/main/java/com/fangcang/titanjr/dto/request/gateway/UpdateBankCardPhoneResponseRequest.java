package com.fangcang.titanjr.dto.request.gateway;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UpdateBankCardPhoneResponseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5772873965935487974L;
	
	//更改预留手机号接口：113
	private String busiCode;
	
	//签名的类型，默认1为MD5加签
	private String signType;
	
	//新版本：v1.1（含快捷支付）
	private String version;
	
	//商户号
	private String merchantNo;
	
	//平台申请的用户id
	private String userId;
	
	//身份证号
	private String idCode;
	
	//10：借记卡   11：信用卡
	private String accountType;
	
	//银行卡账号姓名
	private String acctName;
	
	//银行卡账号
	private String cardNo;
	
	//安全码（信用卡背后的3位数字 信用卡必传此项/储蓄卡可不传）
	//储蓄卡不能传null，要传""
	private String safetyCode="";
	
	//卡有效期（月年格式 例如2020年09月应写为0920，信用卡必传此项/储蓄卡可不传）
	//储蓄卡不能传null，要传""
	private String validthru="";
	
	//新手机号
	private String phone;
	
	//签名字符串
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSafetyCode() {
		return safetyCode;
	}

	public void setSafetyCode(String safetyCode) {
		this.safetyCode = safetyCode;
	}

	public String getValidthru() {
		return validthru;
	}

	public void setValidthru(String validthru) {
		this.validthru = validthru;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
