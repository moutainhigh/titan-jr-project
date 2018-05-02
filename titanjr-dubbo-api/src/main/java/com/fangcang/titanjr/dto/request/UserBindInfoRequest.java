package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class UserBindInfoRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398897010622963630L;
	private Integer tfsuserid;
	private Long fcuserid;
	private String loginname;
	private String fcloginname;
	private Integer cooptype;
	private String merchantcode;
	private Integer isactive;
	
	public Integer getTfsuserid() {
		return tfsuserid;
	}
	public void setTfsuserid(Integer tfsuserid) {
		this.tfsuserid = tfsuserid;
	}
	public Long getFcuserid() {
		return fcuserid;
	}
	public void setFcuserid(Long fcuserid) {
		this.fcuserid = fcuserid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public String getFcloginname() {
		return fcloginname;
	}
	public void setFcloginname(String fcloginname) {
		this.fcloginname = fcloginname;
	}
	public String getMerchantcode() {
		return merchantcode;
	}
	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public Integer getCooptype() {
		return cooptype;
	}
	public void setCooptype(Integer cooptype) {
		this.cooptype = cooptype;
	}
	
	
}
