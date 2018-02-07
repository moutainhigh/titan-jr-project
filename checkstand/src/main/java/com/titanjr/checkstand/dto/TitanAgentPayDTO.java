/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanAgentPayDTO.java
 * @author Jerry
 * @date 2017年12月25日 上午11:49:04  
 */
package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.AgentTradeCodeEnum;

/**
 * 泰坦金融代付请求对象
 * @author Jerry
 * @date 2017年12月25日 上午11:49:04  
 */
public class TitanAgentPayDTO {
	
	/**
	 * 商家ID
	 */
	@NotBlank
	private String merchantNo;
	
	/**
	 * 订单号或者唯一流水号
	 */
	@NotBlank
	private String orderNo;
	
	/**
	 * 交易代码 {@link AgentTradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;
	
	/**
	 * 银行标示
	 */
	@NotBlank
	private String bankInfo;
	
	//融宝渠道代付需要字段 ------start----------------
	/**
	 * 序号   数字，同一批次不允许重复，不超过六个字符  必填
	 * 建议用时分秒生成
	 */
	private String number;
	/**
	 * 银行名称   如：中国工商银行  必填
	 */
	private String bankName;
	/**
	 * 分行名称    如：北京分行，当对公付款和对私大于5万时务必填写。
	 */
	private String branchBank;
	/**
	 * 支行名称   如：中关村支行，当对公付款和对私大于5万时务必填写
	 */
	private String subBank;
	/**
	 * 省，当对公付款和对私大于5万时务必填写
	 */
	private String province;
	/**
	 * 市，当对公付款和对私大于5万时务必填写
	 */
	private String city;
	/**
	 * 手机号，当所属行业为金融投资类和资金托管类时必须上送
	 */
	private String phone;
	/**
	 * 用户协议号  选填
	 */
	private String protocolNo;
	//融宝代付需要字段 ------end----------------
	
	/**
	 * 银行卡类型    00银行卡，01存折，02信用卡。不填默认为银行卡00
	 */
	@NotBlank
	private String accountType;
	
	/**
	 * 银行卡号
	 */
	@NotBlank
	private String accountNo;
	
	/**
	 * 帐户名
	 */
	@NotBlank
	private String accountName;
	
	/**
	 * 账户属性    0私人，1公司。不填时，默认为私人0
	 */
	@NotBlank
	private String accountProperty;
	
	/**
	 * 交易金额  单位分
	 */
	@NotBlank
	private String tradeAmount;
	
	/**
	 * 人民币：CNY, 港元：HKD，美元：USD。不填时，默认为人民币。
	 */
	@NotBlank
	private String currency;
	
	/**
	 * 证件号
	 */
	private String idCode;
	
	/**
	 * 开户证件类型<br>
	 * 0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5. 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7. 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
	 */
	private String idType;

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
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

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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

	public String getAccountProperty() {
		return accountProperty;
	}

	public void setAccountProperty(String accountProperty) {
		this.accountProperty = accountProperty;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
