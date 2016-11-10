package com.fangcang.titanjr.rs.response;

/**
 * 查询贷款订单状态
 * @author luoqinglong
 * @2016年11月8日
 */
public class QueryLoanApplyResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9004919310547581250L;
	/**
	 * 	订单状态
	 */
	private String statustring;
	/**
	 * 	费率模板
	 */
	private String ratetemplrate;
	/**
	 * 	应收账款
	 */
	private String shouldamount;
	/**
	 * 	分期付款培训协议
	 */
	private String urlkeya;
	/**
	 * 应收账款融资申请书
	 */
	private String urlkeyb;
	/**
	 * 应收账款债权转让通知书回执
	 */
	private String urlkeyc;
	/**
	 * 放款金额
	 */
	private String loanmoney;
	/**
	 * 	放款日期
	 */
	private String loandate;
	
	/**
	 * 申请金额
	 */
	private String applyamoney;
	/**
	 * 创建订单时间
	 */
	private String createtime;
	/**
	 * 应收账款债权转让通知书
	 */
	private String urlkeyd;
	
	/**
	 * 	应放款金额
	 */
	private String shouldloanamount;
	/**
	 * 终审时间:yyyy-MM-dd HH:mm:ss
	 */
	private String lastappealtime;
	
	public String getStatustring() {
		return statustring;
	}
	public void setStatustring(String statustring) {
		this.statustring = statustring;
	}
	public String getRatetemplrate() {
		return ratetemplrate;
	}
	public void setRatetemplrate(String ratetemplrate) {
		this.ratetemplrate = ratetemplrate;
	}
	public String getShouldamount() {
		return shouldamount;
	}
	public void setShouldamount(String shouldamount) {
		this.shouldamount = shouldamount;
	}
	public String getUrlkeya() {
		return urlkeya;
	}
	public void setUrlkeya(String urlkeya) {
		this.urlkeya = urlkeya;
	}
	public String getUrlkeyb() {
		return urlkeyb;
	}
	public void setUrlkeyb(String urlkeyb) {
		this.urlkeyb = urlkeyb;
	}
	public String getUrlkeyc() {
		return urlkeyc;
	}
	public void setUrlkeyc(String urlkeyc) {
		this.urlkeyc = urlkeyc;
	}
	public String getLoanmoney() {
		return loanmoney;
	}
	public void setLoanmoney(String loanmoney) {
		this.loanmoney = loanmoney;
	}
	public String getLoandate() {
		return loandate;
	}
	public void setLoandate(String loandate) {
		this.loandate = loandate;
	}
	public String getApplyamoney() {
		return applyamoney;
	}
	public void setApplyamoney(String applyamoney) {
		this.applyamoney = applyamoney;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUrlkeyd() {
		return urlkeyd;
	}
	public void setUrlkeyd(String urlkeyd) {
		this.urlkeyd = urlkeyd;
	}
	public String getShouldloanamount() {
		return shouldloanamount;
	}
	public void setShouldloanamount(String shouldloanamount) {
		this.shouldloanamount = shouldloanamount;
	}
	public String getLastappealtime() {
		return lastappealtime;
	}
	public void setLastappealtime(String lastappealtime) {
		this.lastappealtime = lastappealtime;
	}
	
}
