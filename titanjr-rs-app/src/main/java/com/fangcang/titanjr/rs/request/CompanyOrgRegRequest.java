package com.fangcang.titanjr.rs.request;


import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public class CompanyOrgRegRequest extends BaseRequest {
    //===============以下信息必填==========
    //企业名称
    private String companyname;
    //营业执照
    private String buslince;
    //用户类型(1：商户 )
    private String usertype = "1";
    //用户名称，及接入机构的用户名
    private String username;

    //===================以下信息可选===========
    //邮政编码
    private String post;
    //税务登记证1
    private String taxregcardf;
    //企业编号
    private String companycode;
    //贷款卡
    private String loancard;
    //法人姓名
    private String corporatename;
    //MCC码
    private String mcc;
    //开户许可证
    private String acuntopnlince;
    //备注
    private String remark;
    //角色号
    private String role;
    //经营场所实地认证
    private String busplacectf;
    //法人身份证
    private String corporateidentity;
    //地址
    private String address;
    //企业简称
    private String shortname;
    //税务登记证2
    private String taxregcards;
    //组织结构代码证
    private String organcertificate;
    //联系方式
    private String connect;


    @Override
    public void check() throws RSValidateException {
        //校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.companyname,"companyname");
        RequestValidationUtil.checkNotEmpty(this.buslince,"buslince");
        RequestValidationUtil.checkNotEmpty(this.usertype,"usertype");
        RequestValidationUtil.checkNotEmpty(this.username,"username");

        //校验长度
        RequestValidationUtil.checkMaxLength(this.usertype, 1, "usertype");
        //校验固定值

    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getBuslince() {
        return buslince;
    }

    public void setBuslince(String buslince) {
        this.buslince = buslince;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTaxregcardf() {
        return taxregcardf;
    }

    public void setTaxregcardf(String taxregcardf) {
        this.taxregcardf = taxregcardf;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getLoancard() {
        return loancard;
    }

    public void setLoancard(String loancard) {
        this.loancard = loancard;
    }

    public String getCorporatename() {
        return corporatename;
    }

    public void setCorporatename(String corporatename) {
        this.corporatename = corporatename;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getAcuntopnlince() {
        return acuntopnlince;
    }

    public void setAcuntopnlince(String acuntopnlince) {
        this.acuntopnlince = acuntopnlince;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBusplacectf() {
        return busplacectf;
    }

    public void setBusplacectf(String busplacectf) {
        this.busplacectf = busplacectf;
    }

    public String getCorporateidentity() {
        return corporateidentity;
    }

    public void setCorporateidentity(String corporateidentity) {
        this.corporateidentity = corporateidentity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
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

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }
}
