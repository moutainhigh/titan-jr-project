package com.fangcang.titanjr.web.pojo;

/**
 * 贷款查询条件
 * 
 * @author Administrator
 *
 */
public class LoanQueryConditions {
	// 还款到期日时间段
	private String beginActualRepaymentDate;
	private String endActualRepaymentDate;

	// 贷款申请时间段
	private String beginCreateTime;
	private String endCreateTime;

	// 最后一次还款的时间
	private String beginLastRepaymentDate;
	private String endLastRepaymentDate;

	// 放款时间
	private String beginRelMoneyTime;
	private String endRelMoneyTime;

	// 产品类型
	private String productType;
	// 贷款状态
	private String loanStatus;
	//分页
	private String currPage;
	//跳转位置
	private String pageKey;

	public String getBeginActualRepaymentDate() {
		return beginActualRepaymentDate;
	}

	public void setBeginActualRepaymentDate(String beginActualRepaymentDate) {
		this.beginActualRepaymentDate = beginActualRepaymentDate;
	}

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public String getEndActualRepaymentDate() {
		return endActualRepaymentDate;
	}

	public void setEndActualRepaymentDate(String endActualRepaymentDate) {
		this.endActualRepaymentDate = endActualRepaymentDate;
	}

	public String getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getBeginLastRepaymentDate() {
		return beginLastRepaymentDate;
	}

	public void setBeginLastRepaymentDate(String beginLastRepaymentDate) {
		this.beginLastRepaymentDate = beginLastRepaymentDate;
	}

	public String getEndLastRepaymentDate() {
		return endLastRepaymentDate;
	}

	public void setEndLastRepaymentDate(String endLastRepaymentDate) {
		this.endLastRepaymentDate = endLastRepaymentDate;
	}

	public String getBeginRelMoneyTime() {
		return beginRelMoneyTime;
	}

	public void setBeginRelMoneyTime(String beginRelMoneyTime) {
		this.beginRelMoneyTime = beginRelMoneyTime;
	}

	public String getEndRelMoneyTime() {
		return endRelMoneyTime;
	}

	public void setEndRelMoneyTime(String endRelMoneyTime) {
		this.endRelMoneyTime = endRelMoneyTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

}
