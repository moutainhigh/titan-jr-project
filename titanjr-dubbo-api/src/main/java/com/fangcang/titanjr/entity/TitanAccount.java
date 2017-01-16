package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * AbstractAccount entity provides the base persistence definition of the
 * Account entity. @author MyEclipse Persistence Tools
 */

public class TitanAccount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3270421907480612181L;
	private Integer accountid;
	private String finaccountid;
	private String userid;
	private String accountcode;
	private String accountname;
	private Integer currency;
	private Integer allownopwdpay;
	private Double nopwdpaylimit;
	private Double creditamount ;
	private Double settleamount;
	private Double forzenamount;
	private Double balanceoverlimit ; 
	private Double usableamount;
	private Double totalamount;
	
	/**
	 * 账户状态1.正常，2.冻结中
	 */
	private Integer status;
	private String creator;
	private Date createtime;
	private String modifier;
	private Date modifytime;

	// Constructors

	/** default constructor */
	public TitanAccount() {
	}
	// Property accessors

	public Integer getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getAllownopwdpay() {
		return this.allownopwdpay;
	}

	public void setAllownopwdpay(Integer allownopwdpay) {
		this.allownopwdpay = allownopwdpay;
	}

	public Double getNopwdpaylimit() {
		return this.nopwdpaylimit;
	}

	public void setNopwdpaylimit(Double nopwdpaylimit) {
		this.nopwdpaylimit = nopwdpaylimit;
	}

	public Double getTotalamount() {
		return this.totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public Double getForzenamount() {
		return this.forzenamount;
	}

	public void setForzenamount(Double forzenamount) {
		this.forzenamount = forzenamount;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getFinaccountid() {
		return finaccountid;
	}

	public void setFinaccountid(String finaccountid) {
		this.finaccountid = finaccountid;
	}

	public Double getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(Double creditamount) {
		this.creditamount = creditamount;
	}

	public Double getSettleamount() {
		return settleamount;
	}

	public void setSettleamount(Double settleamount) {
		this.settleamount = settleamount;
	}

	public Double getBalanceoverlimit() {
		return balanceoverlimit;
	}

	public void setBalanceoverlimit(Double balanceoverlimit) {
		this.balanceoverlimit = balanceoverlimit;
	}

	public Double getUsableamount() {
		return usableamount;
	}

	public void setUsableamount(Double usableamount) {
		this.usableamount = usableamount;
	}
	

}