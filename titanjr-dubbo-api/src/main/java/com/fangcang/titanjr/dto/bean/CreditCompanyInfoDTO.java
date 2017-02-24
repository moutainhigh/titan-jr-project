package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

/***
 * 授信申请公司资料
 * @author luoqinglong
 *
 */
public class CreditCompanyInfoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3641222913433391514L;
	
	/**
	 * 授信申请单号
	 */
	private String orderNo;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 授信申请时间
	 */
	private Date reqTime;
	/**
	 * 审核人
	 */
	private String firstAuditor;
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
	
	/**
	 * 查询用户ID
	 */
	private String orgCode;
	

	public String getFirstAuditor() {
		return firstAuditor;
	}

	public void setFirstAuditor(String firstAuditor) {
		this.firstAuditor = firstAuditor;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
