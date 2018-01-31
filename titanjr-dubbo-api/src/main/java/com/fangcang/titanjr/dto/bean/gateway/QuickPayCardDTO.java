package com.fangcang.titanjr.dto.bean.gateway;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 快捷支付绑卡信息
 * @author jerry
 *
 */
public class QuickPayCardDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 243592485453442657L;
	
	//绑卡ID
	private String bindCardId;
	
	//10：借记卡 11：信用卡
	private String accountType;
	
	//银行卡号
	private String accountNo;
	
	//银行名称
	private String accountName;
	
	//所属银行编码
	private String bankNo;
	
	//手机号
	private String mobile;

	public String getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
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

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
