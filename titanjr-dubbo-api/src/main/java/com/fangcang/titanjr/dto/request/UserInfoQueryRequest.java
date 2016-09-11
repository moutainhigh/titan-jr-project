package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 金服用户查询
 * Created by zhaoshan on 2016/4/25.
 */
public class UserInfoQueryRequest extends BaseRequestDTO {

    //房仓二级机构标识符
    private String userId;
    //泰坦金服用户id
    private Integer tfsUserId;
    //金服用户登录名
    private String userLoginName;
    //房仓绑定用户名
    private String bindLoginName;
    //房仓绑定商家编码
    private String bindMerchantCode;
    //金服用户名
    private String userName;
    //手机号
    private String mobilePhone;
    
    private String orgCode;
    
    private Integer status;
    /**
     * 是否是管理员
     */
    private Integer isadmin;
    /**
     * 要排除的状态
     */
    private Integer excludeStatus;

    
    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Integer getTfsUserId() {
		return tfsUserId;
	}

	public void setTfsUserId(Integer tfsUserId) {
		this.tfsUserId = tfsUserId;
	}

	public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getBindLoginName() {
        return bindLoginName;
    }

    public void setBindLoginName(String bindLoginName) {
        this.bindLoginName = bindLoginName;
    }

    public String getBindMerchantCode() {
        return bindMerchantCode;
    }

    public void setBindMerchantCode(String bindMerchantCode) {
        this.bindMerchantCode = bindMerchantCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

	public Integer getExcludeStatus() {
		return excludeStatus;
	}

	public void setExcludeStatus(Integer excludeStatus) {
		this.excludeStatus = excludeStatus;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
}
