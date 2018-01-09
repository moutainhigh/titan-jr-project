package com.titanjr.checkstand.dto;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.BusiCodeEnum;

/**
 * 退款查询参数（融数格式）
 * @author Jerry
 * @date 2017年12月5日 下午6:25:15
 */
public class TitanRefundQueryDTO {
	
	/**
	 * 商户号
	 */
	@NotBlank
	private String merchantNo;

	/**
	 * 业务订单号
	 */
	@NotBlank
	private String orderNo;

	/**
	 * 退款订单号
	 */
	@NotBlank
	private String refundOrderno;

	/**
	 * 业务号  {@link BusiCodeEnum}
	 */
	@NotBlank
	private String busiCode;

	/**
	 * 下单时间，格式yyyyMMddHHmmss
	 */
	@NotBlank
	private String orderTime;

	/**
	 * 退款金额，单位分
	 */
	@NotNull
	private Integer refundAmount;

	/**
	 * 签名的类型，默认1为MD5加签
	 */
	@NotBlank
	private String signType;

	/**
	 * 固定值：v1.0；含快捷支付：v1.1
	 */
	@NotBlank
	private String version;
	
	@NotBlank
	private String signMsg;
	

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

	public String getRefundOrderno() {
		return refundOrderno;
	}

	public void setRefundOrderno(String refundOrderno) {
		this.refundOrderno = refundOrderno;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
