package com.fangcang.titanjr.entity;

import java.util.Date;

/**
 * 贷款申请单
 * 
 * @author Administrator
 *
 */
public class LoanApplyOrder {

	private String orderNo;// 贷款单号

	private String creditOrderNo;// 授信单号

	private String orgCode;// 泰坦金融企业编号

	private String amount;// 申请贷款金额

	private String actualAmount;// 实际贷款金额

	private float rate;// 贷款利率

	private String rspId;// 融数产品编号

	private String rsorgId;// 融数机构编号

	private int productType;// 1 包房贷 2 运营贷

	private String productId;// 产品编号

	private Date createTime;// 创建日期

	private String rateTmp;// 费率模板

	private int status;// 0 无效贷款 1 贷款申请中 2 贷款成功等待放款 3 放款成功 4 放款失败 5 贷款失败

	private String errorMsg;// 贷款失败原因

	private Date relMoneyTime; // 放款时间

	private String productSpecId;// 产品规格 ID

	private int repaymentType;// 1 按日计利，随借随还

	private Date actualRepaymentDate;// 用户还款到期日


	private Date lastRepaymentDate;// 最后一次还款时间

	private long repaymentPrincipal;// 已还本金

	private long repaymentInterest;// 已还利息

	private long shouldCapital;// 应还本金

	private long shouldInterest;// 应还利息

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreditOrderNo() {
		return creditOrderNo;
	}

	public void setCreditOrderNo(String creditOrderNo) {
		this.creditOrderNo = creditOrderNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getRspId() {
		return rspId;
	}

	public void setRspId(String rspId) {
		this.rspId = rspId;
	}

	public String getRsorgId() {
		return rsorgId;
	}

	public void setRsorgId(String rsorgId) {
		this.rsorgId = rsorgId;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRateTmp() {
		return rateTmp;
	}

	public void setRateTmp(String rateTmp) {
		this.rateTmp = rateTmp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getRelMoneyTime() {
		return relMoneyTime;
	}

	public void setRelMoneyTime(Date relMoneyTime) {
		this.relMoneyTime = relMoneyTime;
	}

	public String getProductSpecId() {
		return productSpecId;
	}

	public void setProductSpecId(String productSpecId) {
		this.productSpecId = productSpecId;
	}

	public int getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(int repaymentType) {
		this.repaymentType = repaymentType;
	}

	public Date getActualRepaymentDate() {
		return actualRepaymentDate;
	}

	public void setActualRepaymentDate(Date actualRepaymentDate) {
		this.actualRepaymentDate = actualRepaymentDate;
	}

	public Date getLastRepaymentDate() {
		return lastRepaymentDate;
	}

	public void setLastRepaymentDate(Date lastRepaymentDate) {
		this.lastRepaymentDate = lastRepaymentDate;
	}

	public long getRepaymentPrincipal() {
		return repaymentPrincipal;
	}

	public void setRepaymentPrincipal(long repaymentPrincipal) {
		this.repaymentPrincipal = repaymentPrincipal;
	}

	public long getRepaymentInterest() {
		return repaymentInterest;
	}

	public void setRepaymentInterest(long repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}

	public long getShouldCapital() {
		return shouldCapital;
	}

	public void setShouldCapital(long shouldCapital) {
		this.shouldCapital = shouldCapital;
	}

	public long getShouldInterest() {
		return shouldInterest;
	}

	public void setShouldInterest(long shouldInterest) {
		this.shouldInterest = shouldInterest;
	}
}
