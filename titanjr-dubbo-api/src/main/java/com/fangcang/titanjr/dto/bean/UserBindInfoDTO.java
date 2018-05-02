package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 绑定DTO
 * Created by zhaoshan on 2016/5/12.
 */
public class UserBindInfoDTO  implements Serializable {

    private Long fcUserId;
    private String userName;
    private String loginName;
    private String fcUserName;
    private String fcLoginName;
    private String merchantCode;
    private Integer coopType;
    private Integer isActive;

    public Long getFcUserId() {
        return fcUserId;
    }

    public void setFcUserId(Long fcUserId) {
        this.fcUserId = fcUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFcUserName() {
        return fcUserName;
    }

    public void setFcUserName(String fcUserName) {
        this.fcUserName = fcUserName;
    }

    public String getFcLoginName() {
        return fcLoginName;
    }

    public void setFcLoginName(String fcLoginName) {
        this.fcLoginName = fcLoginName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
    
    
}
