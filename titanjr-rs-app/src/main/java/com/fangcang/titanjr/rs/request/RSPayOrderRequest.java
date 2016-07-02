package com.fangcang.titanjr.rs.request;

import java.util.HashMap;
import java.util.Map;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

public class RSPayOrderRequest {
	
	//商户号
	private String merchantNo;
	//业务订单号
	private String orderNo;
	//产品号
	private String productNo;
	//产品名称
	private String productName;
	//支付订单时间
	private String productDesc;
	//产品数量
	private String productNum;
	//订单金额
	private String orderAmount;
	//支付方式
	private String payType;
	//币种
	private String amtType;
	//银行标识
	private String bankInfo;
	//支付人账号
	private String payerAcount;
	//支付人姓名
	private String payerName;
	//支付人手机号
	private String payerPhone;
	//支付人邮箱
	private String payerMail;
	//页面返回地址
	private String pageUrl;
	//结果通讯地址
	private String notifyUrl;
	//订单提交时间
	private String orderTime;
	//订单过期时间
	private String orderExpireTime;
	//订单标志
	private String orderMark;
	//扩展字段
	private String expand;
	//扩展字段2
	private String expand2;
	//签名类型
	private String signType;
	//业务号
	private String busiCode;
	//版本
	private String version="v1.0";
	//编码
	private String charset;
	//签名字符串
	private String signMsg;
	
	
	
	//测试支付回调接口
	private String payOrderNo;
	
	private String payStatus;
	
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

