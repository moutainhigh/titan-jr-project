package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class AccountHistoryDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String payeruserid;
	private String payeeuserid;
	private String inaccountcode;
	private String outaccountcode;
	private Integer accountid;
	private String accountcode;
	private String accountname;
	private String creator;
	private Date createtime;
	private String titancode;
	private String orgname;

	// Constructors

	/** default constructor */
	public AccountHistoryDTO() {
	}

	public String getPayeruserid() {
		return payeruserid;
	}

	public void setPayeruserid(String payeruserid) {
		this.payeruserid = payeruserid;
	}

	public String getPayeeuserid() {
		return payeeuserid;
	}

	public void setPayeeuserid(String payeeuserid) {
		this.payeeuserid = payeeuserid;
	}

	public String getInaccountcode() {
		return inaccountcode;
	}

	public void setInaccountcode(String inaccountcode) {
		this.inaccountcode = inaccountcode;
	}

	public String getOutaccountcode() {
		return outaccountcode;
	}

	public void setOutaccountcode(String outaccountcode) {
		this.outaccountcode = outaccountcode;
	}

	public Integer getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getAccountcode() {
		return this.accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getAccountname() {
		return this.accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
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

	public String getTitancode() {
		return titancode;
	}

	public void setTitancode(String titancode) {
		this.titancode = titancode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
}
