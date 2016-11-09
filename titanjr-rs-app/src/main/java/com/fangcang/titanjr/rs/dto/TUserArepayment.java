package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;
/**
 * 用户主动还款信息
 * @author luoqinglong
 * @2016年11月8日
 */
public class TUserArepayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4785037920978287784L;
	/**
	 * 	主键ID
	 */
	private String borrowid;
	/**
	 * 管理机构代码
	 */
	private String rootinstcd;
	/**
	 * 
	 */
	private String productid;
	/**
	 * 授信提供方ID,例JRD
	 */
	private String providerid;
	/**
	 * 
	 */
	private String userid;
	/**
	 * 	用户订单流水号
	 */
	private String userorderid;
	/**
	 * 	订单ID
	 */
	private String orderid;
	/**
	 * 主动还款本金(分)
	 */
	private String activecapital;
	/**
	 * 	主动还款利息(分)
	 */
	private String activeinterest;
	/**
	 * 主动还款逾期罚金(分)
	 */
	private String activeoverduefine;
	/**
	 * 主动还款逾期利息(分)
	 */
	private String activeoverdueinterest;
	
	/**
	 * 主动还款日期
	 */
	private String activerepaymentdate;
	/**
	 * 	是否恢复额度,0否,1是
	 */
	private String islimit;
	/**
	 * 状态
	 */
	private String statusid;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 
	 */
	private String createdtime;
	/**
	 * 
	 */
	private String updatedtime;
	
	
	public String getBorrowid() {
		return borrowid;
	}
	public void setBorrowid(String borrowid) {
		this.borrowid = borrowid;
	}
	public String getRootinstcd() {
		return rootinstcd;
	}
	public void setRootinstcd(String rootinstcd) {
		this.rootinstcd = rootinstcd;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getActivecapital() {
		return activecapital;
	}
	public void setActivecapital(String activecapital) {
		this.activecapital = activecapital;
	}
	public String getActiveinterest() {
		return activeinterest;
	}
	public void setActiveinterest(String activeinterest) {
		this.activeinterest = activeinterest;
	}
	public String getActiveoverduefine() {
		return activeoverduefine;
	}
	public void setActiveoverduefine(String activeoverduefine) {
		this.activeoverduefine = activeoverduefine;
	}
	public String getActiveoverdueinterest() {
		return activeoverdueinterest;
	}
	public void setActiveoverdueinterest(String activeoverdueinterest) {
		this.activeoverdueinterest = activeoverdueinterest;
	}
	public String getActiverepaymentdate() {
		return activerepaymentdate;
	}
	public void setActiverepaymentdate(String activerepaymentdate) {
		this.activerepaymentdate = activerepaymentdate;
	}
	public String getIslimit() {
		return islimit;
	}
	public void setIslimit(String islimit) {
		this.islimit = islimit;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
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
	
}
