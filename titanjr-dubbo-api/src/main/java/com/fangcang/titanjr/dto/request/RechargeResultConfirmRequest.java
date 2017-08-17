package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

public class RechargeResultConfirmRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// 不可空 支付网关系统提供 商户号 字母、数字、-、_,字母数字开头，交易中唯一，商户可以通过商户号登录到支付网关为商户提供的平台
		private String merchantNo;
		// 业务订单号 32 不可空 字符串 字母、数字、-、_,字母数字开头，交易中唯一
		private String orderNo;
		// 订单金额 18 不可空 以分为单位
		private String orderAmount;
		// 订单提交时间 20 不可空 yyyyMMddHHmmss
		private String orderTime;
		// 订单状态 2 不可空 受理中: 0 未支付:1 支付中:2支付成功:3支付失败:4
		private String payStatus;
		// 支付订单号 24 不可空 字符串字母、数字、-、_,字母数字开头，交易中唯一
		private String payOrderNo;
		// 支付金额 18 不可空 以分为单位。
		private String payAmount;
		// 支付订单时间 20 不可空 yyyyMMddHHmmss
		private String orderPayTime;
		// 支付信息 32 可空 支付成功 支付失败
		private String payMsg;
		// 版本 不可空 固定值：v1.0注意为小写字母
		private String version;
		// 签名类型 2 不可空
		private String signType;
		// 签名字符串 256 不可空 见下方的签名信息描述，参与签名的val为原值，不为编码后的值
		private String signMsg;
		//	支付方式	2	不可空	间连方式可空，直连方式,不可空，企业银行是1，个人银行是2
		private String payType;
		//银行标识	24	可空	银行的标识
		private String bankCode	;
		//	扩展字段	32	可空	
		private String expand;
		//扩展字段2	32	可空	
		private String expand2;
		//二级机构标识
		private String userid;
		
		private Integer freezereqId;
		
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
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
		public String getPayStatus() {
			return payStatus;
		}
		public void setPayStatus(String payStatus) {
			this.payStatus = payStatus;
		}
		public String getPayOrderNo() {
			return payOrderNo;
		}
		public void setPayOrderNo(String payOrderNo) {
			this.payOrderNo = payOrderNo;
		}
		public String getPayAmount() {
			return payAmount;
		}
		public void setPayAmount(String payAmount) {
			this.payAmount = payAmount;
		}
		public String getOrderPayTime() {
			return orderPayTime;
		}
		public void setOrderPayTime(String orderPayTime) {
			this.orderPayTime = orderPayTime;
		}
		public String getPayMsg() {
			return payMsg;
		}
		public void setPayMsg(String payMsg) {
			this.payMsg = payMsg;
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
		public String getBankCode() {
			return bankCode;
		}
		public void setBankCode(String bankCode) {
			this.bankCode = bankCode;
		}
		public String getExpand() {
			return expand;
		}
		public void setExpand(String expand) {
			this.expand = expand;
		}
		public String getExpand2() {
			return expand2;
		}
		public void setExpand2(String expand2) {
			this.expand2 = expand2;
		}
		public Integer getFreezereqId() {
			return freezereqId;
		}
		public void setFreezereqId(Integer freezereqId) {
			this.freezereqId = freezereqId;
		}
}
