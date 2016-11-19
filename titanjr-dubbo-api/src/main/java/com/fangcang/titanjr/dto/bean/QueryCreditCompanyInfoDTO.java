package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/***
 * 授信申请公司资料
 * @author luoqinglong
 *
 */
public class QueryCreditCompanyInfoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3641222913433391514L;
	/**
	 * 申请时间(创建时间)
	 */
	private String createtime;
	/**
	 * 授信申请联系人
	 */
	private String contactName;
	/**
	 * 授信申请公司名称
	 */
	private String name;
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 授信申请手机号码
	 */
	private String contactPhone;

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

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

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	
}
