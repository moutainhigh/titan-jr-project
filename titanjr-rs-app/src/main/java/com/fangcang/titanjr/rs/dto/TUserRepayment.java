package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;

/***
 * 用户还款信息
 * @author luoqinglong
 * @2016年11月9日
 */
public class TUserRepayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -68763848325868279L;
	/**
	 * 主键ID
	 */
	private String borrowid;
	/**
	 * 管理机构代码
	 */
	private String rootinstcd;
	/**
	 * 	产品号
	 */
	private String productid;
	/**
	 * 	授信提供方ID,例JRD
	 */
	private String providerid;
	/**
	 * 用户ID
	 */
	private String userid;
	/**
	 * 	授信结果订单ID
	 */
	private String creditresultid;
	/**
	 * 用户订单流水号
	 */
	private String userorderid;
	/**
	 * 订单ID
	 */
	private String orderid;
	/**
	 * 	授信协议ID
	 */
	private String creditagreementid;
	
	/**
	 * 	期数汇总
	 */
	private String periodsummary;
	/**
	 * 当前期数
	 */
	private String periodcurrent;
	/**
	 *用户应还日期
	 */
	private String usershouldrepaymentdate;
	/**
	 *  用户利息应还日期
	 */
	private String userinterestrepaymentdate;
	/**
	 * 用户应还本金(分)
	 */
	private String usershouldcapital;
	
	/**
	 * 用户应还利息(分)
	 */
	private String usershouldinterest;
	/**
	 * 用户应还金额(分)
	 */
	private String usershouldamount;
	/**
	 * 	用户逾期标志,0未逾期,1逾期
	 */
	private String useroverdueflag;
	/**
	 * 	用户逾期天数
	 */
	private String useroverduedays;
	/**
	 * 用户逾期罚金(分)
	 */
	private String useroverduefine;
	
	/**
	 * 用户逾期利息(分)
	 */
	private String useroverdueinterest;
	/**
	 * 	用户逾期应还金额(分)
	 */
	private String useroverdueshouldamount;
	/**
	 * 用户实际还款日期
	 */
	private String userrepaidrepaymentdate;
	/**
	 * 是否恢复额度,0否,1是
	 */
	private String islimit;
	/**
	 * 	用户扣款状态
	 */
	private String userstatusid;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 	创建时间
	 */
	private String createdtime;
	/**
	 * 更新时间
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
	public String getCreditresultid() {
		return creditresultid;
	}
	public void setCreditresultid(String creditresultid) {
		this.creditresultid = creditresultid;
	}
	public String getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}
	public String getCreditagreementid() {
		return creditagreementid;
	}
	public void setCreditagreementid(String creditagreementid) {
		this.creditagreementid = creditagreementid;
	}
	public String getPeriodsummary() {
		return periodsummary;
	}
	public void setPeriodsummary(String periodsummary) {
		this.periodsummary = periodsummary;
	}
	public String getPeriodcurrent() {
		return periodcurrent;
	}
	public void setPeriodcurrent(String periodcurrent) {
		this.periodcurrent = periodcurrent;
	}
	public String getUsershouldrepaymentdate() {
		return usershouldrepaymentdate;
	}
	public void setUsershouldrepaymentdate(String usershouldrepaymentdate) {
		this.usershouldrepaymentdate = usershouldrepaymentdate;
	}
	public String getUserinterestrepaymentdate() {
		return userinterestrepaymentdate;
	}
	public void setUserinterestrepaymentdate(String userinterestrepaymentdate) {
		this.userinterestrepaymentdate = userinterestrepaymentdate;
	}
	public String getUsershouldcapital() {
		return usershouldcapital;
	}
	public void setUsershouldcapital(String usershouldcapital) {
		this.usershouldcapital = usershouldcapital;
	}
	public String getUsershouldinterest() {
		return usershouldinterest;
	}
	public void setUsershouldinterest(String usershouldinterest) {
		this.usershouldinterest = usershouldinterest;
	}
	public String getUsershouldamount() {
		return usershouldamount;
	}
	public void setUsershouldamount(String usershouldamount) {
		this.usershouldamount = usershouldamount;
	}
	public String getUseroverdueflag() {
		return useroverdueflag;
	}
	public void setUseroverdueflag(String useroverdueflag) {
		this.useroverdueflag = useroverdueflag;
	}
	public String getUseroverduedays() {
		return useroverduedays;
	}
	public void setUseroverduedays(String useroverduedays) {
		this.useroverduedays = useroverduedays;
	}
	public String getUseroverduefine() {
		return useroverduefine;
	}
	public void setUseroverduefine(String useroverduefine) {
		this.useroverduefine = useroverduefine;
	}
	public String getUseroverdueinterest() {
		return useroverdueinterest;
	}
	public void setUseroverdueinterest(String useroverdueinterest) {
		this.useroverdueinterest = useroverdueinterest;
	}
	public String getUseroverdueshouldamount() {
		return useroverdueshouldamount;
	}
	public void setUseroverdueshouldamount(String useroverdueshouldamount) {
		this.useroverdueshouldamount = useroverdueshouldamount;
	}
	public String getUserrepaidrepaymentdate() {
		return userrepaidrepaymentdate;
	}
	public void setUserrepaidrepaymentdate(String userrepaidrepaymentdate) {
		this.userrepaidrepaymentdate = userrepaidrepaymentdate;
	}
	public String getIslimit() {
		return islimit;
	}
	public void setIslimit(String islimit) {
		this.islimit = islimit;
	}
	public String getUserstatusid() {
		return userstatusid;
	}
	public void setUserstatusid(String userstatusid) {
		this.userstatusid = userstatusid;
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
