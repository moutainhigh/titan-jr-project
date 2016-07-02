package com.fangcang.titanjr.rs.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("finanaceentry")
public class AccountFlow {
	private String accountdate;
	private String finaccountid;
	private String amount;
	private String direction;
	private String balanceusable;
	private String balancesettle;
	private String balanceoverLimit;
	private String balancecredit;
	private String reverseflag;
	private String referid;
	private String paymentamount;
	private String transdate;
	private String remark;
	private String createdtime;
	private String updatedtime;
	private String funcode;
	private String busitypeid;
	private String rootinstcd;
	private String userid;
	private String productid;
	
	public String getAccountdate() {
		return accountdate;
	}
	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}
	public String getFinaccountid() {
		return finaccountid;
	}
	public void setFinaccountid(String finaccountid) {
		this.finaccountid = finaccountid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getBalanceusable() {
		return balanceusable;
	}
	public void setBalanceusable(String balanceusable) {
		this.balanceusable = balanceusable;
	}
	public String getBalancesettle() {
		return balancesettle;
	}
	public void setBalancesettle(String balancesettle) {
		this.balancesettle = balancesettle;
	}
	public String getBalanceoverLimit() {
		return balanceoverLimit;
	}
	public void setBalanceoverLimit(String balanceoverLimit) {
		this.balanceoverLimit = balanceoverLimit;
	}
	public String getBalancecredit() {
		return balancecredit;
	}
	public void setBalancecredit(String balancecredit) {
		this.balancecredit = balancecredit;
	}
	public String getReverseflag() {
		return reverseflag;
	}
	public void setReverseflag(String reverseflag) {
		this.reverseflag = reverseflag;
	}
	public String getReferid() {
		return referid;
	}
	public void setReferid(String referid) {
		this.referid = referid;
	}
	public String getPaymentamount() {
		return paymentamount;
	}
	public void setPaymentamount(String paymentamount) {
		this.paymentamount = paymentamount;
	}
	public String getTransdate() {
		return transdate;
	}
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(String updatedtime) {
		this.updatedtime = updatedtime;
	}
	public String getFuncode() {
		return funcode;
	}
	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}
	public String getBusitypeid() {
		return busitypeid;
	}
	public void setBusitypeid(String busitypeid) {
		this.busitypeid = busitypeid;
	}
	public String getRootinstcd() {
		return rootinstcd;
	}
	public void setRootinstcd(String rootinstcd) {
		this.rootinstcd = rootinstcd;
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
	
}
