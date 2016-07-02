package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/5/12.
 */
public class UserInfoDTO implements Serializable{
    private Integer tfsUserId;
    private String userId;
    private String orgCode;
    private String userName;
    private String userLoginName;
    private String password;
    private String department;
    private String mobilePhone;
    private String email;
    private String merchantCode;
    private String payPassword;
    private String paySalt;
    private Integer status;
    private Integer isAdmin;
    private Integer isOperator;
    private String creator;
    private Date createTime;

    private UserBindInfoDTO userBindInfoDTO;

    private List<RoleDTO> roleDTOList;

    
    public Integer getIsOperator() {
		return isOperator;
	}

	public void setIsOperator(Integer isOperator) {
		this.isOperator = isOperator;
	}

	public Integer getTfsUserId() {
        return tfsUserId;
    }

    public void setTfsUserId(Integer tfsUserId) {
        this.tfsUserId = tfsUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getPaySalt() {
        return paySalt;
    }

    public void setPaySalt(String paySalt) {
        this.paySalt = paySalt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserBindInfoDTO getUserBindInfoDTO() {
        return userBindInfoDTO;
    }

    public void setUserBindInfoDTO(UserBindInfoDTO userBindInfoDTO) {
        this.userBindInfoDTO = userBindInfoDTO;
    }

    public List<RoleDTO> getRoleDTOList() {
        return roleDTOList;
    }

    public void setRoleDTOList(List<RoleDTO> roleDTOList) {
        this.roleDTOList = roleDTOList;
    }
}
