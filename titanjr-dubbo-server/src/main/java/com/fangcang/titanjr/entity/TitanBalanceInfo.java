package com.fangcang.titanjr.entity;
// default package

import java.util.Date;


public class TitanBalanceInfo implements java.io.Serializable {

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
	private Long creditamount ;
	/**
	 * 期初提现余额
	 */
	private Long settleamount;
	/**
	 * 期初冻结余额
	 */
	private Long frozonamount;
	
	
	/**
	 * 期初可信用消费余额
	 */
	private Long overlimit ; 
	/**
	 * 期初可用余额
	 */
	private Long usablelimit;
	/**
	 * 期初账户余额
	 */
	private Long totalamount;
	
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
	
	public Long getCreditamount() {
		return creditamount;
	}
	public void setCreditamount(Long creditamount) {
		this.creditamount = creditamount;
	}
	public Long getSettleamount() {
		return settleamount;
	}
	public void setSettleamount(Long settleamount) {
		this.settleamount = settleamount;
	}
	public Long getFrozonamount() {
		return frozonamount;
	}
	public void setFrozonamount(Long frozonamount) {
		this.frozonamount = frozonamount;
	}
	public Long getOverlimit() {
		return overlimit;
	}
	public void setOverlimit(Long overlimit) {
		this.overlimit = overlimit;
	}
	public Long getUsablelimit() {
		return usablelimit;
	}
	public void setUsablelimit(Long usablelimit) {
		this.usablelimit = usablelimit;
	}
	public Long getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Long totalamount) {
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