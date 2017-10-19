package com.fangcang.titanjr.dto.request;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class CusBankCardBindRequest extends BaseRequestDTO {
	 
    // 用户类型(1：商户，2：普通用户)
	@NotBlank
    private String userType;
    // 银行卡账号
	@NotBlank
    private String accountNumber;
    // 账号类型 00银行卡，01存折，02信用卡。不填默认为银行卡00。
    private String accountTypeId;
    // 开户行总行名称
    @NotBlank
    private String bankHeadName;
    // 币种（CNY）
    private String currency;

    // 交易批次号 类型：C(40) 取值：当前系统毫秒数
    private String reqSn;
    // 提交时间yyyyMMddHHmmss
    private String submitTime;
    // 账户目的(1:结算卡，2：其他卡, 3：提现卡,4:结算提现一体卡)
    private String accountPurpose;
    // 账户属性（1：对公，2：对私）
    private String accountProperty;
    // 证件号
    @NotBlank
    private String certificateNumber;
    // 开户证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5.
    // 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7.
    // 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
    private String certificateType;
    // 账号名 银行卡或存折上的所有人姓名。
    @NotBlank
    private String accountName;
    // 银行代码总行  通过接口"ruixue.wheatfield.bankn.query" 查询
    @NotBlank
    private String bankCode;

    // ============建议要有的参数===========
    // 开户行支行号
    private String bankBranch;
    // 开户行所在省
    private String bankProvince;
    // 开户行所在市
    private String bankCity;


    //===============非必输参数===========
    //角色号
    private String role;
    //开户行支行名称
    private String bankBranchName;
    //开户日期（yyyy-MM-dd）
    private Date openAccountDate;
    //账号用途
    private String openAccountDescription;
    //协议ID，若商户不填写，通联自动生成
    private String bindId;
    //商户关联ID
    private String relatId;
    //关联卡号
    private String relatedcard;
    //手机号，小灵通号
    private String tel;
    //商户保留信息
    private String merRem;
    //备注
    private String remark;
    //第三方用户ID
    private String referUserId;
    //创建人
    private String creator;

    //机构码
    private String productId;
    //真实机构
    private String userId;

    private String constId;

    
	public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getBankHeadName() {
        return bankHeadName;
    }

    public void setBankHeadName(String bankHeadName) {
        this.bankHeadName = bankHeadName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReqSn() {
        return reqSn;
    }

    public void setReqSn(String reqSn) {
        this.reqSn = reqSn;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getAccountPurpose() {
        return accountPurpose;
    }

    public void setAccountPurpose(String accountPurpose) {
        this.accountPurpose = accountPurpose;
    }

    public String getAccountProperty() {
        return accountProperty;
    }

    public void setAccountProperty(String accountProperty) {
        this.accountProperty = accountProperty;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public Date getOpenAccountDate() {
        return openAccountDate;
    }

    public void setOpenAccountDate(Date openAccountDate) {
        this.openAccountDate = openAccountDate;
    }

    public String getOpenAccountDescription() {
        return openAccountDescription;
    }

    public void setOpenAccountDescription(String openAccountDescription) {
        this.openAccountDescription = openAccountDescription;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getRelatId() {
        return relatId;
    }

    public void setRelatId(String relatId) {
        this.relatId = relatId;
    }

    public String getRelatedcard() {
        return relatedcard;
    }

    public void setRelatedcard(String relatedcard) {
        this.relatedcard = relatedcard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMerRem() {
        return merRem;
    }

    public void setMerRem(String merRem) {
        this.merRem = merRem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReferUserId() {
        return referUserId;
    }

    public void setReferUserId(String referUserId) {
        this.referUserId = referUserId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConstId() {
        return constId;
    }

    public void setConstId(String constId) {
        this.constId = constId;
    }
    
}
