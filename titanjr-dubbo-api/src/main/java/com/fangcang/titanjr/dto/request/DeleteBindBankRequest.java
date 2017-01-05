package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class DeleteBindBankRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 //可选，暂不关注
    private Long referuserid;
    //角色可选
    private String role;
    //用户类型(1：商户，2：普通用户)
    private String usertype;
    //银行卡号
    private String accountnumber;
    
    private String userid;
    
    private String productid;
    
    private String constid;
    
    
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getConstid() {
		return constid;
	}
	public void setConstid(String constid) {
		this.constid = constid;
	}
	public Long getReferuserid() {
		return referuserid;
	}
	public void setReferuserid(Long referuserid) {
		this.referuserid = referuserid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
    
}
