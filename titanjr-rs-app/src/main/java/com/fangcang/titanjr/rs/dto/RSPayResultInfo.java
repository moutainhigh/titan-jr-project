package com.fangcang.titanjr.rs.dto;

import com.fangcang.titanjr.rs.manager.impl.RSInvokeInitManagerImpl;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;

public class RSPayResultInfo {
	// 错误代码 10 失败返回错误代码 参看返回码表
	private String errCode;
	// 失败返回错误描述
	private String errMsg;
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
	private String orderpayNo;
	// 支付金额 18 不可空 以分为单位。
	private String payAmount;
	// 支付订单时间 20 不可空 yyyyMMddHHmmss
	private String orderPaytime;
	// 支付信息 32 可空 支付成功 支付失败
	private String payMsg;
	// 版本 不可空 固定值：v1.0注意为小写字母
	private String version;
	// 签名类型 2 不可空
	private String signType;
	// 签名字符串 256 不可空 见下方的签名信息描述，参与签名的val为原值，不为编码后的值
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

	public String getOrderpayNo() {
		return orderpayNo;
	}

	public void setOrderpayNo(String orderpayNo) {
		this.orderpayNo = orderpayNo;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getOrderPaytime() {
		return orderPaytime;
	}

	public void setOrderPaytime(String orderPaytime) {
		this.orderPaytime = orderPaytime;
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
	
	public static String getSign(RSPayResultInfo rsPayResultInfo){
		StringBuffer stringBuffer = new StringBuffer();
		if(rsPayResultInfo !=null){
			stringBuffer.append("merchantNo=");
			stringBuffer.append(rsPayResultInfo.getMerchantNo());
			stringBuffer.append("&orderNo=");
			stringBuffer.append(rsPayResultInfo.getOrderNo());
			stringBuffer.append("&orderAmount=");
			stringBuffer.append(rsPayResultInfo.getOrderAmount());
			stringBuffer.append("&orderTime=");
			stringBuffer.append(rsPayResultInfo.getOrderTime());
			stringBuffer.append("&payStatus=");
			stringBuffer.append(rsPayResultInfo.getPayStatus());
			stringBuffer.append("&orderpayNo=");
			stringBuffer.append(rsPayResultInfo.getOrderpayNo());
			stringBuffer.append("&payAmount=");
			stringBuffer.append(rsPayResultInfo.getPayAmount());
			stringBuffer.append("&orderPaytime=");
			stringBuffer.append(rsPayResultInfo.getOrderPaytime());
			stringBuffer.append("&key=");
			stringBuffer.append(RSInvokeConstant.rsCheckKey);
		}
		System.out.println(stringBuffer.toString());
		return stringBuffer.toString();
	}
}
