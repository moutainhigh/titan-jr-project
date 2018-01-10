/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanUnBindCardDTO.java
 * @author Jerry
 * @date 2018年1月9日 上午11:18:47  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.BusiCodeEnum;

/**
 * @author Jerry
 * @date 2018年1月9日 上午11:18:47  
 */
public class TitanUnBindCardDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -2586137248384602494L;
	
	/**
	 * 业务号  {@link BusiCodeEnum}
	 */
	@NotBlank
	private String busiCode;
	/**
	 * 商户号
	 */
	@NotBlank
	private String merchantNo;
	/**
	 * 用户id
	 */
	@NotBlank
	private String userId;
	/**
	 * 身份证号
	 */
	@NotBlank
	private String idCode;
	/**
	 * 银行卡姓名
	 */
	@NotBlank
	private String acctName;
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String cardNo;
	/**
	 * 银行卡类型    10储蓄卡  11信用卡
	 */
	@NotBlank
	private String cardType;
	/**
	 * 协议号
	 */
	private String protocolNo;
	/**
	 * 签名的类型，默认1为MD5加签
	 */
	@NotBlank
	private String signType;
	/**
	 * 版本号  新版本：v1.1（含快捷支付）
	 */
	@NotBlank
	private String version;
	/**
	 * 签名字符串
	 */
	@NotBlank
	private String signMsg;
	
	
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
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
	public String getProtocolNo() {
		return protocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
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
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
