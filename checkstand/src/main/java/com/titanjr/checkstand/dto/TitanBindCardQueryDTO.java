/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanBindCardQueryDTO.java
 * @author Jerry
 * @date 2018年1月8日 下午6:16:23  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 泰坦金融用户绑卡列表查询（目前用不上，泰坦金融没有调绑卡查询接口，支付路由自己调上游查）
 * @author Jerry
 * @date 2018年1月8日 下午6:16:23  
 */
public class TitanBindCardQueryDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -4576854344121402160L;
	
	@NotBlank
	private String busiCode;
	@NotBlank
	private String signType;
	@NotBlank
	private String version;
	@NotBlank
	private String merchantNo;
	@NotBlank
	private String idCode;
	@NotBlank
	private String bindCardId;
	@NotBlank
	private String cardType;//融数卡类型：10储蓄卡  11信用卡
	@NotBlank
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
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getBindCardId() {
		return bindCardId;
	}
	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}

}
