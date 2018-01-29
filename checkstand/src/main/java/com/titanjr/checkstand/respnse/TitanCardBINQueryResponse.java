/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanCardBINQueryRequest.java
 * @author Jerry
 * @date 2018年1月4日 下午4:50:02  
 */
package com.titanjr.checkstand.respnse;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年1月4日 下午4:50:02  
 */
public class TitanCardBINQueryResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -2757777071441871239L;
	
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String cardNo;
	/**
	 * 所属银行编号（其实是泰坦金融的bankInfo）
	 */
	private String bankCode;
	
	/**
	 * 融宝的bankCode是泰坦金融的bankInfo
	 */
	@NotBlank
	private String bankInfo;
	
	/**
	 * 所属银行名称
	 */
	@NotBlank
	private String bankName;
	/**
	 * 银行卡类型    新版本v1.1：10-储蓄卡，11-信用卡
	 */
	@NotBlank
	private String cardType;
	
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getBankInfo() {
		return bankInfo;
	}
	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

}
