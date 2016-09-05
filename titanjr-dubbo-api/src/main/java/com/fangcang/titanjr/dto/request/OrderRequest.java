package com.fangcang.titanjr.dto.request;

import java.io.Serializable;
import java.util.Date;

public class OrderRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//机构号
	private String merchantCode;
	
	//产品号
	private String productId;
	
	//支付工单号
	private String payOrderNo;
	
	private String userid;
	  //订单时间
    private Date ordertime;
    //备注
    private String remark;
    //调整类型
    private String adjusttype;
    //用户订单编号
    private String userorderid;
    //基础业务为B1，扩展业务待定
    private String ordertypeid;
    //操作类型（修改：2,新增：1,取消4,查询3）
    private String opertype;
    //商品数量
    private Integer number;
    //订单日期
    private Date orderdate;
    //调整内容
    private String adjustcontent;
    //接收方
    private String userrelateid;
    
    private String interProductid;
    
    //订单金额（若不存在商品概念则必填）
    private String amount;
    //商品描述
    private String goodsdetail;
    //商品名称
    private String goodsname;
    //单价
    private String unitprice;
    //订单单号
    private String businessordercode;
    //融数返回的订单号
    private String orderid;
    //创建人
    private String creator;
    
    //交易金额
    private Long tradeamount;
    
    //是否担保支付
    private String isEscrowedPayment;
    
    //解冻时间
    private Date escrowedDate;
    
    //收款账户
    private String payeemerchant;
    
    //入款账户
    private String payermerchant;
    
    //交易类型
    private Integer transordertype;
    
    private String bussinessInfo;
    //应收手续费
    private String receivablefee;
    //实收手续费
    private String receivedfee;
    //标准费率手续费
    private String standfee;
    //回调地址
    private String notifyUrl;
    
    private Integer transid;
    
    private String payerType;
    public String getReceivablefee() {
		return receivablefee;
	}

	public void setReceivablefee(String receivablefee) {
		this.receivablefee = receivablefee;
	}

	public String getReceivedfee() {
		return receivedfee;
	}

	public void setReceivedfee(String receivedfee) {
		this.receivedfee = receivedfee;
	}

	public String getStandfee() {
		return standfee;
	}

	public void setStandfee(String standfee) {
		this.standfee = standfee;
	}
    
    
    public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
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
    
    public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getBusinessordercode() {
		return businessordercode;
	}
	public void setBusinessordercode(String businessordercode) {
		this.businessordercode = businessordercode;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdjusttype() {
		return adjusttype;
	}
	public void setAdjusttype(String adjusttype) {
		this.adjusttype = adjusttype;
	}
	public String getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}
	public String getOrdertypeid() {
		return ordertypeid;
	}
	public void setOrdertypeid(String ordertypeid) {
		this.ordertypeid = ordertypeid;
	}
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getAdjustcontent() {
		return adjustcontent;
	}
	public void setAdjustcontent(String adjustcontent) {
		this.adjustcontent = adjustcontent;
	}
	public String getUserrelateid() {
		return userrelateid;
	}
	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getGoodsdetail() {
		return goodsdetail;
	}
	public void setGoodsdetail(String goodsdetail) {
		this.goodsdetail = goodsdetail;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Long getTradeamount() {
		return tradeamount;
	}

	public void setTradeamount(Long tradeamount) {
		this.tradeamount = tradeamount;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getInterProductid() {
		return interProductid;
	}

	public void setInterProductid(String interProductid) {
		this.interProductid = interProductid;
	}

	public Integer getTransordertype() {
		return transordertype;
	}

	public void setTransordertype(Integer transordertype) {
		this.transordertype = transordertype;
	}

	public Integer getTransid() {
		return transid;
	}

	public void setTransid(Integer transid) {
		this.transid = transid;
	}

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}

	public String getBussinessInfo() {
		return bussinessInfo;
	}

	public void setBussinessInfo(String bussinessInfo) {
		this.bussinessInfo = bussinessInfo;
	}
	
}
