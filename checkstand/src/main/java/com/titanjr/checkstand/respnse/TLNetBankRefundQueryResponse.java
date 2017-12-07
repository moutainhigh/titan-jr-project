/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLNetBankRefundQueryResponse.java
 * @author Jerry
 * @date 2017年12月5日 下午6:45:08  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * 通联网银支付的退款查询结果对象
 * @author Jerry
 * @date 2017年12月5日 下午6:45:08  
 */
public class TLNetBankRefundQueryResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -342204961109651073L;
	
	/**
	 * 商户号
	 */
	private String merchantId;

	/**
	 * 商户订单号，与提交订单时的商户号保持一致
	 */
	private String orderNo;

	/**
	 * 退款金额，单位是分
	 */
	private Integer refundAmount;

	/**
	 * 退款受理时间，格式：yyyyMMddhhmmss
	 */
	private String refundDatetime;

	/**
	 * 商户退款订单号
	 */
	private String mchtRefundOrderNo;

	/**
	 * 退款结果：<br>
	 * TKSUCC0001 退款未受理；TKSUCC0002 待通联审核 <br>
	 * TKSUCC0003 通联审核通过；TKSUCC0004 退款冲销 <br>
	 * TKSUCC0005 处理中；TKSUCC0006 退款成功 <br>
	 * TKSUCC0007 退款失败；TKSUCC0008 通联审核不通过<br>
	 */
	private String refundResult;

	/**
	 * 结果返回时间，格式：yyyyMMddhhmmss
	 */
	private String returnDatetime;

	/**
	 * 固定值：v2.4
	 */
	private String version;

	/**
	 * 0表示订单上送和结果返回都使用MD5进行签名<br>
	 * 1表示商户用使用MD5算法签名上送订单，通联返回使用证书签名
	 */
	private String signType;
	

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

	public String getRefundResult() {
		return refundResult;
	}

	public void setRefundResult(String refundResult) {
		this.refundResult = refundResult;
	}

	public String getReturnDatetime() {
		return returnDatetime;
	}

	public void setReturnDatetime(String returnDatetime) {
		this.returnDatetime = returnDatetime;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
