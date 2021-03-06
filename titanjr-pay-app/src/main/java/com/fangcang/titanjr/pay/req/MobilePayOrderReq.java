package com.fangcang.titanjr.pay.req;


import org.hibernate.validator.constraints.NotBlank;


/**
 * 移动支付订单请求
 * 
 * @ClassName: MobilePayOrderReq
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年4月26日 下午5:40:37
 */
public class MobilePayOrderReq {

	// 收付款方编码类型：第一位为收款方类型，第二位为付款方类型，格式：SS,ST,TT,TS；S-SAAS商家编码M，T-金融机构编码TJM
	private String payType;
	// 资金解冻日期,格式：2017-08-09
	private String escrowedDate;
	// 收款机构
	@NotBlank
	private String payeeOrg;

	// 收款机构名称
	private String payeeOrgName;

	// 付款机构
	private String payerOrg;

	// 付款机构名称
	private String payerOrgName;

	// 商品名称或者简要
	private String commodityName;

	// 商品详细描述
	private String commodityDesc;
	
	//业务单号
	@NotBlank
	private String bussOrderNo;
	
	// 币种 1 人民币
	@NotBlank
	private String currencyType;

	// 订单金额
	@NotBlank
	private String amount;

	// 支付通道类型 1 WX
	@NotBlank
	private String channelType;

	// 通知地址
	private String notifyUrl;
	
	//业务回调时需要传递给业务的参数
	private String bussInfo;

	// 成功跳转地址
	private String successJumpUrl;

	// 失败跳转地址
	private String failJumpUrl;
	//签名
	@NotBlank
	private String sign;
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getBussOrderNo() {
		return bussOrderNo;
	}

	public void setBussOrderNo(String bussOrderNo) {
		this.bussOrderNo = bussOrderNo;
	}
	public String getBussInfo() {
		return bussInfo;
	}

	public void setBussInfo(String bussInfo) {
		this.bussInfo = bussInfo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSuccessJumpUrl() {
		return successJumpUrl;
	}

	public void setSuccessJumpUrl(String successJumpUrl) {
		this.successJumpUrl = successJumpUrl;
	}

	public String getFailJumpUrl() {
		return failJumpUrl;
	}

	public void setFailJumpUrl(String failJumpUrl) {
		this.failJumpUrl = failJumpUrl;
	}

	public String getPayeeOrg() {
		return payeeOrg;
	}

	public void setPayeeOrg(String payeeOrg) {
		this.payeeOrg = payeeOrg;
	}

	public String getPayeeOrgName() {
		return payeeOrgName;
	}

	public void setPayeeOrgName(String payeeOrgName) {
		this.payeeOrgName = payeeOrgName;
	}

	public String getPayerOrg() {
		return payerOrg;
	}

	public void setPayerOrg(String payerOrg) {
		this.payerOrg = payerOrg;
	}

	public String getPayerOrgName() {
		return payerOrgName;
	}

	public void setPayerOrgName(String payerOrgName) {
		this.payerOrgName = payerOrgName;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityDesc() {
		return commodityDesc;
	}

	public void setCommodityDesc(String commodityDesc) {
		this.commodityDesc = commodityDesc;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	
	
	public String getEscrowedDate() {
		return escrowedDate;
	}

	public void setEscrowedDate(String escrowedDate) {
		this.escrowedDate = escrowedDate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	 
}
