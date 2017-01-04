package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.dto.BaseRequestDTO;

public class GetLoanOrderInfoListRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orgCode;

	private String orderNo;

	private LoanOrderStatusEnum orderStatusEnum[];
	
	//还款到期日时间段
	private String beginActualRepaymentDate;
	private String endActualRepaymentDate;
	
	//贷款申请时间段
	private String beginCreateTime;
	private String endCreateTime;
	
	//最后一次还款的时间
	private String beginLastRepaymentDate;
	private String endLastRepaymentDate;
	
	//放款时间
	private String beginRelMoneyTime;
	private String endRelMoneyTime;
	
	//贷款产品类型
	private LoanProductEnum productEnum;
	
//	//排序字字段
//	private String orderBy;
//	
//	public String getOrderBy() {
//		return orderBy;
//	}
//
//	public void setOrderBy(String orderBy) {
//		this.orderBy = orderBy;
//	}

	public String getBeginActualRepaymentDate() {
		return beginActualRepaymentDate;
	}

	public void setBeginActualRepaymentDate(String beginActualRepaymentDate) {
		this.beginActualRepaymentDate = beginActualRepaymentDate;
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

	public LoanProductEnum getProductEnum() {
		return productEnum;
	}

	public void setProductEnum(LoanProductEnum productEnum) {
		this.productEnum = productEnum;
	}

	public LoanOrderStatusEnum[] getOrderStatusEnum() {
		return orderStatusEnum;
	}

	public void setOrderStatusEnum(LoanOrderStatusEnum... orderStatusEnum) {
		this.orderStatusEnum = orderStatusEnum;
	}

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
}
