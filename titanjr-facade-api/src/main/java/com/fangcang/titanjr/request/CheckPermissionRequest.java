package com.fangcang.titanjr.request;

import java.io.Serializable;

public class CheckPermissionRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//商家编码 .==请和【fcuserid】 一起传过来
	private String merchantcode;
	//房仓用户id
	private String fcuserid;
	
	//permission 1.付款权限,2.查看权限,3充值权限,4.充值和提现权限,5.理财权限,6.贷款权限,7.消息提醒权限,8.系统运营员权限
	private String permission;
	
	
	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
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
