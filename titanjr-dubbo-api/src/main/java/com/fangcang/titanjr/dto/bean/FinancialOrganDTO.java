package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/5/9.
 */
public class FinancialOrganDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//==公共部分
    private String orgCode;
    private String orgId;
    private String orgName;
    private String constId;
    private String userId;
    private String productId;
    private String role;
    private String userName;
    private Integer userType;
    private Integer statusId;
    private String address;
    private String post;
    private String remark;
    private Integer orgType;
    private Integer certificateType;
    private String certificateNumber;
    private String mobileTel;
    private String bindCompanyName;
    private Date createTime;
    //==企业机构部分
    private String orgshortname;
    private String mcc;
    private String connect;
    private String buslince;
    private String taxregcardf;
    private String taxregcards;
    private String organcertificate;
    private String busplacectf;
    private String loancard;
    private String acuntopnlince;
    private String corporatename;

    //==个人机构部分,个人机构orgName就是personname
    private String personengname;
    private String persontype;
    private String personsex;
    private String birthday;
    private String fixTel;
    private String email;
    private String titanCode;

    private List<OrgImageInfo> orgImageInfoList;

    private CheckStatus checkStatus;

    private OrgBindInfo orgBindInfo;

    
    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
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

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getConstId() {
        return constId;
    }

    public void setConstId(String constId) {
        this.constId = constId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getBindCompanyName() {
        return bindCompanyName;
    }

    public void setBindCompanyName(String bindCompanyName) {
        this.bindCompanyName = bindCompanyName;
    }

    public List<OrgImageInfo> getOrgImageInfoList() {
        return orgImageInfoList;
    }

    public void setOrgImageInfoList(List<OrgImageInfo> orgImageInfoList) {
        this.orgImageInfoList = orgImageInfoList;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public OrgBindInfo getOrgBindInfo() {
        return orgBindInfo;
    }

    public String getOrgshortname() {
        return orgshortname;
    }

    public void setOrgshortname(String orgshortname) {
        this.orgshortname = orgshortname;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getBuslince() {
        return buslince;
    }

    public void setBuslince(String buslince) {
        this.buslince = buslince;
    }

    public String getTaxregcardf() {
        return taxregcardf;
    }

    public void setTaxregcardf(String taxregcardf) {
        this.taxregcardf = taxregcardf;
    }

    public String getTaxregcards() {
        return taxregcards;
    }

    public void setTaxregcards(String taxregcards) {
        this.taxregcards = taxregcards;
    }

    public String getOrgancertificate() {
        return organcertificate;
    }

    public void setOrgancertificate(String organcertificate) {
        this.organcertificate = organcertificate;
    }

    public String getBusplacectf() {
        return busplacectf;
    }

    public void setBusplacectf(String busplacectf) {
        this.busplacectf = busplacectf;
    }

    public String getLoancard() {
        return loancard;
    }

    public void setLoancard(String loancard) {
        this.loancard = loancard;
    }

    public String getAcuntopnlince() {
        return acuntopnlince;
    }

    public void setAcuntopnlince(String acuntopnlince) {
        this.acuntopnlince = acuntopnlince;
    }

    public String getCorporatename() {
        return corporatename;
    }

    public void setCorporatename(String corporatename) {
        this.corporatename = corporatename;
    }

    public String getPersonengname() {
        return personengname;
    }

    public void setPersonengname(String personengname) {
        this.personengname = personengname;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }

    public String getPersonsex() {
        return personsex;
    }

    public void setPersonsex(String personsex) {
        this.personsex = personsex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFixTel() {
        return fixTel;
    }

    public void setFixTel(String fixTel) {
        this.fixTel = fixTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrgBindInfo(OrgBindInfo orgBindInfo) {
        this.orgBindInfo = orgBindInfo;
    }

    public String getTitanCode() {
        return titanCode;
    }

    public void setTitanCode(String titanCode) {
        this.titanCode = titanCode;
    }
}
