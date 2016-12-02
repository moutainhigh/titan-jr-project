package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class SaaSUserRoleRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1365132234273218720L;
	private String merchantCode;
	
	private Integer fcUserId;
	 
    /**
     * saas用户姓名
     */
    private String saasUserName;
	
	public String getMerchantCode() {
		return merchantCode;
	}

	
	public Integer getFcUserId() {
		return fcUserId;
	}


	public void setFcUserId(Integer fcUserId) {
		this.fcUserId = fcUserId;
	}


	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}


	public String getSaasUserName() {
		return saasUserName;
	}

	public void setSaasUserName(String saasUserName) {
		this.saasUserName = saasUserName;
	}
	
	
}
