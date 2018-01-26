package com.titanjr.fop.entity;

import java.io.Serializable;
import java.util.Date;
/***
 * 机构
 * @author luoqinglong
 * @date 2018年1月22日
 */
public class TitanMainOrg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5568284416565603111L;
	private Long orgId;
	private String orgCode;
	private String orgName;
	private String productId;
	//账户类型：1-企业，２-个人
	private Integer userType;
	//账户状态（1：生效，2：冻结，3：注销） 
	private Integer statusId;
	private String remark;
	//证件类型：0身份证;1护照;2军官证;3士兵证;4回乡证;5户口本;6外国护照;7其它
	private Integer certificatetype;
	private String certificateNumber;
	private String connect;
	private String mobileTel;
	//营业执照
	private String buslince;
	private Date createTime;
	private Date updateTime;
	
	
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(Integer certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
