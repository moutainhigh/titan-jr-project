/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanUnBindCardResponse.java
 * @author Jerry
 * @date 2018年1月9日 上午11:47:53  
 */
package com.titanjr.checkstand.respnse;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年1月9日 上午11:47:53  
 */
public class TitanUnBindCardResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 7758777321292469704L;
	
	/**
	 * 用户ID
	 */
	@NotBlank
	private String userId;
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String cardNo;
	/**
	 * 协议号
	 */
	private String protocolNo;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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

}
