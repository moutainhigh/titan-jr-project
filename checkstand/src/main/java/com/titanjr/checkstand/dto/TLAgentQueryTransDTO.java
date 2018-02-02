package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;

public class TLAgentQueryTransDTO {
	
	//交易流水号，若不填时间必填
	private String QUERY_SN;
	//商户号
	@NotBlank
	private String MERCHANT_ID;
	//交易状态     0成功,1失败, 2全部,3退票,4代付失败退款,5代付退票退款,6委托扣款,7提现
	@NotBlank
	private int STATUS;
	//查询类型    0.按完成日期1.按提交日期，默认为1
	@NotBlank
	private int TYPE;
	//开始时间  若不填QUERY_SN则必填
	private String START_DAY;
	//结束时间  填了开始时间必填
	private String END_DAY;
	//结算账号
	private String SETTACCT;
	//是否包含手续费   0.不需手续费，1.包含手续费    空则默认为0
	private String CONTFEE ;
	
	
	public String getCONTFEE() {
		return CONTFEE;
	}
	public void setCONTFEE(String contfee) {
		CONTFEE = contfee;
	}
	public String getSETTACCT()
	{
		return SETTACCT;
	}
	public void setSETTACCT(String sETTACCT)
	{
		SETTACCT = sETTACCT;
	}
	public String getQUERY_SN()
	{
		return QUERY_SN;
	}
	public void setQUERY_SN(String qUERYSN)
	{
		QUERY_SN = qUERYSN;
	}
	public int getTYPE()
	{
		return TYPE;
	}
	public void setTYPE(int tYPE)
	{
		TYPE = tYPE;
	}
	public int getSTATUS()
	{
		return STATUS;
	}
	public void setSTATUS(int sTATUS)
	{
		STATUS = sTATUS;
	}
	public String getMERCHANT_ID()
	{
		return MERCHANT_ID;
	}
	public void setMERCHANT_ID(String mERCHANTID)
	{
		MERCHANT_ID = mERCHANTID;
	}
	public String getSTART_DAY()
	{
		return START_DAY;
	}
	public void setSTART_DAY(String sTARTDAY)
	{
		START_DAY = sTARTDAY;
	}
	public String getEND_DAY()
	{
		return END_DAY;
	}
	public void setEND_DAY(String eNDDAY)
	{
		END_DAY = eNDDAY;
	}
}
