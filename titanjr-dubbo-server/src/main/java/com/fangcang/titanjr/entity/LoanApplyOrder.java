package com.fangcang.titanjr.entity;

import java.util.Date;

/**
 * 贷款申请单
 * 
 * @author Administrator
 *
 */
public class LoanApplyOrder implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String orderNo;// 贷款单号

	private String creditOrderNo;// 授信单号

	private String orgCode;// 泰坦金融企业编号

	private Long amount;// 申请贷款金额

	private Long actualAmount;// 实际贷款金额

	private Float rate;// 贷款利率

	private String rspId;// 融数产品编号

	private String rsorgId;// 融数机构编号

	private Integer productType;// 1 包房贷 2 运营贷

	private String productId;// 产品编号
	
	private Integer creatorId;// 创建人id

	private Date createTime;// 创建日期

	private String rateTmp;// 费率模板

	private Integer status;// 0 无效贷款  1 贷款申请中  2 待放款   3 已放款  4 放款失败  5 贷款失败  6 已逾期 7 已结清 8 已撤销

	private String errorMsg;// 贷款失败原因

	private Date relMoneyTime; // 放款时间

	private String productSpecId;// 产品规格 ID

	private Integer repaymentType;// 1 按日计利，随借随还

	private Date actualRepaymentDate;// 用户还款到期日
	
	private String orderid;//融数返回单号

	private Date lastRepaymentDate;// 最后一次还款时间

	private Long repaymentPrincipal;// 已还本金

	private Long repaymentInterest;// 已还利息

	private Long shouldCapital;// 应还本金

	private Long shouldInterest;// 应还利息


	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Long actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
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

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
	}

	public Date getActualRepaymentDate() {
		return actualRepaymentDate;
	}

	public void setActualRepaymentDate(Date actualRepaymentDate) {
		this.actualRepaymentDate = actualRepaymentDate;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Date getLastRepaymentDate() {
		return lastRepaymentDate;
	}

	public void setLastRepaymentDate(Date lastRepaymentDate) {
		this.lastRepaymentDate = lastRepaymentDate;
	}

	public Long getRepaymentPrincipal() {
		return repaymentPrincipal;
	}

	public void setRepaymentPrincipal(Long repaymentPrincipal) {
		this.repaymentPrincipal = repaymentPrincipal;
	}

	public Long getRepaymentInterest() {
		return repaymentInterest;
	}

	public void setRepaymentInterest(Long repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}

	public Long getShouldCapital() {
		return shouldCapital;
	}

	public void setShouldCapital(Long shouldCapital) {
		this.shouldCapital = shouldCapital;
	}

	public Long getShouldInterest() {
		return shouldInterest;
	}

	public void setShouldInterest(Long shouldInterest) {
		this.shouldInterest = shouldInterest;
	}
	
}
