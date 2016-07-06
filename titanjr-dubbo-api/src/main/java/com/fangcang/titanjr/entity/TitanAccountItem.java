package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanAccountItemId entity. @author MyEclipse Persistence Tools
 */

public class TitanAccountItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411214977009352369L;
	private String userid;
	private Date accountdate;
	private String finaccountid;
	private Double amount;
	private Integer direction;
	private Double balanceusable;
	private Double balancesettle;
	private Double balancefrozon;
	private Double balanceoverlimit;
	private Double balancecredit;
	private Integer reverseflag;
	private String referid;
	private Double paymentamount;
	private Date transdate;
	private String remark;
	private Date createdtime;
	private Date updatedtime;
	private String rootinstcd;
	private String funcode;
	private String productid;
	private String busitypeid;

	// Constructors

	/** default constructor */
	public TitanAccountItem() {
	}

	/** minimal constructor */
	public TitanAccountItem(String userid) {
		this.userid = userid;
	}

	/** full constructor */
	public TitanAccountItem(String userid, Date accountdate,
			String finaccountid, Double amount, Integer direction,
			Double balanceusable, Double balancesettle, Double balancefrozon,
			Double balanceoverlimit, Double balancecredit, Integer reverseflag,
			String referid, Double paymentamount, Date transdate,
			String remark, Date createdtime, Date updatedtime,
			String rootinstcd, String funcode, String productid,
			String busitypeid) {
		this.userid = userid;
		this.accountdate = accountdate;
		this.finaccountid = finaccountid;
		this.amount = amount;
		this.direction = direction;
		this.balanceusable = balanceusable;
		this.balancesettle = balancesettle;
		this.balancefrozon = balancefrozon;
		this.balanceoverlimit = balanceoverlimit;
		this.balancecredit = balancecredit;
		this.reverseflag = reverseflag;
		this.referid = referid;
		this.paymentamount = paymentamount;
		this.transdate = transdate;
		this.remark = remark;
		this.createdtime = createdtime;
		this.updatedtime = updatedtime;
		this.rootinstcd = rootinstcd;
		this.funcode = funcode;
		this.productid = productid;
		this.busitypeid = busitypeid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getAccountdate() {
		return this.accountdate;
	}

	public void setAccountdate(Date accountdate) {
		this.accountdate = accountdate;
	}

	public String getFinaccountid() {
		return this.finaccountid;
	}

	public void setFinaccountid(String finaccountid) {
		this.finaccountid = finaccountid;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getDirection() {
		return this.direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Double getBalanceusable() {
		return this.balanceusable;
	}

	public void setBalanceusable(Double balanceusable) {
		this.balanceusable = balanceusable;
	}

	public Double getBalancesettle() {
		return this.balancesettle;
	}

	public void setBalancesettle(Double balancesettle) {
		this.balancesettle = balancesettle;
	}

	public Double getBalancefrozon() {
		return this.balancefrozon;
	}

	public void setBalancefrozon(Double balancefrozon) {
		this.balancefrozon = balancefrozon;
	}

	public Double getBalanceoverlimit() {
		return this.balanceoverlimit;
	}

	public void setBalanceoverlimit(Double balanceoverlimit) {
		this.balanceoverlimit = balanceoverlimit;
	}

	public Double getBalancecredit() {
		return this.balancecredit;
	}

	public void setBalancecredit(Double balancecredit) {
		this.balancecredit = balancecredit;
	}

	public Integer getReverseflag() {
		return this.reverseflag;
	}

	public void setReverseflag(Integer reverseflag) {
		this.reverseflag = reverseflag;
	}

	public String getReferid() {
		return this.referid;
	}

	public void setReferid(String referid) {
		this.referid = referid;
	}

	public Double getPaymentamount() {
		return this.paymentamount;
	}

	public void setPaymentamount(Double paymentamount) {
		this.paymentamount = paymentamount;
	}

	public Date getTransdate() {
		return this.transdate;
	}

	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Date getUpdatedtime() {
		return this.updatedtime;
	}

	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

	public String getRootinstcd() {
		return this.rootinstcd;
	}

	public void setRootinstcd(String rootinstcd) {
		this.rootinstcd = rootinstcd;
	}

	public String getFuncode() {
		return this.funcode;
	}

	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}

	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getBusitypeid() {
		return this.busitypeid;
	}

	public void setBusitypeid(String busitypeid) {
		this.busitypeid = busitypeid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TitanAccountItem))
			return false;
		TitanAccountItem castOther = (TitanAccountItem) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getAccountdate() == castOther.getAccountdate()) || (this
						.getAccountdate() != null
						&& castOther.getAccountdate() != null && this
						.getAccountdate().equals(castOther.getAccountdate())))
				&& ((this.getFinaccountid() == castOther.getFinaccountid()) || (this
						.getFinaccountid() != null
						&& castOther.getFinaccountid() != null && this
						.getFinaccountid().equals(castOther.getFinaccountid())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())))
				&& ((this.getDirection() == castOther.getDirection()) || (this
						.getDirection() != null
						&& castOther.getDirection() != null && this
						.getDirection().equals(castOther.getDirection())))
				&& ((this.getBalanceusable() == castOther.getBalanceusable()) || (this
						.getBalanceusable() != null
						&& castOther.getBalanceusable() != null && this
						.getBalanceusable()
						.equals(castOther.getBalanceusable())))
				&& ((this.getBalancesettle() == castOther.getBalancesettle()) || (this
						.getBalancesettle() != null
						&& castOther.getBalancesettle() != null && this
						.getBalancesettle()
						.equals(castOther.getBalancesettle())))
				&& ((this.getBalancefrozon() == castOther.getBalancefrozon()) || (this
						.getBalancefrozon() != null
						&& castOther.getBalancefrozon() != null && this
						.getBalancefrozon()
						.equals(castOther.getBalancefrozon())))
				&& ((this.getBalanceoverlimit() == castOther
						.getBalanceoverlimit()) || (this.getBalanceoverlimit() != null
						&& castOther.getBalanceoverlimit() != null && this
						.getBalanceoverlimit().equals(
								castOther.getBalanceoverlimit())))
				&& ((this.getBalancecredit() == castOther.getBalancecredit()) || (this
						.getBalancecredit() != null
						&& castOther.getBalancecredit() != null && this
						.getBalancecredit()
						.equals(castOther.getBalancecredit())))
				&& ((this.getReverseflag() == castOther.getReverseflag()) || (this
						.getReverseflag() != null
						&& castOther.getReverseflag() != null && this
						.getReverseflag().equals(castOther.getReverseflag())))
				&& ((this.getReferid() == castOther.getReferid()) || (this
						.getReferid() != null && castOther.getReferid() != null && this
						.getReferid().equals(castOther.getReferid())))
				&& ((this.getPaymentamount() == castOther.getPaymentamount()) || (this
						.getPaymentamount() != null
						&& castOther.getPaymentamount() != null && this
						.getPaymentamount()
						.equals(castOther.getPaymentamount())))
				&& ((this.getTransdate() == castOther.getTransdate()) || (this
						.getTransdate() != null
						&& castOther.getTransdate() != null && this
						.getTransdate().equals(castOther.getTransdate())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null && castOther.getRemark() != null && this
						.getRemark().equals(castOther.getRemark())))
				&& ((this.getCreatedtime() == castOther.getCreatedtime()) || (this
						.getCreatedtime() != null
						&& castOther.getCreatedtime() != null && this
						.getCreatedtime().equals(castOther.getCreatedtime())))
				&& ((this.getUpdatedtime() == castOther.getUpdatedtime()) || (this
						.getUpdatedtime() != null
						&& castOther.getUpdatedtime() != null && this
						.getUpdatedtime().equals(castOther.getUpdatedtime())))
				&& ((this.getRootinstcd() == castOther.getRootinstcd()) || (this
						.getRootinstcd() != null
						&& castOther.getRootinstcd() != null && this
						.getRootinstcd().equals(castOther.getRootinstcd())))
				&& ((this.getFuncode() == castOther.getFuncode()) || (this
						.getFuncode() != null && castOther.getFuncode() != null && this
						.getFuncode().equals(castOther.getFuncode())))
				&& ((this.getProductid() == castOther.getProductid()) || (this
						.getProductid() != null
						&& castOther.getProductid() != null && this
						.getProductid().equals(castOther.getProductid())))
				&& ((this.getBusitypeid() == castOther.getBusitypeid()) || (this
						.getBusitypeid() != null
						&& castOther.getBusitypeid() != null && this
						.getBusitypeid().equals(castOther.getBusitypeid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37
				* result
				+ (getAccountdate() == null ? 0 : this.getAccountdate()
						.hashCode());
		result = 37
				* result
				+ (getFinaccountid() == null ? 0 : this.getFinaccountid()
						.hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result
				+ (getDirection() == null ? 0 : this.getDirection().hashCode());
		result = 37
				* result
				+ (getBalanceusable() == null ? 0 : this.getBalanceusable()
						.hashCode());
		result = 37
				* result
				+ (getBalancesettle() == null ? 0 : this.getBalancesettle()
						.hashCode());
		result = 37
				* result
				+ (getBalancefrozon() == null ? 0 : this.getBalancefrozon()
						.hashCode());
		result = 37
				* result
				+ (getBalanceoverlimit() == null ? 0 : this
						.getBalanceoverlimit().hashCode());
		result = 37
				* result
				+ (getBalancecredit() == null ? 0 : this.getBalancecredit()
						.hashCode());
		result = 37
				* result
				+ (getReverseflag() == null ? 0 : this.getReverseflag()
						.hashCode());
		result = 37 * result
				+ (getReferid() == null ? 0 : this.getReferid().hashCode());
		result = 37
				* result
				+ (getPaymentamount() == null ? 0 : this.getPaymentamount()
						.hashCode());
		result = 37 * result
				+ (getTransdate() == null ? 0 : this.getTransdate().hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37
				* result
				+ (getCreatedtime() == null ? 0 : this.getCreatedtime()
						.hashCode());
		result = 37
				* result
				+ (getUpdatedtime() == null ? 0 : this.getUpdatedtime()
						.hashCode());
		result = 37
				* result
				+ (getRootinstcd() == null ? 0 : this.getRootinstcd()
						.hashCode());
		result = 37 * result
				+ (getFuncode() == null ? 0 : this.getFuncode().hashCode());
		result = 37 * result
				+ (getProductid() == null ? 0 : this.getProductid().hashCode());
		result = 37
				* result
				+ (getBusitypeid() == null ? 0 : this.getBusitypeid()
						.hashCode());
		return result;
	}

}