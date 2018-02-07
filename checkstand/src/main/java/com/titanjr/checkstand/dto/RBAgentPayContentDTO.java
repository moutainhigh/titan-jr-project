/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBAgentPayContentDTO.java
 * @author Jerry
 * @date 2018年2月5日 上午11:20:55  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年2月5日 上午11:20:55  
 */
public class RBAgentPayContentDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -6539352927463209260L;
	
	/**
	 * 序号，不超过6个字符，同一批次不允许重复
	 */
	@NotBlank
	private String number;
	/**
	 * 银行账户
	 */
	@NotBlank
	private String accountNo;
	/**
	 * 开户名
	 */
	@NotBlank
	private String accountName;
	/**
	 * 开户行     如：中国工商银行
	 */
	@NotBlank
	private String bankName;
	/**
	 * 分行名称    如：北京分行，当对公付款和对私大于5万时务必填写。
	 */
	private String branchBank = "";
	/**
	 * 支行名称   如：中关村支行，当对公付款和对私大于5万时务必填写
	 */
	private String subBank = "";
	/**
	 * 账户属性   必须上送“公”或者“私”
	 */
	@NotBlank
	private String accountProperty;
	/**
	 * 金额  单位元
	 */
	@NotBlank
	private String amount;
	/**
	 * 币种   人名币，固定值CNY
	 */
	private String currency;
	/**
	 * 省，当对公付款和对私大于5万时务必填写
	 */
	private String province = "";
	/**
	 * 市，当对公付款和对私大于5万时务必填写
	 */
	private String city = "";
	/**
	 * 手机号，当所属行业为金融投资类和资金托管类时必须上送
	 */
	private String phone = "";
	/**
	 * 证件类型，当所属行业为金融投资类和资金托管类时必须上送，目前支持：身份证、户口簿、军官证、士兵证、护照、台胞证
	 */
	private String certificateType = "";
	/**
	 * 证件号
	 */
	private String certificateNo = "";
	/**
	 * 用户协议号
	 */
	private String protocolNo;
	/**
	 * 商户订单号
	 */
	private String orderNo;
	/**
	 * 备注
	 */
	private String remark;
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchBank() {
		return branchBank;
	}
	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}
	public String getSubBank() {
		return subBank;
	}
	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}
	public String getAccountProperty() {
		return accountProperty;
	}
	public void setAccountProperty(String accountProperty) {
		this.accountProperty = accountProperty;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getProtocolNo() {
		return protocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
