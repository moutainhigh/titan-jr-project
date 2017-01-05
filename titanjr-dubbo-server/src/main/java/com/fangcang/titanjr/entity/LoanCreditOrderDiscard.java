package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 废弃的授信申请单
 * @author luoqinglong
 * @date   2016年12月26日
 */
public class LoanCreditOrderDiscard implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8438574136131597511L;

	private int discardId;
	/***
	 * 旧金融的授信订单申请号
	 */
	private String oldOrderNo;
	
	/***
	 * 新金融的授信订单申请号
	 */
	private String newOrderNo;
	
	private String orgCode;
	/**
	 * 融数订单号
	 */
	private String rsorderNo;
	/***
	 * 废弃的原因，不通过(续议)原因，
	 */
	private String discardMsg;
	
	private String remark;
	
	private String creator;
	
	private Date discardTime;

	public int getDiscardId() {
		return discardId;
	}

	public void setDiscardId(int discardId) {
		this.discardId = discardId;
	}

	public String getOldOrderNo() {
		return oldOrderNo;
	}

	public void setOldOrderNo(String oldOrderNo) {
		this.oldOrderNo = oldOrderNo;
	}

	public String getNewOrderNo() {
		return newOrderNo;
	}

	public void setNewOrderNo(String newOrderNo) {
		this.newOrderNo = newOrderNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRsorderNo() {
		return rsorderNo;
	}

	public void setRsorderNo(String rsorderNo) {
		this.rsorderNo = rsorderNo;
	}

	public String getDiscardMsg() {
		return discardMsg;
	}

	public void setDiscardMsg(String discardMsg) {
		this.discardMsg = discardMsg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDiscardTime() {
		return discardTime;
	}

	public void setDiscardTime(Date discardTime) {
		this.discardTime = discardTime;
	}
	
}
