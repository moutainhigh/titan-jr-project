package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/***
 * 注册子机构(真实证件注册)
 * @author luoqinglong
 *
 */
public class RegOrgSubRequest extends BaseRequestDTO implements Serializable {
	
	//机构名称
	@NotBlank
    private String orgName;
    
    private String orgCode;
    //联系人
    private String connect;
    //联系电话
    private String mobileTel;
    //注册个人机构时必须，营业执照号
    private String buslince;

    //注册个人机构时必须，身份认证类型，默认身份证 = 0
    private String certificateType;
    //注册个人机构时必须，身份认证编码
    private String certificateNumber;
    @NotNull
    private Integer userType;
    //是否为添加子机构
    private boolean isAddOrgSub = false;
    
    
	public boolean getIsAddOrgSub() {
		return isAddOrgSub;
	}
	public void setIsAddOrgSub(boolean isAddOrgSub) {
		this.isAddOrgSub = isAddOrgSub;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
    
}