	//商户密钥
	private String key ="12356780Poi";
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
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getAmtType() {
		return amtType;
	}
	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}
	public String getBankInfo() {
		return bankInfo;
	}
	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}
	public String getPayerAcount() {
		return payerAcount;
	}
	public void setPayerAcount(String payerAcount) {
		this.payerAcount = payerAcount;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerPhone() {
		return payerPhone;
	}
	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}
	public String getPayerMail() {
		return payerMail;
	}
	public void setPayerMail(String payerMail) {
		this.payerMail = payerMail;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	public String getOrderExpireTime() {
		return orderExpireTime;
	}
	public void setOrderExpireTime(String orderExpireTime) {
		this.orderExpireTime = orderExpireTime;
	}
	public String getOrderMark() {
		return orderMark;
	}
	public void setOrderMark(String orderMark) {
		this.orderMark = orderMark;
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
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public static Map<String,String> RSPayOrderRequestToMap(RSPayOrderRequest rsPayOrderRequest){
		Map<String,String>paraMap = new HashMap<String, String>();
		if(rsPayOrderRequest !=null){
			if(rsPayOrderRequest.getMerchantNo() !=null){
				paraMap.put("merchantNo",rsPayOrderRequest.getMerchantNo());
			}
			if(rsPayOrderRequest.getOrderNo() !=null){
				paraMap.put("orderNo",rsPayOrderRequest.getOrderNo());
			}
	        if(rsPayOrderRequest.getProductNo()!=null){
	        	paraMap.put("productNo",rsPayOrderRequest.getProductNo());
	        }
	        if(rsPayOrderRequest.getProductName() !=null){
	        	paraMap.put("productName",rsPayOrderRequest.getProductName());
	        }
	        if(rsPayOrderRequest.getProductDesc() !=null){
	        	paraMap.put("productDesc",rsPayOrderRequest.getProductDesc());
	        }
	        if(rsPayOrderRequest.getProductNum() !=null){
	        	paraMap.put("productNum",rsPayOrderRequest.getProductNum());
	        }
	        if(rsPayOrderRequest.getOrderAmount() !=null){
	        	paraMap.put("orderAmount",rsPayOrderRequest.getOrderAmount());
	        }
	        if(rsPayOrderRequest.getPayType() !=null){
	            paraMap.put("payType",rsPayOrderRequest.getPayType());
	        }
	        if(rsPayOrderRequest.getAmtType() !=null){
	        	paraMap.put("amtType",rsPayOrderRequest.getAmtType());
	        }
	        if(rsPayOrderRequest.getBankInfo() !=null){
	        	paraMap.put("bankInfo",rsPayOrderRequest.getBankInfo());//间连可为空
	        }
	        if(rsPayOrderRequest.getPayerAcount() !=null){
	        	paraMap.put("payerAcount",rsPayOrderRequest.getPayerAcount());
	        }
	        if(rsPayOrderRequest.getPayerName() !=null){
	        	paraMap.put("payerName",rsPayOrderRequest.getPayerName());
	        }
	        if(rsPayOrderRequest.getPayerPhone() !=null){
	        	paraMap.put("payerPhone",rsPayOrderRequest.getPayerPhone());
	        }
	        if(rsPayOrderRequest.getPayerMail() !=null){
	        	paraMap.put("payerMail",rsPayOrderRequest.getPayerMail());
	        }
	        if(rsPayOrderRequest.getPageUrl() !=null){
	        	paraMap.put("pageUrl",rsPayOrderRequest.getPageUrl());
	        }
	        if(rsPayOrderRequest.getNotifyUrl() !=null){
	        	paraMap.put("notifyUrl",rsPayOrderRequest.getNotifyUrl());
	        }
	        if(rsPayOrderRequest.getOrderTime() !=null){
	        	paraMap.put("orderTime",rsPayOrderRequest.getOrderTime());
	        }
	        if(rsPayOrderRequest.getOrderExpireTime() !=null){
	        	paraMap.put("orderExpireTime",rsPayOrderRequest.getOrderExpireTime());
	        }
	        if(rsPayOrderRequest.getOrderMark() !=null){
	        	paraMap.put("orderMark",rsPayOrderRequest.getOrderMark());
	        }
	        if(rsPayOrderRequest.getExpand() !=null){
	        	paraMap.put("expand",rsPayOrderRequest.getExpand());
	        }
	        if(rsPayOrderRequest.getExpand2() !=null){
	        	paraMap.put("expand2",rsPayOrderRequest.getExpand2());
	        }
	        if(rsPayOrderRequest.getSignType() !=null){
	        	paraMap.put("signType",rsPayOrderRequest.getSignType());
	        }
	        if(rsPayOrderRequest.getBusiCode() !=null){
	        	paraMap.put("busiCode",rsPayOrderRequest.getBusiCode());//商户订单
	        }
	        if(rsPayOrderRequest.getVersion() !=null){
	        	paraMap.put("version",rsPayOrderRequest.getVersion());
	        }
	        if(rsPayOrderRequest.getCharset() !=null){
	        	paraMap.put("charset",rsPayOrderRequest.getCharset());
	        }
		}
		return paraMap;
	}
	
	
	public void check() throws RSValidateException{
		  RequestValidationUtil.checkNotEmpty(this.getMerchantNo(), "merchantNo");
	      RequestValidationUtil.checkNotEmpty(this.getOrderNo(), "orderNo");
	      RequestValidationUtil.checkNotEmpty(this.getOrderAmount(), "orderAmount");
	      RequestValidationUtil.checkNotEmpty(this.getAmtType(), "amtType");
	      RequestValidationUtil.checkNotEmpty(this.getPageUrl(), "pageUrl");
	      RequestValidationUtil.checkNotEmpty(this.getNotifyUrl(), "notifyUrl");
	      RequestValidationUtil.checkNotEmpty(this.getOrderTime(), "orderTime");
	      RequestValidationUtil.checkNotEmpty(this.getOrderMark(), "orderMark");
	      RequestValidationUtil.checkNotEmpty(this.getSignType(), "signType");
	      RequestValidationUtil.checkNotEmpty(this.getBusiCode(), "busiCode");
	      RequestValidationUtil.checkNotEmpty(this.getVersion(), "version");
	      RequestValidationUtil.checkNotEmpty(this.getCharset(), "charset");
	}
	
	public void checkPayResult() throws RSValidateException{
		  RequestValidationUtil.checkNotEmpty(this.getMerchantNo(), "merchantNo");
	      RequestValidationUtil.checkNotEmpty(this.getOrderNo(), "orderNo");
	      RequestValidationUtil.checkNotEmpty(this.getOrderTime(), "orderTime");
	      RequestValidationUtil.checkNotEmpty(this.getSignType(), "signType");
	      RequestValidationUtil.checkNotEmpty(this.getBusiCode(), "busiCode");
	      RequestValidationUtil.checkNotEmpty(this.getVersion(), "version");
	}
	
}
