package com.fangcang.titanjr.web.pojo;

import java.io.Serializable;

/**
 * 授信申请
 * @author luoqinglong
 *
 */
public class CreditOrderPojo extends BasePojo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3903511860401036331L;

	/**
	 * 授信状态:1-待审核，2-初审通，3-终审通过，
	 */
	private Integer status;
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 联系人姓名
	 */
	private String contactName;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
}
