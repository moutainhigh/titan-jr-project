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
    private Integer userType;
    private Integer bindStatus;
    private String userId;
    //private Integer checkStatus;
    private String userloginname;
    private String resultKey;
    
    /**
     * 机构:1：生效，2：冻结，3：注销
     */
    private Integer statusId;

    //若当前是商家用户，则根据商家查询，判定是否有结果
    private String merchantcode;
	//注册渠道，提供对外查询可用
	private Integer regchannel;
	//合作方类型:SAAS:2,TTM:4
	private Integer coopType;
	
	private String subCertificatetype;
	private String subCertificatenumber;
	private Integer subUserType;
	private String subOrgName;
	
	//关联关系是否有效
	private Integer mapIsactive;
	

	public Integer getMapIsactive() {
		return mapIsactive;
	}

	public void setMapIsactive(Integer mapIsactive) {
		this.mapIsactive = mapIsactive;
	}

	public String getSubCertificatetype() {
		return subCertificatetype;
	}

	public void setSubCertificatetype(String subCertificatetype) {
		this.subCertificatetype = subCertificatetype;
	}

	public String getSubCertificatenumber() {
		return subCertificatenumber;
	}

	public void setSubCertificatenumber(String subCertificatenumber) {
		this.subCertificatenumber = subCertificatenumber;
	}

	public String getSubOrgName() {
		return subOrgName;
	}

	public void setSubOrgName(String subOrgName) {
		this.subOrgName = subOrgName;
	}

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
 
	public Integer getSubUserType() {
		return subUserType;
	}

	public void setSubUserType(Integer subUserType) {
		this.subUserType = subUserType;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
	
}
