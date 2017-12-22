package com.fangcang.titanjr.entity;
// default package

import java.util.Date;


public class TitanBalanceInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3270421907480612181L;
	private Integer accountid;
	private String finaccountid;
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
	private Long initcreditamount ;
	/**
	 * 期初提现余额
	 */
	private Long initsettleamount;
	/**
	 * 期初冻结余额
	 */
	private Long initfrozonamount;
	
	
	/**
	 * 期初可信用消费余额
	 */
	private Long initoverlimit ; 
	/**
	 * 期初可用余额
	 */
	private Long initusablelimit;
	/**
	 * 期初账户余额
	 */
	private Long inittotalamount;
	
	/**
	 * 账户状态1.正常，2.冻结中
	 */
	private Integer status;
	private String creator;
	private Date createtime;
	private String modifier;
	private Date modifytime;
	
	
	public String getFinaccountid() {
		return finaccountid;
	}
	public void setFinaccountid(String finaccountid) {
		this.finaccountid = finaccountid;
	}
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
	public Long getInitcreditamount() {
		return initcreditamount;
	}
	public void setInitcreditamount(Long initcreditamount) {
		this.initcreditamount = initcreditamount;
	}
	public Long getInitsettleamount() {
		return initsettleamount;
	}
	public void setInitsettleamount(Long initsettleamount) {
		this.initsettleamount = initsettleamount;
	}
	public Long getInitfrozonamount() {
		return initfrozonamount;
	}
	public void setInitfrozonamount(Long initfrozonamount) {
		this.initfrozonamount = initfrozonamount;
	}
	public Long getInitoverlimit() {
		return initoverlimit;
	}
	public void setInitoverlimit(Long initoverlimit) {
		this.initoverlimit = initoverlimit;
	}
	public Long getInitusablelimit() {
		return initusablelimit;
	}
	public void setInitusablelimit(Long initusablelimit) {
		this.initusablelimit = initusablelimit;
	}
	public Long getInittotalamount() {
		return inittotalamount;
	}
	public void setInittotalamount(Long inittotalamount) {
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