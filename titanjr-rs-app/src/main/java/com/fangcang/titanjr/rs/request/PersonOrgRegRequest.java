package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.util.MyBeanUtil;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class PersonOrgRegRequest extends BaseRequest {

    //===========必填项目
    //操作类型（1：新增，2：修改）
    private String opertype = "1";
    //中文姓名（当操作类型是2：修改时，此项目为可选）
    private String personchnname;
    //证件号（当操作类型是2：修改时，此项目为可选）
    private String certificatenumber;
    //证件类型,0身份证;1护照;2军官证;3士兵证;4回乡证;
    // 5户口本;6外国护照;7其它（当操作类型是2：修改时，此项目为可选）
    private String certificatetype;

    //===========可选项目=======
    //生日
    private String birthday;
    //电话
    private String mobiletel;
    //地址
    private String address;
    //备注
    private String remark;
    //类型，默认1，现在也只有1
    private String persontype;
    //用户名
    private String username;
    //英文名
    private String personengname;
    //固定电话号码
    private String fixtel;
    //邮编
    private String post;
    //角色
    private String role;
    //邮箱
    private String email;
    //性别（1：女，2：男）
    private String personsex;

    @Override
    public void check() throws RSValidateException {
        //校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.personchnname,"personchnname");
        RequestValidationUtil.checkNotEmpty(this.certificatenumber, "certificatenumber");
        RequestValidationUtil.checkNotEmpty(this.certificatetype,"certificatetype");
    }

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public String getPersonchnname() {
        return personchnname;
    }

    public void setPersonchnname(String personchnname) {
        this.personchnname = personchnname;
    }

    public String getCertificatenumber() {
        return certificatenumber;
    }

    public void setCertificatenumber(String certificatenumber) {
        this.certificatenumber = certificatenumber;
    }

    public String getCertificatetype() {
        return certificatetype;
    }

    public void setCertificatetype(String certificatetype) {
        this.certificatetype = certificatetype;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobiletel() {
        return mobiletel;
    }

    public void setMobiletel(String mobiletel) {
        this.mobiletel = mobiletel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonengname() {
        return personengname;
    }

    public void setPersonengname(String personengname) {
        this.personengname = personengname;
    }

    public String getFixtel() {
        return fixtel;
    }

    public void setFixtel(String fixtel) {
        this.fixtel = fixtel;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonsex() {
        return personsex;
    }

    public void setPersonsex(String personsex) {
        this.personsex = personsex;
    }

    public static void main(String[] args) {
        PersonOrgRegRequest req1 = new PersonOrgRegRequest();
        req1.setAddress("dsfdsgds");
        PersonOrgUpdateRequest req2 = new PersonOrgUpdateRequest();
        MyBeanUtil.copyProperties(req2,req1);
        System.out.println(req2);
    }
}
