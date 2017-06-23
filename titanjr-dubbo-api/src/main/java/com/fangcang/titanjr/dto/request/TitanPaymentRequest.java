package com.fangcang.titanjr.dto.request;


import com.fangcang.titanjr.enums.PayTypeEnum;

public class TitanPaymentRequest extends TradeBaseParamRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//直连支付方式
	private String linePayType;
		
	//收银台
	private String deskId;

	//付款人员
	private String fcUserid;
	
	//付款密码
	private String payPassword;
	
	//卡或者第三方支付需要支付金额,业务逻辑改为从后台计算，【不从页面获取】
    private String payAmount = "0";
    
    //余额,业务逻辑改为从后台计算，【不从页面获取】
    private String transferAmount = "0";
	// 应收手续费
	private String receivablefee = "0";
	// 实收手续费
	private String receivedfee = "0";
	// 标准费率手续费
	private String standfee = "0";
	// 标准费率
	private Float standardrate;
	// 执行费率
	private Float executionrate;
	// 应收费率
	private Float receivablerate;

	// 费率类型
	private int rateType;
   //调整类容
  	private String adjustcontent;
  	
  	//调整时间
  	private String adjusttype;
  	
    //客户识别号
  	private String payerAcount;
  	
    //银行标识
  	private String bankInfo;

	// 付款者类型
	private String payerType;

	// 付款源
	private String paySource;
	
	
	private String orderTypeId;
	
	
	//md5签名
	private String sign ;
	//支付方式 默认个人银行
	private PayTypeEnum payType = PayTypeEnum.Personal_Banking;
	public Float getReceivablerate() {
		return receivablerate;
	}

	public void setReceivablerate(Float receivablerate) {
		this.receivablerate = receivablerate;
	}

	public int getRateType() {
		return rateType;
	}

	public void setRateType(int rateType) {
		this.rateType = rateType;
	}

	public Float getStandardrate() {
		return standardrate;
	}

	public void setStandardrate(Float standardrate) {
		this.standardrate = standardrate;
	}

	public Float getExecutionrate() {
		return executionrate;
	}

	public void setExecutionrate(Float executionrate) {
		this.executionrate = executionrate;
	}

	public String getPaySource() {
		return paySource;
	}

	public void setPaySource(String paySource) {
		this.paySource = paySource;
	}

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}

	public String getReceivablefee() {
		return receivablefee;
	}

	public void setReceivablefee(String receivablefee) {
		this.receivablefee = receivablefee;
	}

	public String getReceivedfee() {
		return receivedfee;
	}

	public void setReceivedfee(String receivedfee) {
		this.receivedfee = receivedfee;
	}

	public String getStandfee() {
		return standfee;
	}

	public void setStandfee(String standfee) {
		this.standfee = standfee;
	}

	public String getLinePayType() {
		return linePayType;
	}

	public void setLinePayType(String linePayType) {
		this.linePayType = linePayType;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public String getFcUserid() {
		return fcUserid;
	}

	public void setFcUserid(String fcUserid) {
		this.fcUserid = fcUserid;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getAdjustcontent() {
		return adjustcontent;
	}

	public void setAdjustcontent(String adjustcontent) {
		this.adjustcontent = adjustcontent;
	}

	public String getAdjusttype() {
		return adjusttype;
	}

	public void setAdjusttype(String adjusttype) {
		this.adjusttype = adjusttype;
	}

	public String getPayerAcount() {
		return payerAcount;
	}

	public void setPayerAcount(String payerAcount) {
		this.payerAcount = payerAcount;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	public PayTypeEnum getPayType() {
		return payType;
	}

	public void setPayType(PayTypeEnum payType) {
		this.payType = payType;
	}

	public String getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}
	
}
