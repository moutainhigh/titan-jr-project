/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLNetBankOrderRefundRequest.java
 * @author Jerry
 * @date 2017年12月4日 上午11:11:42  
 */
package com.titanjr.checkstand.request;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联网银支付单退款请求对象
 * @author Jerry
 * @date 2017年12月4日 上午11:11:42  
 */
public class TLNetBankOrderRefundRequest extends TLBaseRequest {
	
	/**
	 * 【不可空】商户号，与提交订单时的商户号保持一致
	 */
	@NotBlank
	private String merchantId;

	/**
	 * 【不可空】商户订单号，与提交订单时的商户订单号保持一致
	 */
	@NotBlank
	private String orderNo;

	/**
	 * 【不可空】退款金额      若是人民币，单位是分；如果是美元，单位是美分
	 */
	@NotNull
	private Integer refundAmount; 

	/**
	 * 【可空】商户退款订单号，如果填写，退款结果原样返回
	 */
	@NotBlank
	private String mchtRefundOrderNo;

	/**
	 * 【不可空】商户订单提交时间，与提交订单时的商户订单提交时间保持一致
	 */
	@NotBlank
	private String orderDatetime;

	/**
	 * 【不可空】固定值：v2.3
	 */
	@NotBlank
	private String version;

	/**
	 * 【不可空】签名类型<br>
	 * 1表示商户用使用MD5算法验签上送订单，通联交易结果通知使用证书签名<br>
	 * 0表示订单上送和交易结果通知都使用MD5进行签名
	 */
	@NotBlank
	private String signType;

	/**
	 * 【不可空】签名字符串
	 */
	@NotBlank
	private String signMsg;
	

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getMchtRefundOrderNo() {
		return mchtRefundOrderNo;
	}

	public void setMchtRefundOrderNo(String mchtRefundOrderNo) {
		this.mchtRefundOrderNo = mchtRefundOrderNo;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
