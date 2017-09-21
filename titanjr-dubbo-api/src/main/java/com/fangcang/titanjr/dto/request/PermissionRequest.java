package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class PermissionRequest extends BaseRequestDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//SASA系统的用户id
	private String fcuserid;
	//商家编码
	private String Merchantcode;
	//泰坦云系统的id
	private String tfsuserid;
	
	//permission 1.付款权限,2.查看权限,3充值权限,4.充值和提现权限,5.理财权限,6.贷款权限,7.消息提醒权限,8.系统运营员权限
	private String permission;
	
	
	public String getMerchantcode() {
		return Merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		Merchantcode = merchantcode;
	}

	public String getTfsuserid() {
		return tfsuserid;
	}

	public void setTfsuserid(String tfsuserid) {
		this.tfsuserid = tfsuserid;
	}

	public String getFcuserid() {
		return fcuserid;
	}

	public void setFcuserid(String fcuserid) {
		this.fcuserid = fcuserid;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
