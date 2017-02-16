package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;


/**
 * Created by zhaoshan on 2016/3/30.
 */
public class FinancialOrganQueryRequest extends BaseRequestDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6211572158966127160L;
	private String regStartTime;
    private String regEndTime;
   
    private Integer orgId;
    private String orgCode;
    private Integer bindStatus;
    /***
     * 公司名称
     */
    private String orgName;
    private String userId;
    
    //private Integer checkStatus;
    private String userloginname;
    private Integer isadmin;
    /**
     * 是否为运营人员
     */
    private Integer isoperator;
    private String resultKey;
    private Integer orgType;
    private Integer userType;
    /**
     * 机构:1：生效，2：冻结，3：注销
     */
    private Integer statusId;


    //若当前是商家用户，则根据商家查询，判定是否有结果
    private String merchantcode;

	//注册渠道，提供对外查询可用
	private Integer regchannel;
    
    public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}

	public Integer getIsoperator() {
		return isoperator;
	}

	public void setIsoperator(Integer isoperator) {
		this.isoperator = isoperator;
	}

	public String getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(String regStartTime) {
        this.regStartTime = regStartTime;
    }

    public String getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(String regEndTime) {
        this.regEndTime = regEndTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBindStatus() {
		return bindStatus;
	}

	public void setBindStatus(Integer bindStatus) {
		this.bindStatus = bindStatus;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getUserloginname() {
		return userloginname;
	}

	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}

	public String getResultKey() {
		return resultKey;
	}

	public void setResultKey(String resultKey) {
		this.resultKey = resultKey;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getRegchannel() {
		return regchannel;
	}

	public void setRegchannel(Integer regchannel) {
		this.regchannel = regchannel;
	}
}
