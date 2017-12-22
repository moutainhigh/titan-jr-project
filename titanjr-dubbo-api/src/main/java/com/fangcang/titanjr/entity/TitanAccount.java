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
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getAllownopwdpay() {
		return allownopwdpay;
	}
	public void setAllownopwdpay(Integer allownopwdpay) {
		this.allownopwdpay = allownopwdpay;
	}
	public Double getNopwdpaylimit() {
		return nopwdpaylimit;
	}
	public void setNopwdpaylimit(Double nopwdpaylimit) {
		this.nopwdpaylimit = nopwdpaylimit;
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
	public Double getForzenamount() {
		return forzenamount;
	}
	public void setForzenamount(Double forzenamount) {
		this.forzenamount = forzenamount;
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
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}