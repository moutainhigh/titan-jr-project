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
	//如果全部用余额支付，也这只此字段，用于费率计算
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
	
	//付款方机构编码
	private String partnerOrgCode;
	
	//新版收银台增加字段
	private String payerAccountType; //支付人银行卡类型   10：借记卡  11：信用卡
	private String payerName; //支付人名字
	private String payerPhone; //支付人银行预留电话
	private String idCode; //支付人身份证
	private String terminalIp; //终端IP
	private String terminalType; //终端类型
	private String terminalInfo; //终端信息
	private String safetyCode;//信用卡背后的3位数字
	private String validthru;//月年格式 例如2020年09月应写为0920
	private String rsVersion;//融数对接版本
	private String jrVersion;//收银台版本
	private String isSaveHistorypay;//是否保存常用卡  0否  1是
	
	//快捷支付绑卡ID
	private String bindCardId;
	
	
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
	
	public String getPayerAccountType() {
		return payerAccountType;
	}

	public void setPayerAccountType(String payerAccountType) {
		this.payerAccountType = payerAccountType;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getTerminalIp() {
		return terminalIp;
	}

	public void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getTerminalInfo() {
		return terminalInfo;
	}

	public void setTerminalInfo(String terminalInfo) {
		this.terminalInfo = terminalInfo;
	}

	public String getJrVersion() {
		return jrVersion;
	}

	public void setJrVersion(String jrVersion) {
		this.jrVersion = jrVersion;
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

	public String getSafetyCode() {
		return safetyCode;
	}

	public void setSafetyCode(String safetyCode) {
		this.safetyCode = safetyCode;
	}

	public String getValidthru() {
		return validthru;
	}

	public void setValidthru(String validthru) {
		this.validthru = validthru;
	}

	public String getPartnerOrgCode() {
		return partnerOrgCode;
	}

	public void setPartnerOrgCode(String partnerOrgCode) {
		this.partnerOrgCode = partnerOrgCode;
	}

	public String getRsVersion() {
		return rsVersion;
	}

	public void setRsVersion(String rsVersion) {
		this.rsVersion = rsVersion;
	}

	public String getIsSaveHistorypay() {
		return isSaveHistorypay;
	}

	public void setIsSaveHistorypay(String isSaveHistorypay) {
		this.isSaveHistorypay = isSaveHistorypay;
	}

	public String getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}
	
}
