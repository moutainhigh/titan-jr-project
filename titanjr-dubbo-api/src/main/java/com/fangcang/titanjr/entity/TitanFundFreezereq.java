package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanFundFreezereqId entity. @author MyEclipse Persistence Tools
 */

public class TitanFundFreezereq implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2721295998306074923L;
	private Integer freezereqid;
	private String userid;
	private Long amount;
	private Double feeamount;
	private String funccode;
	private String intermerchantcode;
	private String merchantcode;
	private Long orderamount;
	private Integer ordercount;
	private Date orderdate;
	private String orderno;
	private String orderpackageno;
	private String paychannelid;
	private String remark;
	private String requestno;
	private Date requesttime;
	private Integer status;
	private String tradeflowno;
	private Integer userfee;
	private Double profit;
	private String busitypeid;
	private String bankcode;
	private String errorcode;
	private String errormsg;
	private String productid;
	private String useripaddress;
	private String referuserid;
	private String conditioncode;
	private String authcode;

	// Constructors

	/** default constructor */
	public TitanFundFreezereq() {
	}

	/** full constructor */
	public TitanFundFreezereq(Integer freezereqid, String userid,
			Long amount, Double feeamount, String funccode,
			String intermerchantcode, String merchantcode, Long orderamount,
			Integer ordercount, Date orderdate, String orderno,
			String orderpackageno, String paychannelid, String remark,
			String requestno, Date requesttime, Integer status,
			String tradeflowno, Integer userfee, Double profit,
			String busitypeid, String bankcode, String errorcode,
			String errormsg, String productid, String useripaddress,
			String referuserid, String conditioncode, String authcode) {
		this.freezereqid = freezereqid;
		this.userid = userid;
		this.amount = amount;
		this.feeamount = feeamount;
		this.funccode = funccode;
		this.intermerchantcode = intermerchantcode;
		this.merchantcode = merchantcode;
		this.orderamount = orderamount;
		this.ordercount = ordercount;
		this.orderdate = orderdate;
		this.orderno = orderno;
		this.orderpackageno = orderpackageno;
		this.paychannelid = paychannelid;
		this.remark = remark;
		this.requestno = requestno;
		this.requesttime = requesttime;
		this.status = status;
		this.tradeflowno = tradeflowno;
		this.userfee = userfee;
		this.profit = profit;
		this.busitypeid = busitypeid;
		this.bankcode = bankcode;
		this.errorcode = errorcode;
		this.errormsg = errormsg;
		this.productid = productid;
		this.useripaddress = useripaddress;
		this.referuserid = referuserid;
		this.conditioncode = conditioncode;
		this.authcode = authcode;
	}

	// Property accessors

	public Integer getFreezereqid() {
		return this.freezereqid;
	}

	public void setFreezereqid(Integer freezereqid) {
		this.freezereqid = freezereqid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Double getFeeamount() {
		return this.feeamount;
	}

	public void setFeeamount(Double feeamount) {
		this.feeamount = feeamount;
	}

	public String getFunccode() {
		return this.funccode;
	}

	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	public String getIntermerchantcode() {
		return this.intermerchantcode;
	}

	public void setIntermerchantcode(String intermerchantcode) {
		this.intermerchantcode = intermerchantcode;
	}

	public String getMerchantcode() {
		return this.merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(Long orderamount) {
		this.orderamount = orderamount;
	}

	public Integer getOrdercount() {
		return this.ordercount;
	}

	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}

	public Date getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getOrderpackageno() {
		return this.orderpackageno;
	}

	public void setOrderpackageno(String orderpackageno) {
		this.orderpackageno = orderpackageno;
	}

	public String getPaychannelid() {
		return this.paychannelid;
	}

	public void setPaychannelid(String paychannelid) {
		this.paychannelid = paychannelid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRequestno() {
		return this.requestno;
	}

	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}

	public Date getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTradeflowno() {
		return this.tradeflowno;
	}

	public void setTradeflowno(String tradeflowno) {
		this.tradeflowno = tradeflowno;
	}

	public Integer getUserfee() {
		return userfee;
	}

	public void setUserfee(Integer userfee) {
		this.userfee = userfee;
	}

	public Double getProfit() {
		return this.profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getBusitypeid() {
		return this.busitypeid;
	}

	public void setBusitypeid(String busitypeid) {
		this.busitypeid = busitypeid;
	}

	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getErrorcode() {
		return this.errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return this.errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getUseripaddress() {
		return this.useripaddress;
	}

	public void setUseripaddress(String useripaddress) {
		this.useripaddress = useripaddress;
	}

	public String getReferuserid() {
		return this.referuserid;
	}

	public void setReferuserid(String referuserid) {
		this.referuserid = referuserid;
	}

	public String getConditioncode() {
		return this.conditioncode;
	}

	public void setConditioncode(String conditioncode) {
		this.conditioncode = conditioncode;
	}

	public String getAuthcode() {
		return this.authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TitanFundFreezereq))
			return false;
		TitanFundFreezereq castOther = (TitanFundFreezereq) other;

		return ((this.getFreezereqid() == castOther.getFreezereqid()) || (this
				.getFreezereqid() != null && castOther.getFreezereqid() != null && this
				.getFreezereqid().equals(castOther.getFreezereqid())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())))
				&& ((this.getFeeamount() == castOther.getFeeamount()) || (this
						.getFeeamount() != null
						&& castOther.getFeeamount() != null && this
						.getFeeamount().equals(castOther.getFeeamount())))
				&& ((this.getFunccode() == castOther.getFunccode()) || (this
						.getFunccode() != null
						&& castOther.getFunccode() != null && this
						.getFunccode().equals(castOther.getFunccode())))
				&& ((this.getIntermerchantcode() == castOther
						.getIntermerchantcode()) || (this
						.getIntermerchantcode() != null
						&& castOther.getIntermerchantcode() != null && this
						.getIntermerchantcode().equals(
								castOther.getIntermerchantcode())))
				&& ((this.getMerchantcode() == castOther.getMerchantcode()) || (this
						.getMerchantcode() != null
						&& castOther.getMerchantcode() != null && this
						.getMerchantcode().equals(castOther.getMerchantcode())))
				&& ((this.getOrderamount() == castOther.getOrderamount()) || (this
						.getOrderamount() != null
						&& castOther.getOrderamount() != null && this
						.getOrderamount().equals(castOther.getOrderamount())))
				&& ((this.getOrdercount() == castOther.getOrdercount()) || (this
						.getOrdercount() != null
						&& castOther.getOrdercount() != null && this
						.getOrdercount().equals(castOther.getOrdercount())))
				&& ((this.getOrderdate() == castOther.getOrderdate()) || (this
						.getOrderdate() != null
						&& castOther.getOrderdate() != null && this
						.getOrderdate().equals(castOther.getOrderdate())))
				&& ((this.getOrderno() == castOther.getOrderno()) || (this
						.getOrderno() != null && castOther.getOrderno() != null && this
						.getOrderno().equals(castOther.getOrderno())))
				&& ((this.getOrderpackageno() == castOther.getOrderpackageno()) || (this
						.getOrderpackageno() != null
						&& castOther.getOrderpackageno() != null && this
						.getOrderpackageno().equals(
								castOther.getOrderpackageno())))
				&& ((this.getPaychannelid() == castOther.getPaychannelid()) || (this
						.getPaychannelid() != null
						&& castOther.getPaychannelid() != null && this
						.getPaychannelid().equals(castOther.getPaychannelid())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null && castOther.getRemark() != null && this
						.getRemark().equals(castOther.getRemark())))
				&& ((this.getRequestno() == castOther.getRequestno()) || (this
						.getRequestno() != null
						&& castOther.getRequestno() != null && this
						.getRequestno().equals(castOther.getRequestno())))
				&& ((this.getRequesttime() == castOther.getRequesttime()) || (this
						.getRequesttime() != null
						&& castOther.getRequesttime() != null && this
						.getRequesttime().equals(castOther.getRequesttime())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getTradeflowno() == castOther.getTradeflowno()) || (this
						.getTradeflowno() != null
						&& castOther.getTradeflowno() != null && this
						.getTradeflowno().equals(castOther.getTradeflowno())))
				&& ((this.getUserfee() == castOther.getUserfee()) || (this
						.getUserfee() != null && castOther.getUserfee() != null && this
						.getUserfee().equals(castOther.getUserfee())))
				&& ((this.getProfit() == castOther.getProfit()) || (this
						.getProfit() != null && castOther.getProfit() != null && this
						.getProfit().equals(castOther.getProfit())))
				&& ((this.getBusitypeid() == castOther.getBusitypeid()) || (this
						.getBusitypeid() != null
						&& castOther.getBusitypeid() != null && this
						.getBusitypeid().equals(castOther.getBusitypeid())))
				&& ((this.getBankcode() == castOther.getBankcode()) || (this
						.getBankcode() != null
						&& castOther.getBankcode() != null && this
						.getBankcode().equals(castOther.getBankcode())))
				&& ((this.getErrorcode() == castOther.getErrorcode()) || (this
						.getErrorcode() != null
						&& castOther.getErrorcode() != null && this
						.getErrorcode().equals(castOther.getErrorcode())))
				&& ((this.getErrormsg() == castOther.getErrormsg()) || (this
						.getErrormsg() != null
						&& castOther.getErrormsg() != null && this
						.getErrormsg().equals(castOther.getErrormsg())))
				&& ((this.getProductid() == castOther.getProductid()) || (this
						.getProductid() != null
						&& castOther.getProductid() != null && this
						.getProductid().equals(castOther.getProductid())))
				&& ((this.getUseripaddress() == castOther.getUseripaddress()) || (this
						.getUseripaddress() != null
						&& castOther.getUseripaddress() != null && this
						.getUseripaddress()
						.equals(castOther.getUseripaddress())))
				&& ((this.getReferuserid() == castOther.getReferuserid()) || (this
						.getReferuserid() != null
						&& castOther.getReferuserid() != null && this
						.getReferuserid().equals(castOther.getReferuserid())))
				&& ((this.getConditioncode() == castOther.getConditioncode()) || (this
						.getConditioncode() != null
						&& castOther.getConditioncode() != null && this
						.getConditioncode()
						.equals(castOther.getConditioncode())))
				&& ((this.getAuthcode() == castOther.getAuthcode()) || (this
						.getAuthcode() != null
						&& castOther.getAuthcode() != null && this
						.getAuthcode().equals(castOther.getAuthcode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFreezereqid() == null ? 0 : this.getFreezereqid()
						.hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result
				+ (getFeeamount() == null ? 0 : this.getFeeamount().hashCode());
		result = 37 * result
				+ (getFunccode() == null ? 0 : this.getFunccode().hashCode());
		result = 37
				* result
				+ (getIntermerchantcode() == null ? 0 : this
						.getIntermerchantcode().hashCode());
		result = 37
				* result
				+ (getMerchantcode() == null ? 0 : this.getMerchantcode()
						.hashCode());
		result = 37
				* result
				+ (getOrderamount() == null ? 0 : this.getOrderamount()
						.hashCode());
		result = 37
				* result
				+ (getOrdercount() == null ? 0 : this.getOrdercount()
						.hashCode());
		result = 37 * result
				+ (getOrderdate() == null ? 0 : this.getOrderdate().hashCode());
		result = 37 * result
				+ (getOrderno() == null ? 0 : this.getOrderno().hashCode());
		result = 37
				* result
				+ (getOrderpackageno() == null ? 0 : this.getOrderpackageno()
						.hashCode());
		result = 37
				* result
				+ (getPaychannelid() == null ? 0 : this.getPaychannelid()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getRequestno() == null ? 0 : this.getRequestno().hashCode());
		result = 37
				* result
				+ (getRequesttime() == null ? 0 : this.getRequesttime()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getTradeflowno() == null ? 0 : this.getTradeflowno()
						.hashCode());
		result = 37 * result
				+ (getUserfee() == null ? 0 : this.getUserfee().hashCode());
		result = 37 * result
				+ (getProfit() == null ? 0 : this.getProfit().hashCode());
		result = 37
				* result
				+ (getBusitypeid() == null ? 0 : this.getBusitypeid()
						.hashCode());
		result = 37 * result
				+ (getBankcode() == null ? 0 : this.getBankcode().hashCode());
		result = 37 * result
				+ (getErrorcode() == null ? 0 : this.getErrorcode().hashCode());
		result = 37 * result
				+ (getErrormsg() == null ? 0 : this.getErrormsg().hashCode());
		result = 37 * result
				+ (getProductid() == null ? 0 : this.getProductid().hashCode());
		result = 37
				* result
				+ (getUseripaddress() == null ? 0 : this.getUseripaddress()
						.hashCode());
		result = 37
				* result
				+ (getReferuserid() == null ? 0 : this.getReferuserid()
						.hashCode());
		result = 37
				* result
				+ (getConditioncode() == null ? 0 : this.getConditioncode()
						.hashCode());
		result = 37 * result
				+ (getAuthcode() == null ? 0 : this.getAuthcode().hashCode());
		return result;
	}

}