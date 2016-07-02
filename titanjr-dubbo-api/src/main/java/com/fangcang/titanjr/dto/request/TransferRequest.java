package com.fangcang.titanjr.dto.request;


import com.fangcang.titanjr.common.enums.ConditioncodeEnum;
import com.fangcang.titanjr.common.enums.TransfertypeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.BaseRequestDTO;

public class TransferRequest extends BaseRequestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransfertypeEnum transfertype = TransfertypeEnum.BRANCH_TRANSFER;
	// 商户指示，请联系供应商获取
	private ConditioncodeEnum conditioncode = ConditioncodeEnum.ADD_OEDER;
	// M000001 发生方商户码
	private String merchantcode;
	// 请求号
	private String requestno;
	// 请求时间 请按格式填写（yyyy-MM-dd HH:mm:ss)
	private String requesttime;
	// 金额
	private String amount;
	// 手续费
	private String userfee;
	// 接收方机构码
	private String intermerchantcode;
	// 接收方产品号
	private String interproductid =CommonConstant.RS_FANGCANG_PRODUCT_ID;
	// 接收方用户id
	private String userrelateid;
	
	private String productId =CommonConstant.RS_FANGCANG_PRODUCT_ID; 
	
	
	/*
	 * 下面是保存到数据库所用的字段
	 */
	
	private String userid;
	//创建人
	private String creator;
	//状态
	private String status;
	//融数订单号
	private String orderid;
	//发起转账人的id地址
	private String ipAddress;
	
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public TransfertypeEnum getTransfertype() {
		return transfertype;
	}

	public void setTransfertype(TransfertypeEnum transfertype) {
		this.transfertype = transfertype;
	}

	public ConditioncodeEnum getConditioncode() {
		return conditioncode;
	}

	public void setConditioncode(ConditioncodeEnum conditioncode) {
		this.conditioncode = conditioncode;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getRequestno() {
		return requestno;
	}

	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUserfee() {
		return userfee;
	}

	public void setUserfee(String userfee) {
		this.userfee = userfee;
	}

	public String getIntermerchantcode() {
		return intermerchantcode;
	}

	public void setIntermerchantcode(String intermerchantcode) {
		this.intermerchantcode = intermerchantcode;
	}

	public String getInterproductid() {
		return interproductid;
	}

	public void setInterproductid(String interproductid) {
		this.interproductid = interproductid;
	}

	public String getUserrelateid() {
		return userrelateid;
	}

	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
