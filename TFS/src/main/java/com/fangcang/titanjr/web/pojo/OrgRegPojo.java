package com.fangcang.titanjr.web.pojo;

/**
 * 注册信息
 * @author luoqinglong
 * @2016年6月7日
 */
public class OrgRegPojo {
	/**
	 * 公司名称
	 */
	private String orgName;
	/**
	 * 营业执照号
	 */
	private String buslince;
	/**
	 * 营业执照号码
	 */
	private String imgIds;
	/**
	 * 联系人短信验证码
	 */
	private String smsRegCode;
	/**
	 * 证件类型：身份证，士官证
	 */
	private Integer certificatetype;
	/**
	 * 证件号，如：身份证，士官证
	 */
	private String certificatenumber;
	/**
	 * 联系手机
	 */
	private String mobiletel;
	/***
	 * 联系人
	 */
	private String connect;
	
	private int userType;
	
	private String orgId;
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	public String getImgIds() {
		return imgIds;
	}
	public void setImgIds(String imgIds) {
		this.imgIds = imgIds;
	}
	public String getSmsRegCode() {
		return smsRegCode;
	}
	public void setSmsRegCode(String smsRegCode) {
		this.smsRegCode = smsRegCode;
	}
	public Integer getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(Integer certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getCertificatenumber() {
		return certificatenumber;
	}
	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}
	public String getMobiletel() {
		return mobiletel;
	}
	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	
	
}
