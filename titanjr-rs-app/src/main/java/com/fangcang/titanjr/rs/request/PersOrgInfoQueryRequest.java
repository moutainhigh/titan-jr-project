package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

import java.util.Date;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class PersOrgInfoQueryRequest extends BaseRequest {
    //以下均是可选参数
    //中文姓名
    private String personchnname;
    //	出生日期from（格式 YYYYMMDD）
    private String birthdayfrom;
    //身份验证码
    private String certificatenumber;
    //创建时间开始from
    private Date createdtimefrom;
    //创建时间截止
    private Date createdtimeto;
    //邮件
    private String email;
    //出生日期to
    private String birthdayto;
    // 状态
    private String statusid;
    //英文姓名
    private String personengname;
    //电话
    private String mobiletel;
    //性别
    private String personsex;
    //证件类型
    private String certificatetype;

    @Override
    public void check() throws RSValidateException {
        //校验不能为空，只有constid不为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
    }

    public String getPersonchnname() {
        return personchnname;
    }

    public void setPersonchnname(String personchnname) {
        this.personchnname = personchnname;
    }

    public String getBirthdayfrom() {
        return birthdayfrom;
    }

    public void setBirthdayfrom(String birthdayfrom) {
        this.birthdayfrom = birthdayfrom;
    }

    public String getCertificatenumber() {
        return certificatenumber;
    }

    public void setCertificatenumber(String certificatenumber) {
        this.certificatenumber = certificatenumber;
    }

    public Date getCreatedtimefrom() {
        return createdtimefrom;
    }

    public void setCreatedtimefrom(Date createdtimefrom) {
        this.createdtimefrom = createdtimefrom;
    }

    public Date getCreatedtimeto() {
        return createdtimeto;
    }

    public void setCreatedtimeto(Date createdtimeto) {
        this.createdtimeto = createdtimeto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdayto() {
        return birthdayto;
    }

    public void setBirthdayto(String birthdayto) {
        this.birthdayto = birthdayto;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getPersonengname() {
        return personengname;
    }

    public void setPersonengname(String personengname) {
        this.personengname = personengname;
    }

    public String getMobiletel() {
        return mobiletel;
    }

    public void setMobiletel(String mobiletel) {
        this.mobiletel = mobiletel;
    }

    public String getPersonsex() {
        return personsex;
    }

    public void setPersonsex(String personsex) {
        this.personsex = personsex;
    }

    public String getCertificatetype() {
        return certificatetype;
    }

    public void setCertificatetype(String certificatetype) {
        this.certificatetype = certificatetype;
    }
}
