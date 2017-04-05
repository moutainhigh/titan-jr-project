package com.fangcang.titanjr.entity;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/11/3.
 */
public class LoanCompanyEnsure {

	private Long id;
	// 新授信单号
	private String newOrderNo;
	// 授信单号
	private String orderNo;

	// 增加机构编码
	private String orgCode;
	private String companyName;
	private Date foundDate;
	// 企业规模 1 1-50 2 50-100 3 100-500 4 500-1000 5 1000以上
	private Integer enterpriseScale;
	// 营业执照
	private String businessLicense;
	// 税务登记证
	private String taxRegisterCode;
	// 组织机构代码证
	private String orgCodeCertificate;
	// 平台注册账号
	private String registerAccount;
	// 平台注册日期
	private Date registerDate;
	// 法人姓名
	private String legalPersonName;
	// 法人证件类型
	private Integer legalPersonCertificateType;
	// 法人证件号码
	private String legalPersonCertificateNumber;
	// 联系人姓名
	private String contactName;
	// 联系人电话
	private String contactPhone;
	// 营业执照地址
	private String businessLicenseUrl;
	// 机构代码证地址
	private String orgCodeCertificateUrl;
	// 税务登记证地址
	private String taxRegisterCodeUrl;
	// 开户许可证地址
	private String licenseUrl;
	// 法人身份证地址--不一定是身份证可能是护照
	private String legalPersonUrl;

	// 企业办公地址
	private String officeAddress;
	// 企业注册地址
	private String regAddress;
	
	private String openAccount;

	public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}

	/**
	 * 营业照生效日期
	 */
	private Date certificateStartDate;
	/**
	 * 营业照失效日期
	 */
	private Date certificateExpireDate;
	/**
	 * 企业类型：'1 有限责任公司 2 股份有限公司 3 内资 4 国有全资 5 集资全资 6 国外投资股份有限公司 99 其他'
	 */
	private Integer companyType;
	/**
	 * 注册资本
	 */
	private String registFinance;

	
	
	public String getNewOrderNo() {
		return newOrderNo;
	}

	public void setNewOrderNo(String newOrderNo) {
		this.newOrderNo = newOrderNo;
	}

	public Date getCertificateStartDate() {
		return certificateStartDate;
	}

	public void setCertificateStartDate(Date certificateStartDate) {
		this.certificateStartDate = certificateStartDate;
	}

	public Date getCertificateExpireDate() {
		return certificateExpireDate;
	}

	public void setCertificateExpireDate(Date certificateExpireDate) {
		this.certificateExpireDate = certificateExpireDate;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getRegistFinance() {
		return registFinance;
	}

	public void setRegistFinance(String registFinance) {
		this.registFinance = registFinance;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public Integer getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(Integer enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getTaxRegisterCode() {
		return taxRegisterCode;
	}

	public void setTaxRegisterCode(String taxRegisterCode) {
		this.taxRegisterCode = taxRegisterCode;
	}

	public String getOrgCodeCertificate() {
		return orgCodeCertificate;
	}

	public void setOrgCodeCertificate(String orgCodeCertificate) {
		this.orgCodeCertificate = orgCodeCertificate;
	}

	public String getRegisterAccount() {
		return registerAccount;
	}

	public void setRegisterAccount(String registerAccount) {
		this.registerAccount = registerAccount;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public Integer getLegalPersonCertificateType() {
		return legalPersonCertificateType;
	}

	public void setLegalPersonCertificateType(Integer legalPersonCertificateType) {
		this.legalPersonCertificateType = legalPersonCertificateType;
	}

	public String getLegalPersonCertificateNumber() {
		return legalPersonCertificateNumber;
	}

	public void setLegalPersonCertificateNumber(
			String legalPersonCertificateNumber) {
		this.legalPersonCertificateNumber = legalPersonCertificateNumber;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getBusinessLicenseUrl() {
		return businessLicenseUrl;
	}

	public void setBusinessLicenseUrl(String businessLicenseUrl) {
		this.businessLicenseUrl = businessLicenseUrl;
	}

	public String getOrgCodeCertificateUrl() {
		return orgCodeCertificateUrl;
	}

	public void setOrgCodeCertificateUrl(String orgCodeCertificateUrl) {
		this.orgCodeCertificateUrl = orgCodeCertificateUrl;
	}

	public String getTaxRegisterCodeUrl() {
		return taxRegisterCodeUrl;
	}

	public void setTaxRegisterCodeUrl(String taxRegisterCodeUrl) {
		this.taxRegisterCodeUrl = taxRegisterCodeUrl;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String getLegalPersonUrl() {
		return legalPersonUrl;
	}

	public void setLegalPersonUrl(String legalPersonUrl) {
		this.legalPersonUrl = legalPersonUrl;
	}
}
