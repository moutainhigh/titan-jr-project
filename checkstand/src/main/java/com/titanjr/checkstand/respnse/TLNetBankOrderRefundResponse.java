/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLNetBankOrderRefundResponse.java
 * @author Jerry
 * @date 2017年12月4日 上午11:23:10  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2017年12月4日 上午11:23:10  
 */
public class TLNetBankOrderRefundResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -2768592004065076350L;
	
	/**
	 * 商户号
	 */
	private String merchantId;

	/**
	 * 固定值：v2.3
	 */
	private String version;

	/**
	 * 签名类型  固定选择值：0、1；与客户提交请求填写的值保持一致
	 */
	private String signType;

	/**
	 * 商户订单号，与提交订单时的商户订单号保持一致
	 */
	private String orderNo;

	/**
	 * 商户订单金额
	 */
	private Integer orderAmount;

	/**
	 * 商户订单提交时间，与提交订单时的商户订单提交时间保持一致
	 */
	private String orderDatetime;

	/**
	 * 退款金额
	 */
	private Integer refundAmount;

	/**
	 * 退款受理时间，日期格式：yyyyMMDDhhmmss
	 */
	private String refundDatetime;

	/**
	 * 退款结果，申请成功：20    其他为失败
	 */
	private String refundResult;

	/**
	 * 商户退款订单号，若请求有填写，退款结果原样返回
	 */
	private String mchtRefundOrderNo;

	/**
	 * 结果返回时间， 退款申请完成的时间日期格式：yyyyMMDDhhmmss
	 */
	private String returnDatetime;

	/**
	 * 签名字符串
	 */
	private String signMsg;
	

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
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

	public String getRefundResult() {
		return refundResult;
	}

	public void setRefundResult(String refundResult) {
		this.refundResult = refundResult;
	}

	public String getMchtRefundOrderNo() {
		return mchtRefundOrderNo;
	}

	public void setMchtRefundOrderNo(String mchtRefundOrderNo) {
		this.mchtRefundOrderNo = mchtRefundOrderNo;
	}

	public String getReturnDatetime() {
		return returnDatetime;
	}

	public void setReturnDatetime(String returnDatetime) {
		this.returnDatetime = returnDatetime;
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
