package com.fangcang.titanjr.dto.bean;

import java.util.Date;

public class TransOrderDTO implements java.io.Serializable {

	

	private Integer transid;
	private String constid;
	private String userid;
	private String ordertypeid;
	private String productid;
	private String orderid;
	private String userorderid;
	private String businessordercode;
	private Date orderdate;
	private Date ordertime;
	private String provider;
	private String userrelateid;
	private Long amount;
	private String goodsname;
	private String goodsdetail;
	private Integer goodscnt;
	private Double unitprice;
	private String statusid;
	private String adjusttype;
	private String adjustcontent;
	private String creator;
	private Date createtime;
	private String amttype;

	private Long receivablefee;
	private Long receivedfee;
	private Long standfee;

	private String merchantcode;
	private String payorderno;

	private String payeemerchant;
	private String payermerchant;

	private String notifyUrl;

	private String remark;

	// 交易金额
	private Long tradeamount;
	// 交易类型
	private String tradeType;

	// 对方
	private String transTarget;
	// 银行卡号
	private String bankcode;
	// 银行名称
	private String bankname;

	// 银行标识
	private String bankInfo;

	private String isEscrowedPayment;

	private Date escrowedDate;

	// 业务信息
	private String businessinfo;

	private String payerType;

	private Integer transordertype;

	private TitanOrderPayDTO titanOrderPayDTO;

	private TitanTransferDTO titanTransferDTO;

	private TitanWithDrawDTO titanWithDrawDTO;

	public Long getReceivablefee() {
		return receivablefee;
	}

	public Long getReceivedfee() {
		return receivedfee;
	}
	public Long getStandfee() {
		return standfee;
	}

	public void setStandfee(Long standfee) {
		this.standfee = standfee;
	}

	public void setReceivablefee(Long receivablefee) {
		this.receivablefee = receivablefee;
	}

	public void setReceivedfee(Long receivedfee) {
		this.receivedfee = receivedfee;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getTransTarget() {
		return transTarget;
	}

	public void setTransTarget(String transTarget) {
		this.transTarget = transTarget;
	}

	public String getPayeemerchant() {
		return payeemerchant;
	}

	public void setPayeemerchant(String payeemerchant) {
		this.payeemerchant = payeemerchant;
	}

	public String getPayermerchant() {
		return payermerchant;
	}

	public void setPayermerchant(String payermerchant) {
		this.payermerchant = payermerchant;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getPayorderno() {
		return payorderno;
	}

	public void setPayorderno(String payorderno) {
		this.payorderno = payorderno;
	}

	public String getAmttype() {
		return amttype;
	}

	public void setAmttype(String amttype) {
		this.amttype = amttype;
	}

	public Integer getTransid() {
		return transid;
	}

	public void setTransid(Integer transid) {
		this.transid = transid;
	}

	public String getConstid() {
		return constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrdertypeid() {
		return ordertypeid;
	}

	public void setOrdertypeid(String ordertypeid) {
		this.ordertypeid = ordertypeid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserorderid() {
		return userorderid;
	}

	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}

	public String getBusinessordercode() {
		return businessordercode;
	}

	public void setBusinessordercode(String businessordercode) {
		this.businessordercode = businessordercode;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getUserrelateid() {
		return userrelateid;
	}

	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodsdetail() {
		return goodsdetail;
	}

	public void setGoodsdetail(String goodsdetail) {
		this.goodsdetail = goodsdetail;
	}

	public Integer getGoodscnt() {
		return goodscnt;
	}

	public void setGoodscnt(Integer goodscnt) {
		this.goodscnt = goodscnt;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public String getStatusid() {
		return statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	public String getAdjusttype() {
		return adjusttype;
	}

	public void setAdjusttype(String adjusttype) {
		this.adjusttype = adjusttype;
	}

	public String getAdjustcontent() {
		return adjustcontent;
	}

	public void setAdjustcontent(String adjustcontent) {
		this.adjustcontent = adjustcontent;
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

	public TitanOrderPayDTO getTitanOrderPayDTO() {
		return titanOrderPayDTO;
	}

	public void setTitanOrderPayDTO(TitanOrderPayDTO titanOrderPayDTO) {
		this.titanOrderPayDTO = titanOrderPayDTO;
	}

	public TitanTransferDTO getTitanTransferDTO() {
		return titanTransferDTO;
	}

	public void setTitanTransferDTO(TitanTransferDTO titanTransferDTO) {
		this.titanTransferDTO = titanTransferDTO;
	}

	public TitanWithDrawDTO getTitanWithDrawDTO() {
		return titanWithDrawDTO;
	}

	public void setTitanWithDrawDTO(TitanWithDrawDTO titanWithDrawDTO) {
		this.titanWithDrawDTO = titanWithDrawDTO;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	public Long getTradeamount() {
		return tradeamount;
	}

	public void setTradeamount(Long tradeamount) {
		this.tradeamount = tradeamount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEscrowedDate() {
		return escrowedDate;
	}

	public void setEscrowedDate(Date escrowedDate) {
		this.escrowedDate = escrowedDate;
	}

	public String getIsEscrowedPayment() {
		return isEscrowedPayment;
	}

	public void setIsEscrowedPayment(String isEscrowedPayment) {
		this.isEscrowedPayment = isEscrowedPayment;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getBusinessinfo() {
		return businessinfo;
	}

	public void setBusinessinfo(String businessinfo) {
		this.businessinfo = businessinfo;
	}

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}

	public Integer getTransordertype() {
		return transordertype;
	}

	public void setTransordertype(Integer transordertype) {
		this.transordertype = transordertype;
	}

}
