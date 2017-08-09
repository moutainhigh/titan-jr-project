package com.fangcang.titanjr.dto.request;

public class RegOrgForOpenRequest {
	private String orgName;
	private Integer coopType;
	private Integer usertype;
	private String userloginname;
	/**
	 * 合作方用户信息
	 */
	//房仓登录用户名
    private String fcLoginUserName;
    //合作方的用户id
    private String coopUserId;
    //商家编码或者第三方的编码
    private String  merchantCode;
    //商家名称
    private String merchantname;
    
    
	public String getUserloginname() {
		return userloginname;
	}
	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getCoopType() {
		return coopType;
	}
	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public String getFcLoginUserName() {
		return fcLoginUserName;
	}
	public void setFcLoginUserName(String fcLoginUserName) {
		this.fcLoginUserName = fcLoginUserName;
	}
	public String getCoopUserId() {
		return coopUserId;
	}
	public void setCoopUserId(String coopUserId) {
		this.coopUserId = coopUserId;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMerchantname() {
		return merchantname;
	}
	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}
    
    
}
