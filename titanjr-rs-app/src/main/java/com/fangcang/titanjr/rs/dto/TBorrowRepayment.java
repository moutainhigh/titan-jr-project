package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;

import com.Rop.api.domain.BorrowRepayment;

/**
 * 查询应还款信息
 * @author luoqinglong
 * @2016年11月7日
 */
public class TBorrowRepayment implements Serializable {
	
	private static final long serialVersionUID = -8958878821231015405L;
	/**
	 * 主键ID
	 */
	private String borrowid;
	/**
	 * 账期
	 */
	private String accountdate;
	/**
	 * 	管理机构代码
	 */
	private String rootinstcd;
	/**
	 * 	产品号
	 */
	private String productid;
	/**
	 * 授信提供方ID
	 */
	private String providerid;
	
	/**
	 * 	用户ID
	 */
	private String userid;
	/**
	 * 授信结果订单ID
	 */
	private String creditresultid;
	/**
	 * 授信协议ID
	 */
	private String creditagreementid;
	/**
	 * 	订单ID
	 */
	private String orderid;
	/**
	 * 用户订单流水号
	 */
	private String userorderid;
	/**
	 * 订单金额(分)
	 */
	private String orderamount;
	/**
	 * 费率协议号
	 */
	private String rateid;
	/**
	 * 费率类型
	 */
	private String ratetype;
	/**
	 * 费率级别
	 */
	private String ratelevel;
	/**
	 * 	计息开始时间
	 */
	private String interestdatesingle;
	/**
	 * 	期数汇总
	 */
	private String periodsummary;
	/**
	 * 	当前期数
	 */
	private String periodcurrent;
	/**
	 * 	用户应还日期
	 */
	private String usershouldrepaymentdate;
	/**
	 * 用户利息应还日期
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
	 * 用户逾期标志,0未逾期,1逾期
	 */
	private String useroverdueflag;
	/**
	 * 用户逾期天数
	 */
	private String useroverduedays;
	/**
	 * 	用户逾期罚金(分)
	 */
	private String useroverduefine;
	/**
	 * 	用户逾期利息(分)
	 */
	private String useroverdueinterest;
	/**
	 * 	用户逾期应还金额(分)
	 */
	private String useroverdueshouldamount;
	/**
	 * 	用户实际还款日期
	 */
	private String userrepaidrepaymentdate;
	/**
	 * 	1承担方用户ID
	 */
	private String oneuserid;
	/**
	 * 	1承担方应还日期
	 */
	private String onepartyshouldrepaymentdate;
	/**
	 * 1承担方应还利息(分)
	 */
	private String onepartyshouldinterest;
	/**
	 *	1承担方应还金额(分) 
	 */
	private String onepartyshouldamount;
	/**
	 * 	1承担方实际还款日期
	 */
	private String onepartyrepaidrepaymentdate;
	/**
	 * 	1承担方扣款状态
	 */
	private String onepartystatusid;
	/**
	 * 	2承担方用户ID
	 */
	private String twouserid;
	/**
	 * 2承担方应还日期
	 */
	private String twopartyshouldrepaymentdate;
	/**
	 * 	2承担方应还利息(分)
	 */
	private String twopartyshouldinterest;
	/**
	 * 	2承担方应还金额(分)
	 */
	private String twopartyshouldamount;
	/**
	 * 2承担方实际还款日期
	 */
	private String twopartyrepaidrepaymentdate;
	/**
	 * 	2承担方扣款状态
	 */
	private String twopartystatusid;
	/**
	 * 	3承担方用户ID
	 */
	private String threeuserid;
	/**
	 * 3承担方应还日期
	 */
	private String threepartyshouldrepaymentdate;
	/**
	 * 3承担方应还利息(分)
	 */
	private String threepartyshouldinterest;
	
	/**
	 * 	3承担方应还金额(分)
	 */
	private String threepartyshouldamount;
	/**
	 * 3承担方实际还款日期
	 */
	private String threepartyrepaidrepaymentdate;
	/**
	 * 	3承担方扣款状态
	 */
	private String threepartystatusid;
	
