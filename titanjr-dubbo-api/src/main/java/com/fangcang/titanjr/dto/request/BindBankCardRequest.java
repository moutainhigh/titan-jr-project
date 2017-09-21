package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class BindBankCardRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//虚拟机构绑卡记录id
	private String orgCardId;
	//机构类型
	private String userType;
	//机构名称
	private String orgName;
	//证件号码
	private String credentialsNumber;
	//开户行名称
	private String bankName;
	
	//银行卡号
	private String accountNumber;
	
	private String userName;
	
	//银行编码
	private String bankCode;
	
	//支行号
	private String branchCode;
	
	//城市编号
	private String cityCode;
	
	//城市名称
	private String cityName;
	
	
	//修改还是绑定 0:绑定 1：修改
	private String modifyOrBind;


	public String getUserType() {
		
		
		return userType;
		
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getModifyOrBind() {
		return modifyOrBind;
	}

	public void setModifyOrBind(String modifyOrBind) {
		this.modifyOrBind = modifyOrBind;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOrgCardId() {
		return orgCardId;
	}

	public void setOrgCardId(String orgCardId) {
		this.orgCardId = orgCardId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCredentialsNumber() {
		return credentialsNumber;
	}

	public void setCredentialsNumber(String credentialsNumber) {
		this.credentialsNumber = credentialsNumber;
	}
	
}
