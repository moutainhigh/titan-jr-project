package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 账户明细表
 * @author luoqinglong
 * @date 2017年12月7日
 */
public class TitanAccountDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8903468466833062205L;
	private Long detailId;
	private String accountCode;
	private Long transOrderId;
	private Integer tradeType;
	private String orgCode;
	private String productId;
	
	private Integer balanceType;
	
	private Long creditAmount;
	
	private Long frozonAmount;
	
	private Long settleAmount;
	
	private Long totalCreditAmount;
	private Long totalFrozonAmount;
	private Long totalSettleAmount; 
	
	/**
	 * 状态:1-有效，２－无效
	 */
	private Integer status;
	private String remark;
	private Date createTime;
	
	
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public Long getTransOrderId() {
		return transOrderId;
	}
	public void setTransOrderId(Long transOrderId) {
		this.transOrderId = transOrderId;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(Integer balanceType) {
		this.balanceType = balanceType;
	}
	public Long getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(Long creditAmount) {
		this.creditAmount = creditAmount;
	}
	public Long getFrozonAmount() {
		return frozonAmount;
	}
	public void setFrozonAmount(Long frozonAmount) {
		this.frozonAmount = frozonAmount;
	}
	public Long getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Long settleAmount) {
		this.settleAmount = settleAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getTotalCreditAmount() {
		return totalCreditAmount;
	}
	public void setTotalCreditAmount(Long totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}
	public Long getTotalFrozonAmount() {
		return totalFrozonAmount;
	}
	public void setTotalFrozonAmount(Long totalFrozonAmount) {
		this.totalFrozonAmount = totalFrozonAmount;
	}
	public Long getTotalSettleAmount() {
		return totalSettleAmount;
	}
	public void setTotalSettleAmount(Long totalSettleAmount) {
		this.totalSettleAmount = totalSettleAmount;
	}
	
}
