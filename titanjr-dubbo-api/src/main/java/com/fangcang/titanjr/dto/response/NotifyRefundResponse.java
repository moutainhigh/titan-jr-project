package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;

public class NotifyRefundResponse extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 	//�������
		private String errCode;
		
		//��������
		private String errMsg;
		
		//�̻���
		private String merchantNo;
		
		//������
		private String orderNo;
		
		//�˿����
		private String refundOrderno;
		
		//	�������	
		private String orderAmount;
		
		//�˿�״̬
		private String refundStatus;
		
		//�µ�ʱ��
		private String orderTime;
		
		//�˿�ʱ��	
		private String refundTime;
		
		//�汾
		private String version;
		
		//ǩ������
		private String signType;
		
		//ǩ���ַ���
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
