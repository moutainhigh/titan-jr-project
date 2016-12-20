package com.fangcang.titanjr.dto.bean;

import java.util.Map;

import com.fangcang.titanjr.common.enums.LoanProductEnum;

/**
 * 組織機構貸款統計信息
 * 
 * @author Administrator
 *
 */
public class OrgLoanStatInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 貸款總額
	private long loanAmount;
	// 將總額劃分到各個產品
	private Map<LoanProductEnum, Long> productAmount;
	
	// 將總額劃分到各個產品
	private Map<LoanProductEnum, Long> productActualAmount;
	
	// 七天還款筆數
	private int sevenDaysNum;
	// 七天待還款金額
	private long sevenDaysAmount;
	// 逾期筆數
	private int expiryNum;
	// 逾期金額
	private int expiryAmount;
	
	public Map<LoanProductEnum, Long> getProductActualAmount() {
		return productActualAmount;
	}

	public void setProductActualAmount(
			Map<LoanProductEnum, Long> productActualAmount) {
		this.productActualAmount = productActualAmount;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Map<LoanProductEnum, Long> getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Map<LoanProductEnum, Long> productAmount) {
		this.productAmount = productAmount;
	}

	public int getSevenDaysNum() {
		return sevenDaysNum;
	}

	public void setSevenDaysNum(int sevenDaysNum) {
		this.sevenDaysNum = sevenDaysNum;
	}

	public long getSevenDaysAmount() {
		return sevenDaysAmount;
	}

	public void setSevenDaysAmount(long sevenDaysAmount) {
		this.sevenDaysAmount = sevenDaysAmount;
	}

	public int getExpiryNum() {
		return expiryNum;
	}

	public void setExpiryNum(int expiryNum) {
		this.expiryNum = expiryNum;
	}

	public int getExpiryAmount() {
		return expiryAmount;
	}

	public void setExpiryAmount(int expiryAmount) {
		this.expiryAmount = expiryAmount;
	}
}
