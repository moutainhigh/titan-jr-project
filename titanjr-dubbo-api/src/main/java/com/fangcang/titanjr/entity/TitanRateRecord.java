package com.fangcang.titanjr.entity;

import java.sql.Timestamp;

/**
 * 
 * @ClassName: TitanRateRecord
 * @Description: 费率信息记录
 * @author: wengxitao
 * @date: 2016年8月16日 下午3:57:54
 */
public class TitanRateRecord {
	// 费率编号
	private String rateId;
	// 用户ID(记录出手续费的用户ID)
	private String userId;
	//订单号
	private String orderNo;
	// 账户编码
	private String accountCode;
	// 币种:1-人民币
	private int currency;
	// 创建人
	private String creator;
	// 创建时间
	private Timestamp createTime;
	// 费率类型 手续费类型1.百分比，2.金额每笔
	private int rateType;
	// 应收手续费(房仓用户看到的手续费)
	private Long receivablefee;
	// 实收手续费(房仓实际收取用户的手续费)
	private Long receivedfee;
	// 标准手续费(房仓跟融数约定的手续费)
	private Long standerdfee;
	// 标准费率
	private Float standardRate;
	// 应收费率
	private Float receivableRate;
	// 实收费率
	private Float receivedRate;
	// 金额
	private Long amount;
	// 1 B2B网银 2 B2C网银 3 信用卡
	private int payType;
	// 1.GDP，2.财务，3.联盟，4.移动
	private int usedFor;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

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

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getRateType() {
		return rateType;
	}

	public void setRateType(int rateType) {
		this.rateType = rateType;
	}

	public Long getReceivablefee() {
		return receivablefee;
	}

	public void setReceivablefee(Long receivablefee) {
		this.receivablefee = receivablefee;
	}

	public Long getReceivedfee() {
		return receivedfee;
	}

	public void setReceivedfee(Long receivedfee) {
		this.receivedfee = receivedfee;
	}

	public Long getStanderdfee() {
		return standerdfee;
	}

	public void setStanderdfee(Long standerdfee) {
		this.standerdfee = standerdfee;
	}

	public Float getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(Float standardRate) {
		this.standardRate = standardRate;
	}

	public Float getReceivableRate() {
		return receivableRate;
	}

	public void setReceivableRate(Float receivableRate) {
		this.receivableRate = receivableRate;
	}

	public Float getReceivedRate() {
		return receivedRate;
	}

	public void setReceivedRate(Float receivedRate) {
		this.receivedRate = receivedRate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getUsedFor() {
		return usedFor;
	}

	public void setUsedFor(int usedFor) {
		this.usedFor = usedFor;
	}

}
