package com.fangcang.titanjr.dto.bean;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/11/3.
 */
public class LoanCompanyEnsure {
    private Long id;
    //增加机构编码
    private String userId;
    private String companyName;
    private Date foundDate;
    //企业规模 1 1-50 2 50-100 3 100-500 4 500-1000 5 1000以上
    private Integer enterpriseScale;
    //营业执照
    private String businessLicense;
    //税务登记证
    private String taxRegisterCode;
    //组织机构代码证
    private String orgCodeCertificate;
    //平台注册账号
    private String registerAccount;
    //平台注册日期
    private Date registerDate;
    //法人姓名
    private String legalPersonName;
    //法人证件类型
    private Integer legalPersonCertificateType;
    //法人证件号码
    private String legalPersonCertificateNumber;
    //联系人姓名
    private String contactName;
    //联系人电话
    private String contactPhone;
    //营业执照地址
    private String businessLicenseUrl;
    //机构代码证地址
    private String orgCodeCertificateUrl;
    //税务登记证地址
    private String taxRegisterCodeUrl;
    //开户许可证地址
    private String licenseUrl;
    //法人身份证地址--不一定是身份证可能是护照
    private String legalPersonUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void setLegalPersonCertificateNumber(String legalPersonCertificateNumber) {
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
