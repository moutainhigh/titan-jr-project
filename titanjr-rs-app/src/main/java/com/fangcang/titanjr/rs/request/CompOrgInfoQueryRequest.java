package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class CompOrgInfoQueryRequest extends BaseRequest {

    //=======都是可选字段
    //营业执照
    private String buslince;
    //法人姓名
    private String corporatename;
    //组织机构代码证
    private String organcertificate;
    //机构简称
    private String shortname;
    //状态
    private String status;
    //创建查询开始时间
    private Date createdstarttime;
    //创建查询结束时间
    private Date createdendtime;
    //开户许可证
    private String acuntopnlince;
    //公司名称
    private String companyname;
    //法人身份证
    private String corporateidentity;
    //公司编码
    private String companycode;


    @Override
    public void check() throws RSValidateException {
        //校验不能为空，只有constid不为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
    }

    public String getBuslince() {
        return buslince;
    }

    public void setBuslince(String buslince) {
        this.buslince = buslince;
    }

    public String getCorporatename() {
        return corporatename;
    }

    public void setCorporatename(String corporatename) {
        this.corporatename = corporatename;
    }

    public String getOrgancertificate() {
        return organcertificate;
    }

    public void setOrgancertificate(String organcertificate) {
        this.organcertificate = organcertificate;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedstarttime() {
        return createdstarttime;
    }

    public void setCreatedstarttime(Date createdstarttime) {
        this.createdstarttime = createdstarttime;
    }

    public String getAcuntopnlince() {
        return acuntopnlince;
    }

    public void setAcuntopnlince(String acuntopnlince) {
        this.acuntopnlince = acuntopnlince;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCorporateidentity() {
        return corporateidentity;
    }

    public void setCorporateidentity(String corporateidentity) {
        this.corporateidentity = corporateidentity;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public Date getCreatedendtime() {
        return createdendtime;
    }

    public void setCreatedendtime(Date createdendtime) {
        this.createdendtime = createdendtime;
    }
}
