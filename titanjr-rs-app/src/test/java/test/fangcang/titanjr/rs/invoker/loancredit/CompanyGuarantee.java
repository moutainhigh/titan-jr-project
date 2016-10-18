package test.fangcang.titanjr.rs.invoker.loancredit;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/10/9.
 * 担保企业信息
 */
public class CompanyGuarantee {
    //担保公司名称
    private String companyName;
    //成立日期
    private Date foundDate;
    //注册地址
    private String registerAddress;
    //办公地址
    private String officeAddress;
    //企业规模
    private String enterpriseScale;
    //营业执照号
    private String businessLicense;
    //税务登记号
    private String taxRegisterCode;
    //组织机构代码
    private String orgCodeCertificate;
    //平台注册账号
    private String registerAccount;
    //平台注册日期
    private Date registerDate;

    //法人姓名
    private String legalPersonName;
    //法人证件类型
    private Integer legalPersonCertificateType;
    //证件号码
    private String legalPersonCertificateNumber;

    //联系人姓名
    private String contactName;
    //联系电话
    private String contactPhone;

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

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    public void setEnterpriseScale(String enterpriseScale) {
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
}
