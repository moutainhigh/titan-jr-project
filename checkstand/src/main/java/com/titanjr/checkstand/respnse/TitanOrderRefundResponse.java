/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanOrderRefundResponse.java
 * @author Jerry
 * @date 2017年12月4日 上午11:39:48  
 */
package com.titanjr.checkstand.respnse;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 退款结果，按照融数格式返回
 * @author Jerry
 * @date 2017年12月4日 上午11:39:48  
 */
public class TitanOrderRefundResponse extends RSResponse {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 2255159199767542474L;
	
	/**
	 * 退款订单号
	 */
	@NotBlank
	private String refundOrderno;

	/**
	 * 订单金额
	 */
	@NotNull
	private String orderAmount;

	/**
	 * 退款金额
	 */
	@NotNull
	private String refundAmount;

	/**
	 * 退款状态  0处理中，1审核失败, 2退款成功 ,3退款失败，4，退款冲销
	 */
	@NotBlank
	private String refundStatus;

	/**
	 * 下单时间
	 */
	@NotBlank
	private String orderTime;

	/**
	 * 退款时间
	 */
	@NotBlank
	private String refundTime;

	@NotBlank
	private String version;
	
	private String signType;
	
	private String signMsg;

	public String getRefundOrderno() {
		return refundOrderno;
	}

	public void setRefundOrderno(String refundOrderno) {
		this.refundOrderno = refundOrderno;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
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

}
