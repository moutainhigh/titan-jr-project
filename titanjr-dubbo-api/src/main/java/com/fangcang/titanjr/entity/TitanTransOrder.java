package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanTransOrder entity. @author MyEclipse Persistence Tools
 */
public class TitanTransOrder implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8516112597444482682L;
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
    private String interproductid;
    private String userrelateid;
    private Long amount;
    private String goodsname;
    private String goodsdetail;
    private Integer goodscnt;
    private Long unitprice;
    private String statusid;
    private String adjusttype;
    private String adjustcontent;
    private String creator;
    private Date createtime;
    private Float receivablefee;
    private Float receivedfee;
    private Long tradeamount;
    private Integer transordertype;
    private String notifyUrl;
    //业务信息
    private String businessinfo;
    
    //付款者类型
    private String payerType;

    //是否担保支付
    private String isEscrowedPayment;

    //解冻时间
    private Date escrowedDate;
    //收款账户
    private String payeemerchant;
    //付款账户
    private String payermerchant;
    //财务支付工单号
    private String payorderno;
    //房仓机构名称
    private String merchantcode;

    //银行卡号
    private String bankcode;
    //银行名称
    private String bankname;

    //银行标识
    private String bankInfo;

    private String remark;
    
    
    public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getIsEscrowedPayment() {
        return isEscrowedPayment;
    }

    public void setIsEscrowedPayment(String isEscrowedPayment) {
        this.isEscrowedPayment = isEscrowedPayment;
    }

    public Date getEscrowedDate() {
        return escrowedDate;
    }

    public void setEscrowedDate(Date escrowedDate) {
        this.escrowedDate = escrowedDate;
    }

    public Integer getTransid() {
        return this.transid;
    }

    public void setTransid(Integer transid) {
        this.transid = transid;
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

    public String getConstid() {
        return this.constid;
    }

    public void setConstid(String constid) {
        this.constid = constid;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrdertypeid() {
        return this.ordertypeid;
    }

    public void setOrdertypeid(String ordertypeid) {
        this.ordertypeid = ordertypeid;
    }

    public String getProductid() {
        return this.productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserorderid() {
        return this.userorderid;
    }

    public void setUserorderid(String userorderid) {
        this.userorderid = userorderid;
    }

    public String getBusinessordercode() {
        return this.businessordercode;
    }

    public void setBusinessordercode(String businessordercode) {
        this.businessordercode = businessordercode;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUserrelateid() {
        return this.userrelateid;
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

    public void setUnitprice(Long unitprice) {
        this.unitprice = unitprice;
    }

    public String getGoodsname() {
        return this.goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsdetail() {
        return this.goodsdetail;
    }

    public void setGoodsdetail(String goodsdetail) {
        this.goodsdetail = goodsdetail;
    }

    public Integer getGoodscnt() {
        return this.goodscnt;
    }

    public void setGoodscnt(Integer goodscnt) {
        this.goodscnt = goodscnt;
    }

    public Long getUnitprice() {
        return unitprice;
    }

    public String getStatusid() {
        return this.statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getAdjusttype() {
        return this.adjusttype;
    }

    public void setAdjusttype(String adjusttype) {
        this.adjusttype = adjusttype;
    }

    public String getAdjustcontent() {
        return this.adjustcontent;
    }

    public void setAdjustcontent(String adjustcontent) {
        this.adjustcontent = adjustcontent;
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

    public Float getReceivablefee() {
        return receivablefee;
    }

    public void setReceivablefee(Float receivablefee) {
        this.receivablefee = receivablefee;
    }

    public Float getReceivedfee() {
        return receivedfee;
    }

    public void setReceivedfee(Float receivedfee) {
        this.receivedfee = receivedfee;
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

    public String getPayorderno() {
        return payorderno;
    }

    public void setPayorderno(String payorderno) {
        this.payorderno = payorderno;
    }

    public String getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode;
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

	public String getInterproductid() {
		return interproductid;
	}

	public void setInterproductid(String interproductid) {
		this.interproductid = interproductid;
	}

	public Integer getTransordertype() {
		return transordertype;
	}

	public void setTransordertype(Integer transordertype) {
		this.transordertype = transordertype;
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
}
