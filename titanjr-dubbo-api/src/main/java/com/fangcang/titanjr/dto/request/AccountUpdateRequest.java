package com.fangcang.titanjr.dto.request;

import java.util.Date;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * 
 * @author luoqinglong
 * @2016年7月4日
 */
public class AccountUpdateRequest extends BaseRequestDTO{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5131735750666691250L;
	private String userId;
	private String accountCode;
	
	private String accountName;
	/**
	 * 币种
	 */
	private Integer currency;
	/**
	 * 是否免密支付
	 */
	private Integer allownopwdpay;
	/**
	 * 免密限额
	 */
	private Double nopwdpaylimit;
	private Double creditAmount ;
	private Double settleAmount;
	private Double forzenAmount;
	private Double balanceOverLimit ; 
	private Double usableAmount;
	private Double totalAmount;
	/**
	 * 账户状态1.正常，2.冻结中
	 */
	private Integer status;
	private String modifier;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getAllownopwdpay() {
		return allownopwdpay;
	}
	public void setAllownopwdpay(Integer allownopwdpay) {
		this.allownopwdpay = allownopwdpay;
	}
	public Double getNopwdpaylimit() {
		return nopwdpaylimit;
	}
	public void setNopwdpaylimit(Double nopwdpaylimit) {
		this.nopwdpaylimit = nopwdpaylimit;
	}
	public Double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public Double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	public Double getForzenAmount() {
		return forzenAmount;
	}
	public void setForzenAmount(Double forzenAmount) {
		this.forzenAmount = forzenAmount;
	}
	public Double getBalanceOverLimit() {
		return balanceOverLimit;
	}
	public void setBalanceOverLimit(Double balanceOverLimit) {
		this.balanceOverLimit = balanceOverLimit;
	}
	public Double getUsableAmount() {
		return usableAmount;
	}
	public void setUsableAmount(Double usableAmount) {
		this.usableAmount = usableAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
	
}
