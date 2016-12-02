package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 注册时修改注册资料
 * Created by zhaoshan on 2016/4/21.
 */
public class OrganRegisterUpdateRequest extends BaseRequestDTO {
	private static final long serialVersionUID = 9196081401819915277L;
	/******** 企业信息 ********/
	//公司名称，需生成公司编码
	@NotBlank
    private String orgName;
	/**
	 * 从不要从页面直接传，从session中间接获取
	 */
    private Integer orgId;
    //联系人
    private String connect;
    //联系电话
    private String mobileTel;
    //注册个人机构时必须，营业执照号
    private String buslince;
    //审核状态
    private String resultKey;
    
    /******* 个人信息 ********/
    //注册个人机构时必须，跟orgName可以一样
    //private String personName;
    //注册个人机构时必须，身份认证类型，默认身份证 = 0
    private String certificateType;
    //注册个人机构时必须，身份认证编码
    private String certificateNumber;
    
    private Integer userType;
    //可以是数组,格式：111,222,333
    private String imageid;
    
	public String getResultKey() {
		return resultKey;
	}

	public void setResultKey(String resultKey) {
		this.resultKey = resultKey;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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

//	public String getPersonName() {
//		return personName;
//	}
//
//	public void setPersonName(String personName) {
//		this.personName = personName;
//	}

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

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
    
    
   
}
