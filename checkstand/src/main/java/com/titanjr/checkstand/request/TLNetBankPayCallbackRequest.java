/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLCallbackResponse.java
 * @author Jerry
 * @date 2017年11月28日 下午4:07:53  
 */
package com.titanjr.checkstand.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联回调结果
 * @author Jerry
 * @date 2017年11月28日 下午4:07:53  
 */
public class TLNetBankPayCallbackRequest implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -3570861810264816341L;
	
	/**
	 * 数字串，商户在通联申请开户的商户号
	 */
	@NotBlank
	private String merchantId;
	
	/**
	 * 网关接收支付请求接口版本, 固定填v1.0
	 */
	@NotBlank
	private String version;
	
	/**
	 * 固定值：1；1代表简体中文、2代表繁体中文、3代表英文
	 */
	private String language;
	
	/**
	 * 签名类型，与提交订单时的签名类型保持一致
	 */
	@NotBlank
	private String signType;
	
	/**
	 * 支付方式
	 */
	private String payType;
	
	/**
	 * 发卡方代码
	 */
	private String issuerId;
	
	/**
	 * 通联订单号
	 */
	@NotBlank
	private String paymentOrderId;
	
	/**
	 * 商户订单号<br>
	 * 只允许使用字母、数字、- 、_,并以字母或数字开头；每商户提交的订单号，必须在当天的该商户所有交易中唯一
	 */
	@NotBlank
	private String orderNo;
	
	/**
	 * 商户订单提交时间<br>
	 * 日期格式：yyyyMMDDhhmmss，例如：20121116020101
	 */
	@NotBlank
	private String orderDatetime;
	
	/**
	 * 商户订单金额<br>
	 * 整型数字，金额与币种有关 如果是人民币，则单位是分，即10元提交时金额应为1000 如果是美元，单位是美分，即10美元提交时金额为1000
	 */
	@NotNull
	private Long orderAmount;
	
	/**
	 * 支付完成时间
	 */
	@NotBlank
	private String payDatetime;
	
	/**
	 * 订单实际支付金额
	 */
	@NotNull
	private Long payAmount;
	
	/**
	 * 扩展字段1
	 */
	private String ext1;
	/**
	 * 扩展字段2
	 */
	private String ext2;
	
	/**
	 * 处理结果   1：支付成功, 仅在支付成功时通知商户。
	 */
	@NotNull
	private String payResult;
	
	/**
	 * 错误代码, 固定为空
	 */
	private String errorCode;
	
	/**
	 * 系统返回支付结果的时间，日期格式：yyyyMMDDhhmmss
	 */
	@NotBlank
	private String returnDatetime;
	
	/**
	 * 以上所有非空参数按上述顺序与密钥组合，经加密后生成该值。
	 */
	@NotBlank
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getPaymentOrderId() {
		return paymentOrderId;
	}

	public void setPaymentOrderId(String paymentOrderId) {
		this.paymentOrderId = paymentOrderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPayDatetime() {
		return payDatetime;
	}

	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime;
	}

	public Long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Long payAmount) {
		this.payAmount = payAmount;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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
