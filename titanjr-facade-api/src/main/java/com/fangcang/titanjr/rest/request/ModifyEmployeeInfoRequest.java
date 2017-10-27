package com.fangcang.titanjr.rest.request;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.request.BaseRequest;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ModifyEmployeeInfoRequest", description = "修改员工基本信息请求参数")
public class ModifyEmployeeInfoRequest extends BaseRequest {
	
	@ApiModelProperty(value = "合作方用户id",required = true)
    @NotBlank
	private String coopUserId;
	
	@ApiModelProperty(value = "合作方机构编码",required = true)
    @NotBlank
	private String coopOrgCode;
	
	@ApiModelProperty(value = "合作方机用户姓名",required = true)
	private String coopUserName;
	
	@ApiModelProperty(value = "金融用户权限码,格式：1|2|3|4",required = true)
    @NotBlank
	private String roleCode;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
}
