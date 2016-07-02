package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class OrgBindInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//绑定的商家编码
    private String merchantCode;
    private String merchantName;
    //1.已绑定，0.未绑定
    private Integer bindStatus;
    private String orgcode;

    private String userid;
    
    
	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
