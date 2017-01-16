package com.fangcang.titanjr.web.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新金服员工
 * @author luoqinglong
 * @2016年6月17日
 */
public class EmployeePojo extends BasePojo{
	private String userName;
	private Integer tfsUserId;
	
	@NotBlank
	private String receiveAddress;
	@NotBlank
	private String code;
	/**
	 * 选中的角色id,格式：1,2,3,4
	 */
	private String checkedRoleId;
	/**
	 * 未选中的角色id,格式：1,2,3,4
	 */
	private String uncheckedRoleId;
	
	public Integer getTfsUserId() {
		return tfsUserId;
	}
	public void setTfsUserId(Integer tfsUserId) {
		this.tfsUserId = tfsUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCheckedRoleId() {
		return checkedRoleId;
	}
	public void setCheckedRoleId(String checkedRoleId) {
		this.checkedRoleId = checkedRoleId;
	}
	public String getUncheckedRoleId() {
		return uncheckedRoleId;
	}
	public void setUncheckedRoleId(String uncheckedRoleId) {
		this.uncheckedRoleId = uncheckedRoleId;
	}
	
}
