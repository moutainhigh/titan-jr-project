package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.BusiCodeEnum;

/**
 * 
 * @author Jerry
 * @date 2018年1月8日 下午4:23:25
 */
public class TitanCardAuthDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205452059328574433L;
	
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
	 * 业务订单号
	 */
	@NotBlank
	private String orderNo;
	
	/**
	 * 身份证号
	 */
	@NotBlank
	private String idCode;
	
	/**
	 * 绑卡ID
	 */
	@NotBlank
	private String bindCardId;
	
	/**
	 * 终端类型  web、wap、mobile
	 */
	@NotBlank
	private String terminalType;
	
	/**
	 * 页面返回地址
	 */
	@NotBlank
	private String cardCheckPageUrl;
	
	/**
	 * 结果通讯地址
	 */
	@NotBlank
	private String cardChecknotifyUrl;
	
	@NotBlank
	private String signType;
	@NotBlank
	private String version;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
