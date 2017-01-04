package com.fangcang.titanjr.entity.parameter;

import java.util.Date;

public class LoanQueryConditions {
	private String orgCode;

	private String orderNo;

	private Integer orderStatusEnum[];

	// 还款到期日时间段
	private Date beginActualRepaymentDate;
	private Date endActualRepaymentDate;

	// 贷款申请时间段
	private Date beginCreateTime;
	private Date endCreateTime;

	// 最后一次还款的时间
	private Date beginLastRepaymentDate;
	private Date endLastRepaymentDate;

	// 放款时间
	private Date beginRelMoneyTime;
	private Date endRelMoneyTime;

	// 贷款产品类型
	private Integer productType;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer[] getOrderStatusEnum() {
		return orderStatusEnum;
	}

	public void setOrderStatusEnum(Integer[] orderStatusEnum) {
		this.orderStatusEnum = orderStatusEnum;
	}

	public Date getBeginActualRepaymentDate() {
		return beginActualRepaymentDate;
	}

	public void setBeginActualRepaymentDate(Date beginActualRepaymentDate) {
		this.beginActualRepaymentDate = beginActualRepaymentDate;
	}

	public Date getEndActualRepaymentDate() {
		return endActualRepaymentDate;
	}

	public void setEndActualRepaymentDate(Date endActualRepaymentDate) {
		this.endActualRepaymentDate = endActualRepaymentDate;
	}

	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public Date getBeginLastRepaymentDate() {
		return beginLastRepaymentDate;
	}

	public void setBeginLastRepaymentDate(Date beginLastRepaymentDate) {
		this.beginLastRepaymentDate = beginLastRepaymentDate;
	}

	public Date getEndLastRepaymentDate() {
		return endLastRepaymentDate;
	}

	public void setEndLastRepaymentDate(Date endLastRepaymentDate) {
		this.endLastRepaymentDate = endLastRepaymentDate;
	}

	public Date getBeginRelMoneyTime() {
		return beginRelMoneyTime;
	}

	public void setBeginRelMoneyTime(Date beginRelMoneyTime) {
		this.beginRelMoneyTime = beginRelMoneyTime;
	}

	public Date getEndRelMoneyTime() {
		return endRelMoneyTime;
	}

	public void setEndRelMoneyTime(Date endRelMoneyTime) {
		this.endRelMoneyTime = endRelMoneyTime;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	
}
