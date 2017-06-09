package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class TitanJrRefundRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//支付单号
	private String payOrderNo;
	
	private String userRelateId;
	
	private String interMerchantCode;
	
	private String interProductId;
	
	private String userId;
	
	private String productId;
	
	private String merchantCode;
	
	private String refundAmount;
	
	private String toBankCardOrAccount;
	
	private String busiCode;
	
	private String orderNo;
	
	private String orderTime;
	
	private String version;
	
	private String signType;
	
	private String signMsg;
	
	private String tradeAmount;
	
	private String fee;
	
	private String tfsUerId;
	//退款单创建者
	private String orderCreator;
	
	private Integer transorderid;
	
	private boolean isFreeze;
	
	private String transferAmount;
	
	private String userOrderId;
	
	private String notifyUrl;
	
	private String businessInfo;
	
	//0实时，1延时
	private Integer isRealTime = 1;
	

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getUserRelateId() {
		return userRelateId;
	}

	public void setUserRelateId(String userRelateId) {
		this.userRelateId = userRelateId;
	}

	public String getInterMerchantCode() {
		return interMerchantCode;
	}

	public void setInterMerchantCode(String interMerchantCode) {
		this.interMerchantCode = interMerchantCode;
	}

	public String getInterProductId() {
		return interProductId;
	}

	public void setInterProductId(String interProductId) {
		this.interProductId = interProductId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getToBankCardOrAccount() {
		return toBankCardOrAccount;
	}

	public void setToBankCardOrAccount(String toBankCardOrAccount) {
		this.toBankCardOrAccount = toBankCardOrAccount;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public Integer getTransorderid() {
		return transorderid;
	}

	public void setTransorderid(Integer transorderid) {
		this.transorderid = transorderid;
	}

	public boolean isFreeze() {
		return isFreeze;
	}

	public void setFreeze(boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public String getTfsUerId() {
		return tfsUerId;
	}

	public void setTfsUerId(String tfsUerId) {
		this.tfsUerId = tfsUerId;
	}

	public String getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public Integer getIsRealTime() {
		return isRealTime;
	}

	public void setIsRealTime(Integer isRealTime) {
		this.isRealTime = isRealTime;
	}

	public String getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}

	public String getOrderCreator() {
		return orderCreator;
	}

	public void setOrderCreator(String orderCreator) {
		this.orderCreator = orderCreator;
	}
	
}
