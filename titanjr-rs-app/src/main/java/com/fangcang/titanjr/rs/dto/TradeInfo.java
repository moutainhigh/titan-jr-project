package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XmlAccessorType(XmlAccessType.FIELD)
@XStreamAlias("tradeinfo")
public class TradeInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String accountdate;
	
	private String datetime;
	
	private String flowid;
	
	private String inner;
	
	private String remark;
	
	private String gooddetail;
	
	private String amount;
	
	private String outer;
	
	public String getAccountdate() {
		return accountdate;
	}
	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getFlowid() {
		return flowid;
	}
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}
	public String getInner() {
		return inner;
	}
	public void setInner(String inner) {
		this.inner = inner;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGooddetail() {
		return gooddetail;
	}
	public void setGooddetail(String gooddetail) {
		this.gooddetail = gooddetail;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOuter() {
		return outer;
	}
	public void setOuter(String outer) {
		this.outer = outer;
	}
	
}
