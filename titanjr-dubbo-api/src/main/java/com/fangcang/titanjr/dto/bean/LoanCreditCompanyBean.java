package com.fangcang.titanjr.dto.bean;

/**
 * Created by zhaoshan on 2016/11/4.
 */
public class LoanCreditCompanyBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String startDate;
	private String regAddress;
	private String officeAddress;
	private Integer orgSize;
	private String license;
	private String taxRegNo;
	private String orgCode;
	private String regAccount;
	private String regDate;
	private Integer empSize;
	private String legalName;
	private Integer legalceType;
	private String legalNo;
	private String contactName;
	private String contactPhone;
	private String waterEmail;
	private String openAccount;
	

	private String certificateStartDate;
	private String certificateExpireDate;
	private Integer companyType;
	private String registFinance;
	

	public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}


	public String getCertificateStartDate() {
		return certificateStartDate;
	}

	public void setCertificateStartDate(String certificateStartDate) {
		this.certificateStartDate = certificateStartDate;
	}

	public String getCertificateExpireDate() {
		return certificateExpireDate;
	}

	public void setCertificateExpireDate(String certificateExpireDate) {
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

	/**
	 * 营业执照URL
	 */
	private String licenseUrl;
	/**
	 * 法人身份证
	 */

	private String legalNoUrl;
	/**
	 * 经营场所证明
	 */
	private String officeNoUrl;
	/**
	 * 开户许可证
	 */
	private String accountUrl;
	/**
	 * 信用报告
	 */
	private String creditUrl;
	/**
	 * 经营产所照片
	 */
	private String officeUrl;
	/**
	 * 企业流水
	 */
	private String waterUrl;
	/**
	 * 税务登记URL
	 */
	private String taxRegUrl;
	/**
	 * 组织编码
	 */
	private String orgCodeUrl;
	private Integer isPush;

	public String getTaxRegUrl() {
		return taxRegUrl;
	}

	public void setTaxRegUrl(String taxRegUrl) {
		this.taxRegUrl = taxRegUrl;
	}

	public String getOrgCodeUrl() {
		return orgCodeUrl;
	}

	public void setOrgCodeUrl(String orgCodeUrl) {
		this.orgCodeUrl = orgCodeUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Integer getOrgSize() {
		return orgSize;
	}

	public void setOrgSize(Integer orgSize) {
		this.orgSize = orgSize;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getTaxRegNo() {
		return taxRegNo;
	}

	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRegAccount() {
		return regAccount;
	}

	public void setRegAccount(String regAccount) {
		this.regAccount = regAccount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Integer getEmpSize() {
		return empSize;
	}

	public void setEmpSize(Integer empSize) {
		this.empSize = empSize;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public Integer getLegalceType() {
		return legalceType;
	}

	public void setLegalceType(Integer legalceType) {
		this.legalceType = legalceType;
	}

	public String getLegalNo() {
		return legalNo;
	}

	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
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

	public String getWaterEmail() {
		return waterEmail;
	}

	public void setWaterEmail(String waterEmail) {
		this.waterEmail = waterEmail;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String getLegalNoUrl() {
		return legalNoUrl;
	}

	public void setLegalNoUrl(String legalNoUrl) {
		this.legalNoUrl = legalNoUrl;
	}

	public String getOfficeNoUrl() {
		return officeNoUrl;
	}

	public void setOfficeNoUrl(String officeNoUrl) {
		this.officeNoUrl = officeNoUrl;
	}

	public String getAccountUrl() {
		return accountUrl;
	}

	public void setAccountUrl(String accountUrl) {
		this.accountUrl = accountUrl;
	}

	public String getCreditUrl() {
		return creditUrl;
	}

	public void setCreditUrl(String creditUrl) {
		this.creditUrl = creditUrl;
	}

	public String getOfficeUrl() {
		return officeUrl;
	}

	public void setOfficeUrl(String officeUrl) {
		this.officeUrl = officeUrl;
	}

	public String getWaterUrl() {
		return waterUrl;
	}

	public void setWaterUrl(String waterUrl) {
		this.waterUrl = waterUrl;
	}

	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}
}