	/**
	 * 备注
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
	/**
	 * 	用户是否生效,0失效,1生效
	 */
	private String useriseffective;
	/**
	 * 用户扣款状态
	 */
	private String userstatusid;
	
	
	 
	
	public String getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(String updatedtime) {
		this.updatedtime = updatedtime;
	}
	public String getRootinstcd() {
		return rootinstcd;
	}
	public void setRootinstcd(String rootinstcd) {
		this.rootinstcd = rootinstcd;
	}
	public String getUseroverdueshouldamount() {
		return useroverdueshouldamount;
	}
	public void setUseroverdueshouldamount(String useroverdueshouldamount) {
		this.useroverdueshouldamount = useroverdueshouldamount;
	}
	public String getUseroverdueinterest() {
		return useroverdueinterest;
	}
	public void setUseroverdueinterest(String useroverdueinterest) {
		this.useroverdueinterest = useroverdueinterest;
	}
	public String getTwopartyshouldrepaymentdate() {
		return twopartyshouldrepaymentdate;
	}
	public void setTwopartyshouldrepaymentdate(String twopartyshouldrepaymentdate) {
		this.twopartyshouldrepaymentdate = twopartyshouldrepaymentdate;
	}
	public String getTwopartyshouldinterest() {
		return twopartyshouldinterest;
	}
	public void setTwopartyshouldinterest(String twopartyshouldinterest) {
		this.twopartyshouldinterest = twopartyshouldinterest;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBorrowid() {
		return borrowid;
	}
	public void setBorrowid(String borrowid) {
		this.borrowid = borrowid;
	}
	public String getThreeuserid() {
		return threeuserid;
	}
	public void setThreeuserid(String threeuserid) {
		this.threeuserid = threeuserid;
	}
	public String getRateid() {
		return rateid;
	}
	public void setRateid(String rateid) {
		this.rateid = rateid;
	}
	public String getOnepartyshouldinterest() {
		return onepartyshouldinterest;
	}
	public void setOnepartyshouldinterest(String onepartyshouldinterest) {
		this.onepartyshouldinterest = onepartyshouldinterest;
	}
	public String getAccountdate() {
		return accountdate;
	}
	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}
	public String getPeriodsummary() {
		return periodsummary;
	}
	public void setPeriodsummary(String periodsummary) {
		this.periodsummary = periodsummary;
	}
	public String getThreepartyshouldinterest() {
		return threepartyshouldinterest;
	}
	public void setThreepartyshouldinterest(String threepartyshouldinterest) {
		this.threepartyshouldinterest = threepartyshouldinterest;
	}
	public String getTwouserid() {
		return twouserid;
	}
	public void setTwouserid(String twouserid) {
		this.twouserid = twouserid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getOnepartyshouldrepaymentdate() {
		return onepartyshouldrepaymentdate;
	}
	public void setOnepartyshouldrepaymentdate(String onepartyshouldrepaymentdate) {
		this.onepartyshouldrepaymentdate = onepartyshouldrepaymentdate;
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
	public String getTwopartyshouldamount() {
		return twopartyshouldamount;
	}
	public void setTwopartyshouldamount(String twopartyshouldamount) {
		this.twopartyshouldamount = twopartyshouldamount;
	}
	public String getOneuserid() {
		return oneuserid;
	}
	public void setOneuserid(String oneuserid) {
		this.oneuserid = oneuserid;
	}
	public String getUserinterestrepaymentdate() {
		return userinterestrepaymentdate;
	}
	public void setUserinterestrepaymentdate(String userinterestrepaymentdate) {
		this.userinterestrepaymentdate = userinterestrepaymentdate;
	}
	public String getCreditagreementid() {
		return creditagreementid;
	}
	public void setCreditagreementid(String creditagreementid) {
		this.creditagreementid = creditagreementid;
	}
	public String getOnepartystatusid() {
		return onepartystatusid;
	}
	public void setOnepartystatusid(String onepartystatusid) {
		this.onepartystatusid = onepartystatusid;
	}
	public String getOnepartyrepaidrepaymentdate() {
		return onepartyrepaidrepaymentdate;
	}
	public void setOnepartyrepaidrepaymentdate(String onepartyrepaidrepaymentdate) {
		this.onepartyrepaidrepaymentdate = onepartyrepaidrepaymentdate;
	}
	public String getRatetype() {
		return ratetype;
	}
	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	public String getOrderamount() {
		return orderamount;
	}
	public void setOrderamount(String orderamount) {
		this.orderamount = orderamount;
	}
	public String getCreditresultid() {
		return creditresultid;
	}
	public void setCreditresultid(String creditresultid) {
		this.creditresultid = creditresultid;
	}
	public String getUsershouldinterest() {
		return usershouldinterest;
	}
	public void setUsershouldinterest(String usershouldinterest) {
		this.usershouldinterest = usershouldinterest;
	}
	public String getInterestdatesingle() {
		return interestdatesingle;
	}
	public void setInterestdatesingle(String interestdatesingle) {
		this.interestdatesingle = interestdatesingle;
	}
	public String getThreepartyshouldrepaymentdate() {
		return threepartyshouldrepaymentdate;
	}
	public void setThreepartyshouldrepaymentdate(
			String threepartyshouldrepaymentdate) {
		this.threepartyshouldrepaymentdate = threepartyshouldrepaymentdate;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUseroverduedays() {
		return useroverduedays;
	}
	public void setUseroverduedays(String useroverduedays) {
		this.useroverduedays = useroverduedays;
	}
	public String getRatelevel() {
		return ratelevel;
	}
	public void setRatelevel(String ratelevel) {
		this.ratelevel = ratelevel;
	}
	public String getUsershouldamount() {
		return usershouldamount;
	}
	public void setUsershouldamount(String usershouldamount) {
		this.usershouldamount = usershouldamount;
	}
	public String getUsershouldcapital() {
		return usershouldcapital;
	}
	public void setUsershouldcapital(String usershouldcapital) {
		this.usershouldcapital = usershouldcapital;
	}
	public String getUserrepaidrepaymentdate() {
		return userrepaidrepaymentdate;
	}
	public void setUserrepaidrepaymentdate(String userrepaidrepaymentdate) {
		this.userrepaidrepaymentdate = userrepaidrepaymentdate;
	}
	public String getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}
	public String getUseroverdueflag() {
		return useroverdueflag;
	}
	public void setUseroverdueflag(String useroverdueflag) {
		this.useroverdueflag = useroverdueflag;
	}
	public String getOnepartyshouldamount() {
		return onepartyshouldamount;
	}
	public void setOnepartyshouldamount(String onepartyshouldamount) {
		this.onepartyshouldamount = onepartyshouldamount;
	}
	public String getTwopartyrepaidrepaymentdate() {
		return twopartyrepaidrepaymentdate;
	}
	public void setTwopartyrepaidrepaymentdate(String twopartyrepaidrepaymentdate) {
		this.twopartyrepaidrepaymentdate = twopartyrepaidrepaymentdate;
	}
	public String getTwopartystatusid() {
		return twopartystatusid;
	}
	public void setTwopartystatusid(String twopartystatusid) {
		this.twopartystatusid = twopartystatusid;
	}
	public String getUseroverduefine() {
		return useroverduefine;
	}
	public void setUseroverduefine(String useroverduefine) {
		this.useroverduefine = useroverduefine;
	}
	public String getThreepartyshouldamount() {
		return threepartyshouldamount;
	}
	public void setThreepartyshouldamount(String threepartyshouldamount) {
		this.threepartyshouldamount = threepartyshouldamount;
	}
	public String getThreepartyrepaidrepaymentdate() {
		return threepartyrepaidrepaymentdate;
	}
	public void setThreepartyrepaidrepaymentdate(
			String threepartyrepaidrepaymentdate) {
		this.threepartyrepaidrepaymentdate = threepartyrepaidrepaymentdate;
	}
	public String getThreepartystatusid() {
		return threepartystatusid;
	}
	public void setThreepartystatusid(String threepartystatusid) {
		this.threepartystatusid = threepartystatusid;
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
	public String getUseriseffective() {
		return useriseffective;
	}
	public void setUseriseffective(String useriseffective) {
		this.useriseffective = useriseffective;
	}
	public String getUserstatusid() {
		return userstatusid;
	}
	public void setUserstatusid(String userstatusid) {
		this.userstatusid = userstatusid;
	}
    
}
