package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

import com.fangcang.titanjr.common.util.CommonConstant;

public class TradeBaseParamRequest implements Serializable{

	//机构编码
	private String userid;
	
	//商户号,M000016
	private String merchantNo;
	
	//业务订单号
	private String orderNo;
	
	//产品号
	private String productNo;
	
	//交易金额
    private String tradeAmount;
  
    //收款人机构orgName
  	private String recieveOrgName;
  	
  	//收款方titanCode
  	private String recieveTitanCode;
  	
    private String userrelateid;
    
    private String interProductid = CommonConstant.RS_FANGCANG_PRODUCT_ID;
    
	// 单号
	private String payOrderNo;
	
	private String creator;
	
	//系统单号，落单时由融数生成
	private String orderid;
  	
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

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getRecieveOrgName() {
		return recieveOrgName;
	}

	public void setRecieveOrgName(String recieveOrgName) {
		this.recieveOrgName = recieveOrgName;
	}

	public String getRecieveTitanCode() {
		return recieveTitanCode;
	}

	public void setRecieveTitanCode(String recieveTitanCode) {
		this.recieveTitanCode = recieveTitanCode;
	}

	public String getUserrelateid() {
		return userrelateid;
	}

	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getInterProductid() {
		return interProductid;
	}

	public void setInterProductid(String interProductid) {
		this.interProductid = interProductid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
