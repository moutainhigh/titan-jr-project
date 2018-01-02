/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLNetBankRefundQueryRequest.java
 * @author Jerry
 * @date 2017年12月5日 下午6:28:14  
 */
package com.titanjr.checkstand.request;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联网银支付退款结果查询请求对象
 * @author Jerry
 * @date 2017年12月5日 下午6:28:14  
 */
public class TLNetBankRefundQueryRequest extends TLBaseRequest {
	
	/**
	 * 商户号，与提交订单时的商户号保持一致
	 */
	@NotBlank
	private String merchantId;
	
	/**
	 * 商户订单号，与提交订单时的商户号保持一致
	 */
	@NotBlank
	private String orderNo;
	
	/**
	 * 退款金额，单位是分
	 */
	@NotNull
	private Integer refundAmount;
	
	/**
	 * 退款申请受理时间，日期格式：yyyyMMDDhhmmss 不可空？
	 */
	//@NotBlank
	private String refundDatetime;
	
	/**
	 * 商户退款订单号
	 */
	private String mchtRefundOrderNo;
	
	/**
	 * 固定值：v2.4
	 */
	@NotBlank
	private String version;
	
	/**
	 * 0表示订单上送和结果返回都使用MD5进行签名<br>
	 * 1表示商户用使用MD5算法签名上送订单，通联返回使用证书签名
	 */
	@NotBlank
	private String signType;
	
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

	public String getRefundDatetime() {
		return refundDatetime;
	}

	public void setRefundDatetime(String refundDatetime) {
		this.refundDatetime = refundDatetime;
	}

	public String getMchtRefundOrderNo() {
		return mchtRefundOrderNo;
	}

	public void setMchtRefundOrderNo(String mchtRefundOrderNo) {
		this.mchtRefundOrderNo = mchtRefundOrderNo;
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
