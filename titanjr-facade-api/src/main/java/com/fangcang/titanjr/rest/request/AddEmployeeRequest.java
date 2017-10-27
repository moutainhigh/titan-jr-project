package com.fangcang.titanjr.rest.request;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.request.BaseRequest;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AddEmployeeRequest", description = "添加员工请求参数")
public class AddEmployeeRequest extends BaseRequest{
	
	@ApiModelProperty(value = "合作方登录用户名",required = true)
    @NotBlank
	private String coopLoginUserName;
	
	@ApiModelProperty(value = "合作方用户id",required = true)
    @NotBlank
	private String coopUserId;
	
	@ApiModelProperty(value = "合作方用户id，之前已经添加到金融",required = true)
    @NotBlank
	private String coopUserIdExist;
	
	@ApiModelProperty(value = "合作方机用户姓名",required = true)
	private String coopUserName;
	
	@ApiModelProperty(value = "合作方机构编码",required = true)
    @NotBlank
	private String coopOrgCode;
	
	@ApiModelProperty(value = "金融登录用户名,邮箱或者手机号",required = true)
    @NotBlank
	private String loginUserName;
	
	@ApiModelProperty(value = "金融机构编码",required = true)
    @NotBlank
	private String orgCode;
	
	@ApiModelProperty(value = "金融用户权限码,格式：1|2|3|4",required = true)
    @NotBlank
	private String roleCode;
	
	//private String unselectRoleIdList;
	
	@ApiModelProperty(value = "金融用户登录密码")
	private String password;
	//private String cooptype;

	public String getCoopLoginUserName() {
		return coopLoginUserName;
	}

	public void setCoopLoginUserName(String coopLoginUserName) {
		this.coopLoginUserName = coopLoginUserName;
	}

	public String getCoopUserId() {
		return coopUserId;
	}

	public void setCoopUserId(String coopUserId) {
		this.coopUserId = coopUserId;
	}

	public String getCoopUserName() {
		return coopUserName;
	}

	public void setCoopUserName(String coopUserName) {
		this.coopUserName = coopUserName;
	}

	public String getCoopOrgCode() {
		return coopOrgCode;
	}

	public void setCoopOrgCode(String coopOrgCode) {
		this.coopOrgCode = coopOrgCode;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCoopUserIdExist() {
		return coopUserIdExist;
	}

	public void setCoopUserIdExist(String coopUserIdExist) {
		this.coopUserIdExist = coopUserIdExist;
	}
	
}
