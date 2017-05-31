package com.fangcang.titanjr.entity;

import java.util.Date;

/**
 * 授信申请公司资料
 * Created by zhaoshan on 2016/11/4.
 */
public class LoanCreditCompany implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 新授信单号
     */
    private String newOrderNo;
    
    private String creditOrderNo;
    /**
     * 企业名称
     */
    private String name;
    /**
     * 成立日期
     */
    private Date startDate;
    /**
     * 注册地址
     */
    private String regAddress;
    /**
     * 办公地址
     */
    private String officeAddress;
    /**
     * 企业规模1 1-50 2 50-100 3 100-500 4 500-1000 5 1000以上
     */
    private Integer orgSize;
    /**
     * 营业执照
     */
    private String license;
    /**
     * 税务登记号
     */
    private String taxRegNo;
    /**
     * 
     */
    private String orgCode;
    /**
     * 平台注册账号
     */
    private String regAccount;
    /**
     * 平台注册时间
     */
    private Date regDate;
    /**
     * 员工人数
     */
    private Integer empSize;
    /**
     * 法人名称
     */
    private String legalName;
    /**
     * 1.身份证; 2.护照; 8.户口本; 21.军官证;
     */
    private Integer legalceType;
    /**
     * 法人证件号
     */
    private String legalNo;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 流水发送邮件地址
     */
    private String waterEmail;
    /**
     * 企业补充信息
     */
    private String appendInfo;
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
    /**
	 * 营业照生效日期
	 */
    private Date certificateStartDate;
    /**
	 * 营业照失效日期
	 */
	private Date certificateExpireDate;
	/**
	 * 企业类型：'1 有限责任公司 2 股份有限公司 3 内资 4 国有全资  5 集资全资 6 国外投资股份有限公司 99 其他'
	 */
	private Integer companyType;
	/**
	 * 注册资本
	 */
	private String registFinance;
	
	private String openAccount;
	
	
    public String getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(String openAccount) {
		this.openAccount = openAccount;
	}

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

	private Integer isPush;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public String getCreditOrderNo() {
		return creditOrderNo;
	}

	public void setCreditOrderNo(String creditOrderNo) {
		this.creditOrderNo = creditOrderNo;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
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

    public String getAppendInfo() {
        return appendInfo;
    }

    public void setAppendInfo(String appendInfo) {
        this.appendInfo = appendInfo;
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
