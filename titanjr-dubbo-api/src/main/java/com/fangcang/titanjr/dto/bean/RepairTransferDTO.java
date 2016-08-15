package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

public class RepairTransferDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
 	 private String amount;
 	 
	 private String intermerchantcode;
	 
	 private String userrelateid;
	 
	 private String interproductid;
	 
	 private String userfee;
	 
	 private String productid;
	 
	 private String userid;
	 
	 private String merchantcode;
	 
	 private String transfertype;
	 
	 private String conditioncode;
	 
	 private int isEscrowedPayment;
	 
	 private Date escrowedDate;
	 
	 private String orderid;
	 
	 private Integer transid; 
	 
	 private Integer tradeamount;
	 
	 private String payeemerchant;
	 
	 private String payermerchant;
	 
	 private String receiveUserId;
	 
	 private String constid;
	 
	 private String userorderid;
	 
	 private String notifyUrl;
	 
	 private String creator;
	 
	 private String businessordercode;
	 
	 private String payorderno;
	 
	 //转账的关联ID
	 private String transorderid;
	 
	 //转账是否成功的状态
	 private String status;
	 
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIntermerchantcode() {
		return intermerchantcode;
	}
	public void setIntermerchantcode(String intermerchantcode) {
		this.intermerchantcode = intermerchantcode;
	}
	public String getUserrelateid() {
		return userrelateid;
	}
	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}
	public String getInterproductid() {
		return interproductid;
	}
	public void setInterproductid(String interproductid) {
		this.interproductid = interproductid;
	}
	public String getUserfee() {
		return userfee;
	}
	public void setUserfee(String userfee) {
		this.userfee = userfee;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMerchantcode() {
		return merchantcode;
	}
	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}
	public String getTransfertype() {
		return transfertype;
	}
	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}
	public String getConditioncode() {
		return conditioncode;
	}
	public void setConditioncode(String conditioncode) {
		this.conditioncode = conditioncode;
	}
	public int getIsEscrowedPayment() {
		return isEscrowedPayment;
	}
	public void setIsEscrowedPayment(int isEscrowedPayment) {
		this.isEscrowedPayment = isEscrowedPayment;
	}
	public Date getEscrowedDate() {
		return escrowedDate;
	}
	public void setEscrowedDate(Date escrowedDate) {
		this.escrowedDate = escrowedDate;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Integer getTransid() {
		return transid;
	}
	public void setTransid(Integer transid) {
		this.transid = transid;
	}
	public Integer getTradeamount() {
		return tradeamount;
	}
	public void setTradeamount(Integer tradeamount) {
		this.tradeamount = tradeamount;
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
	public String getReceiveUserId() {
		return receiveUserId;
	}
	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	public String getConstid() {
		return constid;
	}
	public void setConstid(String constid) {
		this.constid = constid;
	}
	public String getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getBusinessordercode() {
		return businessordercode;
	}
	public void setBusinessordercode(String businessordercode) {
		this.businessordercode = businessordercode;
	}
	public String getPayorderno() {
		return payorderno;
	}
	public void setPayorderno(String payorderno) {
		this.payorderno = payorderno;
	}
	public String getTransorderid() {
		return transorderid;
	}
	public void setTransorderid(String transorderid) {
		this.transorderid = transorderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
