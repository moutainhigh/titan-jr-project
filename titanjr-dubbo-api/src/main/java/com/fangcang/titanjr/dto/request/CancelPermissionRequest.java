package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 解除用户权限
 * @author luoqinglong
 * @2016年5月23日
 */
public class CancelPermissionRequest extends BaseRequestDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6357316795002737574L;
	@NotNull
	private Integer tfsUserId;
	private Integer roleId;
	@NotNull
	private String merchantcode;
	
	public Integer getTfsUserId() {
		return tfsUserId;
	}
	public void setTfsUserId(Integer tfsUserId) {
		this.tfsUserId = tfsUserId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getMerchantcode() {
		return merchantcode;
	}
	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}
	
}
