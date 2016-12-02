package com.fangcang.titanjr.dto.request;

import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class UpdateUserRequest extends BaseRequestDTO{
	
	private static final long serialVersionUID = 1049821063474643008L;
	/**
	 * 金服员工姓名
	 */
	private String userName;
	/***
	 * 金服id
	 */
	private Integer tfsUserId;
	/**
	 * 选中生效的角色
	 */
    private List<Long> roleIdList;
    /**
     * 需要取消的角色
     */
    private List<Long> unselectRoleIdList;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getTfsUserId() {
		return tfsUserId;
	}
	public void setTfsUserId(Integer tfsUserId) {
		this.tfsUserId = tfsUserId;
	}
	public List<Long> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
	public List<Long> getUnselectRoleIdList() {
		return unselectRoleIdList;
	}
	public void setUnselectRoleIdList(List<Long> unselectRoleIdList) {
		this.unselectRoleIdList = unselectRoleIdList;
	}
}
