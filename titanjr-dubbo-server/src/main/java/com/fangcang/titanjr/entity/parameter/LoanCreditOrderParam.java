package com.fangcang.titanjr.entity.parameter;

import java.io.Serializable;


public class LoanCreditOrderParam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4857578960124388604L;
	/**
	 * 公司名称
	 */
	private String name;
	
	private String contactName;
	/**
	 * 审核状态
	 */
	private Integer status;
	
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
