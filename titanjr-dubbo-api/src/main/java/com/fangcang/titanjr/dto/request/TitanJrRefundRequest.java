package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class TitanJrRefundRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//支付单号
	private String payOrderNo;
	
	//�տ�˻�
	private String userRelateId;
	
	//�տ���
	private String interMerchantCode;
	
	//�տ��Ʒ��
	private String interProductId;
	
	//����˻�
	private String userId;
	
	//�����Ʒ��
	private String productId;
	
	//������
	private String merchantCode;
	
	//�˿���(�˿���п�)
	private String refundAmount;
	
	//�˿���п������˻����
	private String toBankCardOrAccount;
	
	//ҵ���
	private String busiCode;
	
	//ԭ������,�����
	private String orderNo;
	
	//�µ�ʱ��
	private String orderTime;
	
	//�汾��
	private String version;
	
	//ǩ������
	private String signType;
	
	//ǩ���ַ�
	private String signMsg;
	
	//���׽���ָ�̼��˿�����˻��Ľ��
	private String tradeAmount;
	
	//�����
	private String fee;
	
	private String tfsUerId;
	
	private Integer transorderid;
	
	private boolean isFreeze;

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

}
