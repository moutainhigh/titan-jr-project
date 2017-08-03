package com.fangcang.titanjr.dto.response.gateway;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConfirmRechargeResponse extends RSBaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		//商户号
		private String merchantNo;
		
		//订单号
		private String orderNo;
		
		//支付方式	
		private String payType;
		
		//版本
		private String version;
		
		//签名类型
		private String signType;
		
		//签名字符串
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
		
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
		}
}
