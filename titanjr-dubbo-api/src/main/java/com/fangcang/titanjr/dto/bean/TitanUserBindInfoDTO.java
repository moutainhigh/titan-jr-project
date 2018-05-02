package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class TitanUserBindInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private Integer tfsuserid;
	private Long fcuserid;
	private String username;
	private String loginname;
	private String fcusername;
	private String fcloginname;
	private String merchantcode;
	private Integer cooptype;
	private Integer isactive;
	private String creator;
	private Date createtime;
	private String modifior;
	private Date modifytime;


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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getFcusername() {
		return this.fcusername;
	}

	public void setFcusername(String fcusername) {
		this.fcusername = fcusername;
	}

	public String getFcloginname() {
		return this.fcloginname;
	}

	public void setFcloginname(String fcloginname) {
		this.fcloginname = fcloginname;
	}

	public String getMerchantcode() {
		return this.merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public Integer getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getModifior() {
		return this.modifior;
	}

	public void setModifior(String modifior) {
		this.modifior = modifior;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Integer getCooptype() {
		return cooptype;
	}

	public void setCooptype(Integer cooptype) {
		this.cooptype = cooptype;
	}
	
}
