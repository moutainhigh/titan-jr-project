package com.fangcang.titanjr.entity.parameter;

public class TitanBalanceInfoParam {
	private Integer accountid;
	private String finaccountid;
	private String userid;
	private String productid;
	private String accountcode;
	private String accountname;
	
	/**
	 * 账户状态1.正常，2.冻结中
	 */
	private Integer status;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getFinaccountid() {
		return finaccountid;
	}

	public void setFinaccountid(String finaccountid) {
		this.finaccountid = finaccountid;
	}

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

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
