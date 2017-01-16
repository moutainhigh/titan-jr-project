package com.fangcang.titanjr.dto.request;

import java.util.Date;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 修改机构信息
 * @author luoqinglong
 * @2016年7月8日
 */
public class OrgUpdateRequest extends BaseRequestDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5904819964831168056L;
	private String orgCode;
	/**
	 * 联系人姓名
	 */
	private String connect;
	/**
	 * 手机号码
	 */
	private String mobiletel;
	/**
	 * 授信金额估算时间
	 */
	private Date lastUpdateDate;
	
	//最大可申请授信额度
	private Long maxLoanAmount;
	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(Long maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	public String getMobiletel() {
		return mobiletel;
	}

	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}
	
}
