package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.bean.PayTypeEnum;

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
	
	//卡或者第三方支付需要支付金额
    private String payAmount;
    
   //调整类容
  	private String adjustcontent;
  	
  	//调整时间
  	private String adjusttype;
  	
    //客户识别号
  	private String payerAcount;
  	
    //银行标识
  	private String bankInfo;
  	
	//支付方式 默认个人银行
	private PayTypeEnum payType = PayTypeEnum.Personal_Banking;

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
}
