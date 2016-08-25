package com.fangcang.titanjr.dto.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fangcang.titanjr.common.enums.PayerTypeEnum;

/**
 * @ClassName: TitanOrderRequest
 * @Description: 创建订单请求
 * @author: wengxitao
 * @date: 2016年8月22日 上午10:32:35
 */
public class TitanOrderRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name; // 打开收银台人员姓名 N
	private String escrowedDate;// 保证期时间 N
	private String goodsId;// 商品编号，可以是对方的订单号
	private String goodsDetail;// 商品描述 N
	private String goodsName;// 商品名称 N
	private String userId;// 付款方身份标示 如果是财务，则建议是FCUSERID
	private String ruserId;// 收款方身份标示 N ,GDP可以指定接受方的 商家联盟可以指定其FCUSERID,ke
	private String currencyType;//币种 1 人民币
	private String amount;// 订单金额
	private String payerType;// 付款人类型 财务 GDP 等
	private String notify;// 通知地址
	private String checkOrderUrl;

	private Map<String, String> businessInfo = new HashMap<String, String>(); // 存储业务信息

	public String getCheckOrderUrl() {
		return checkOrderUrl;
	}

	public void setCheckOrderUrl(String checkOrderUrl) {
		this.checkOrderUrl = checkOrderUrl;
	}
	
	public Map<String, String> getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(Map<String, String> businessInfo) {
		this.businessInfo = businessInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEscrowedDate() {
		return escrowedDate;
	}

	public void setEscrowedDate(String escrowedDate) {
		this.escrowedDate = escrowedDate;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public String getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getRuserId() {
		return ruserId;
	}

	public void setRuserId(String ruserId) {
		this.ruserId = ruserId;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
      this.payerType =payerType;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}
	
}
