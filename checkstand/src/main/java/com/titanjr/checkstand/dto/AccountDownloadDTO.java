/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AccountDownloadDTO.java
 * @author Jerry
 * @date 2018年3月21日 上午10:41:06  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

/**
 * 对账文件信息
 * @author Jerry
 * @date 2018年3月21日 上午10:41:06  
 */
public class AccountDownloadDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -4271893834455228897L;
	
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 商户订单号
	 */
	private String orderNo;
	/**
	 * 第三方订单号
	 */
	private String partnerOrderNo;
	/**
	 * 第三方编码【01通联 02融宝】
	 */
	private String channelCode;
	/**
	 * 交易时间    yyyy-MM-dd HH:mm:ss
	 */
	private String tradeDate;
	/**
	 * 交易状态
	 */
	private String tradeStatus;
	/**
	 * 结算日期    yyyy-MM-dd
	 */
	private String settlDate;
	/**
	 * 交易类型  1充值  2退款  3冲销   4提现
	 */
	private String tradeType;
	/**
	 * 交易类型说明
	 */
	private String tradeTypeDes;
	/**
	 * 交易金额
	 */
	private String tradeAmount;
	/**
	 * 清算金额
	 */
	private String settlAmount;
	/**
	 * 手续费
	 */
	private String fee;
	/**
	 * 银行账号【提现有值】
	 */
	private String bankCardNo;
	/**
	 * 卡类型   1储蓄卡 2信用卡
	 */
	private String cardType;
	/**
	 * 付款方
	 */
	private String payer;
	/**
	 * 收款方
	 */
	private String payee;
	
	
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPartnerOrderNo() {
		return partnerOrderNo;
	}
	public void setPartnerOrderNo(String partnerOrderNo) {
		this.partnerOrderNo = partnerOrderNo;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getSettlDate() {
		return settlDate;
	}
	public void setSettlDate(String settlDate) {
		this.settlDate = settlDate;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeTypeDes() {
		return tradeTypeDes;
	}
	public void setTradeTypeDes(String tradeTypeDes) {
		this.tradeTypeDes = tradeTypeDes;
	}
	public String getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getSettlAmount() {
		return settlAmount;
	}
	public void setSettlAmount(String settlAmount) {
		this.settlAmount = settlAmount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}

}
