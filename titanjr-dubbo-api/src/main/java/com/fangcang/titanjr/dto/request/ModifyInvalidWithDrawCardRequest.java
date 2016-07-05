package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.BaseRequestDTO;

public class ModifyInvalidWithDrawCardRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//信息主键
    private String accountid;
    
    //用户类型(1：商户，2：普通用户)
    private String usertype;
    
    private String userid;
    //机构码
    private String constid = CommonConstant.RS_FANGCANG_PRODUCT_ID;
    // 产品号
    private String productid = CommonConstant.RS_FANGCANG_PRODUCT_ID;
    
    private String role;
    private String certificatetype;
    //	持卡人真实姓名
    private String accountrealname;
    private String hankheadname;
    private String hankbranchname;
    private String hankbranch;
    private String bankcity;
    private String bankprovinec;
    private String accountnumber;
    private String bankhead;
    
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getConstid() {
		return constid;
	}
	public void setConstid(String constid) {
		this.constid = constid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getAccountrealname() {
		return accountrealname;
	}
	public void setAccountrealname(String accountrealname) {
		this.accountrealname = accountrealname;
	}
	public String getHankheadname() {
		return hankheadname;
	}
	public void setHankheadname(String hankheadname) {
		this.hankheadname = hankheadname;
	}
	public String getHankbranchname() {
		return hankbranchname;
	}
	public void setHankbranchname(String hankbranchname) {
		this.hankbranchname = hankbranchname;
	}
	public String getHankbranch() {
		return hankbranch;
	}
	public void setHankbranch(String hankbranch) {
		this.hankbranch = hankbranch;
	}
	public String getBankcity() {
		return bankcity;
	}
	public void setBankcity(String bankcity) {
		this.bankcity = bankcity;
	}
	public String getBankprovinec() {
		return bankprovinec;
	}
	public void setBankprovinec(String bankprovinec) {
		this.bankprovinec = bankprovinec;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getBankhead() {
		return bankhead;
	}
	public void setBankhead(String bankhead) {
		this.bankhead = bankhead;
	}
}
