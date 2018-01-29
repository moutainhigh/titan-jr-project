package com.fangcang.titanjr.dto.response.gateway;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class QuickPaymentResponse extends RSBaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		//商户号
		private String merchantNo;
		
		//订单号
		private String orderNo;
		
		//	订单金额	
		private String orderAmount;
		
		//下单时间
		private String orderTime;
		
		//支付方式	
		private String payType;
		
		//支付信息
		private String payMsg;
		
		//版本
		private String version;
		
		//签名类型
		private String signType;
		
		//签名字符串
		private String signMsg;
		
		//卡密校验标识   1：需要卡密校验（不需要卡密校验不返回此值）
		private String certificate;
		
		//快捷支付绑卡ID
		private String bindCardId;


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

		public String getOrderAmount() {
			return orderAmount;
		}

		public void setOrderAmount(String orderAmount) {
			this.orderAmount = orderAmount;
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

		public String getPayType() {
			return payType;
		}

		public void setPayType(String payType) {
			this.payType = payType;
		}

		public String getPayMsg() {
			return payMsg;
		}

		public void setPayMsg(String payMsg) {
			this.payMsg = payMsg;
		}
		
		public String getCertificate() {
			return certificate;
		}

		public void setCertificate(String certificate) {
			this.certificate = certificate;
		}

		public String getBindCardId() {
			return bindCardId;
		}

		public void setBindCardId(String bindCardId) {
			this.bindCardId = bindCardId;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
		}
}
