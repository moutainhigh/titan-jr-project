package com.fangcang.titanjr.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;
;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class UserRegisterRequest extends BaseRequestDTO{
    //注册来源，LoginSourceEnum
	@NotNull
    private Integer registerSource;
	@NotBlank
    private String userId;
	@NotBlank
    private String loginUserName;
    private String password;

    private String mobilePhone;

    private String fcLoginUserName;
    /**
     * 合作方的用户id
     */
    private String coopUserId;
    private String merchantCode;

    private String orgCode;

    @NotNull
    private Integer isAdminUser;
    
    /**
     * 金服姓名
     */
    private String userName;
	/**
	 * 选中生效的角色
	 */
    private List<Long> roleIdList;
    /**
     * 需要取消的角色
     */
    private List<Long> unselectRoleIdList;

    public Integer getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getCoopUserId() {
		return coopUserId;
	}

	public void setCoopUserId(String coopUserId) {
		this.coopUserId = coopUserId;
	}

	public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Integer getIsAdminUser() {
        return isAdminUser;
    }

    public void setIsAdminUser(Integer isAdminUser) {
        this.isAdminUser = isAdminUser;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getFcLoginUserName() {
        return fcLoginUserName;
    }

    public void setFcLoginUserName(String fcLoginUserName) {
        this.fcLoginUserName = fcLoginUserName;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Long> getUnselectRoleIdList() {
		return unselectRoleIdList;
	}

	public void setUnselectRoleIdList(List<Long> unselectRoleIdList) {
		this.unselectRoleIdList = unselectRoleIdList;
	}

}