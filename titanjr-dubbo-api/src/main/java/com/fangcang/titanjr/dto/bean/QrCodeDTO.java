package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

public class QrCodeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//失败返回错误代码
	private String errCode;
	
	//失败返回错误描述
	private String errMsg;
	
	//支付网关系统提供
	private String merchantNo;
	
	//业务订单号
	private String orderNo;
	
	//订单金额
	private String orderAmount;
	
	//订单提交时间
	private String orderTime;
	
    //	支付方式
	private String payType;
	
	//支付信息
	private String payMsg;
	
	//版本
	private String version;
	
	//签名类型
	private String signType;
	
	//见下方的签名信息描述，参与签名的val为原值，不为编码后的值
	private String signMsg;
	
	//微信支付返回json串或者url
	private String respJs;

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

	public String getRespJs() {
		return respJs;
	}

	public void setRespJs(String respJs) {
		this.respJs = respJs;
	}
	
}
