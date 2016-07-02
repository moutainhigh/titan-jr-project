package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class BankCardBindRequest extends BaseRequest {

    // 用户类型(1：商户，2：普通用户)
    private String usertype;
    // 银行卡账号
    private String accountnumber;
    // 账号类型 00银行卡，01存折，02信用卡。不填默认为银行卡00。
    private String accounttypeid;
    // 开户行总行名称
    private String bankheadname;
    // 币种（CNY）
    private String currency;

    // 交易批次号 类型：C(40) 取值：当前系统毫秒数
    private String req_sn;
    // 提交时间yyyyMMddHHmmss
    private String submit_time;
    // 账户目的(1:结算卡，2：其他卡, 3：提现卡,4:结算提现一体卡)
    private String accountpurpose;
    // 账户属性（1：对公，2：对私）
    private String accountproperty;
    // 证件号
    private String certificatenumnumber;
    // 开户证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5.
    // 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7.
    // 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
    private String certificatetype;
    // 账号名 银行卡或存折上的所有人姓名。
    private String  account_name;
    // 银行代码总行  通过接口"ruixue.wheatfield.bankn.query" 查询
    private String bank_code;

    // ============建议要有的参数===========
    // 开户行支行号
    private String bank_branch;
    // 开户行所在省
    private String bank_province;
    // 开户行所在市
    private String bank_city;


    //===============非必输参数===========
    //角色号
    private String role;
    //开户行支行名称
    private String bankbranchname;
    //开户日期（yyyy-MM-dd）
    private Date openaccountdate;
    //账号用途
    private String openaccountdescription;
    //协议ID，若商户不填写，通联自动生成
    private String  bindid;
    //商户关联ID
    private String relatid;
    //关联卡号
    private String relatedcard;
    //手机号，小灵通号
    private String tel;
    //商户保留信息
    private String merrem;
    //备注
    private String remark;
    //第三方用户ID
    private String referuserid;

    @Override
    public void check() throws RSValidateException {
    	RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
    	RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
    	RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
    	RequestValidationUtil.checkNotEmpty(this.getUsertype(), "usertype");
    	RequestValidationUtil.checkNotEmpty(this.getAccountnumber(), "accountnumber");
    	RequestValidationUtil.checkNotEmpty(this.getAccounttypeid(), "accounttypeid");
    	RequestValidationUtil.checkNotEmpty(this.getBankheadname(), "bankheadname");
    	RequestValidationUtil.checkNotEmpty(this.getCurrency(), "currency");
    	RequestValidationUtil.checkNotEmpty(this.getReq_sn(), "req_sn");
    	RequestValidationUtil.checkNotEmpty(this.getSubmit_time(), "submit_time");
    	RequestValidationUtil.checkNotEmpty(this.getAccountpurpose(), "accountpurpose");
    	RequestValidationUtil.checkNotEmpty(this.getAccountproperty(), "accountproperty");
    	RequestValidationUtil.checkNotEmpty(this.getCertificatenumnumber(), "certificatenumnumber");
    	RequestValidationUtil.checkNotEmpty(this.getCertificatetype(), "certificatetype");
    	RequestValidationUtil.checkNotEmpty(this.getAccount_name(), "account_name");
    	RequestValidationUtil.checkNotEmpty(this.getBank_code(), "bank_code");
//    	RequestValidationUtil.checkNotEmpty(this.getBank_branch(), "bank_branch");
//    	RequestValidationUtil.checkNotEmpty(this.getBank_province(), "bank_province");
//    	RequestValidationUtil.checkNotEmpty(this.getBank_city(), "bank_city");
    	
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccounttypeid() {
        return accounttypeid;
    }

    public void setAccounttypeid(String accounttypeid) {
        this.accounttypeid = accounttypeid;
    }

    public String getBankheadname() {
        return bankheadname;
    }

    public void setBankheadname(String bankheadname) {
        this.bankheadname = bankheadname;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReq_sn() {
        return req_sn;
    }

    public void setReq_sn(String req_sn) {
        this.req_sn = req_sn;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getAccountpurpose() {
        return accountpurpose;
    }

    public void setAccountpurpose(String accountpurpose) {
        this.accountpurpose = accountpurpose;
    }

    public String getAccountproperty() {
        return accountproperty;
    }

    public void setAccountproperty(String accountproperty) {
        this.accountproperty = accountproperty;
    }

    public String getCertificatenumnumber() {
        return certificatenumnumber;
    }

    public void setCertificatenumnumber(String certificatenumnumber) {
        this.certificatenumnumber = certificatenumnumber;
    }

    public String getCertificatetype() {
        return certificatetype;
    }

    public void setCertificatetype(String certificatetype) {
        this.certificatetype = certificatetype;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getBank_province() {
        return bank_province;
    }

    public void setBank_province(String bank_province) {
        this.bank_province = bank_province;
    }

    public String getBank_city() {
        return bank_city;
    }

    public void setBank_city(String bank_city) {
        this.bank_city = bank_city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBankbranchname() {
        return bankbranchname;
    }

    public void setBankbranchname(String bankbranchname) {
        this.bankbranchname = bankbranchname;
    }

    public Date getOpenaccountdate() {
        return openaccountdate;
    }

    public void setOpenaccountdate(Date openaccountdate) {
        this.openaccountdate = openaccountdate;
    }

    public String getOpenaccountdescription() {
        return openaccountdescription;
    }

    public void setOpenaccountdescription(String openaccountdescription) {
        this.openaccountdescription = openaccountdescription;
    }

    public String getBindid() {
        return bindid;
    }

    public void setBindid(String bindid) {
        this.bindid = bindid;
    }

    public String getRelatid() {
        return relatid;
    }

    public void setRelatid(String relatid) {
        this.relatid = relatid;
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

    public String getMerrem() {
        return merrem;
    }

    public void setMerrem(String merrem) {
        this.merrem = merrem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReferuserid() {
        return referuserid;
    }

    public void setReferuserid(String referuserid) {
        this.referuserid = referuserid;
    }
}
