package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class NotifyRefundResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 	//错误代码
		private String errCode;
		
		//错误描述
		private String errMsg;
		
		//商户号
		private String merchantNo;
		
		//订单号
		private String orderNo;
		
		//退款订单号
		private String refundOrderno;
		
		//	订单金额	
		private String orderAmount;
		
		//退款状态,请参考：RefundStatusEnum
		private String refundStatus;
		
		//下单时间
		private String orderTime;
		
		//退款时间	
		private String refundTime;
		
		//版本
		private String version;
		
		//签名类型
		private String signType;
		
		//签名字符串
		private String signMsg;

		public String getErrCode() {
			return errCode;
		}

		public void setErrCode(String errCode) {
			this.errCode = errCode;
		}

		public String getErrMsg() {
			return errMsg;
		}

		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}

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

		public String getOrderAmount() {
			return orderAmount;
		}

		public void setOrderAmount(String orderAmount) {
			this.orderAmount = orderAmount;
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
