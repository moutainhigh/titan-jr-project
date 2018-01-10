package com.titanjr.checkstand.respnse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.BusiCodeEnum;

/**
 * 
 * @author Jerry
 * @date 2018年1月8日 下午4:24:24
 */
public class TitanCardAuthResponse extends RSResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -818636811226340094L;
	
	/**
	 * 业务号  {@link BusiCodeEnum}
	 */
	@NotBlank
	private String busiCode;
	
	/**
	 * 商户订单号
	 */
	private String orderNo;
	
	/**
	 * 终端类型  web、wap、mobile
	 */
	private String terminalType;
	
	/**
	 * 银行卡号后4位
	 */
	private String cardNo;
	
	/**
	 * 只会返回招商银行
	 */
	private String bankName;
	
	/**
	 * 银行编码
	 */
	private String bankInfo;
	
	/**
	 * 银行预留手机号
	 */
	private String phone;
	
	/**
	 * 鉴权成功:3   鉴权失败:4
	 */
	private String statusId;
	

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
