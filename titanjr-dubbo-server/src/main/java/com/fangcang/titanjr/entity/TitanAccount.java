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
	private String userid;
	private String productid;
	private String accountcode;
	private String accountname;
	/**
	 * 币种：1-人民币
	 */
	private Integer currency;
	/**
	 * 期初贷记余额
	 */
	private Double initcreditamount ;
	/**
	 * 期初提现余额
	 */
	private Double initsettleamount;
	/**
	 * 期初冻结余额
	 */
	private Double initfrozonamount;
	
	
	/**
	 * 期初可信用消费余额
	 */
	private Double initoverlimit ; 
	/**
	 * 期初可用余额
	 */
	private Double initusablelimit;
	/**
	 * 期初账户余额
	 */
	private Double inittotalamount;
	
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
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Double getInitcreditamount() {
		return initcreditamount;
	}
	public void setInitcreditamount(Double initcreditamount) {
		this.initcreditamount = initcreditamount;
	}
	public Double getInitsettleamount() {
		return initsettleamount;
	}
	public void setInitsettleamount(Double initsettleamount) {
		this.initsettleamount = initsettleamount;
	}
	public Double getInitfrozonamount() {
		return initfrozonamount;
	}
	public void setInitfrozonamount(Double initfrozonamount) {
		this.initfrozonamount = initfrozonamount;
	}
	public Double getInitoverlimit() {
		return initoverlimit;
	}
	public void setInitoverlimit(Double initoverlimit) {
		this.initoverlimit = initoverlimit;
	}
	public Double getInitusablelimit() {
		return initusablelimit;
	}
	public void setInitusablelimit(Double initusablelimit) {
		this.initusablelimit = initusablelimit;
	}
	public Double getInittotalamount() {
		return inittotalamount;
	}
	public void setInittotalamount(Double inittotalamount) {
		this.inittotalamount = inittotalamount;
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