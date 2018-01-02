package com.titanjr.checkstand.util.tlUtil.bean;

public class TransRet {
	
	private String MERCHANT_ID;
	private String RET_CODE  ;
	private String SETTLE_DAY;
	private String ERR_MSG   ;
	private String F46;      //posp专用
	private String REMARK;   //posp专用
	private String RESERVE;  //posp专用
	
	public String getMERCHANT_ID() {
		return MERCHANT_ID;
	}
	public void setMERCHANT_ID(String mERCHANT_ID) {
		MERCHANT_ID = mERCHANT_ID;
	}
	public String getRET_CODE()
	{
		return RET_CODE;
	}
	public void setRET_CODE(String rETCODE)
	{
		RET_CODE = rETCODE;
	}
	public String getSETTLE_DAY()
	{
		return SETTLE_DAY;
	}
	public void setSETTLE_DAY(String sETTLEDAY)
	{
		SETTLE_DAY = sETTLEDAY;
	}
	public String getERR_MSG()
	{
		return ERR_MSG;
	}
	public void setERR_MSG(String eRRMSG)
	{
		ERR_MSG = eRRMSG;
	}	
	public String getF46()
	{
		return F46;
	}
	public void setF46(String f46)
	{
		F46 = f46;
	}
	public String getREMARK()
	{
		return REMARK;
	}
	public void setREMARK(String rEMARK)
	{
		REMARK = rEMARK;
	}
	public String getRESERVE()
	{
		return RESERVE;
	}
	public void setRESERVE(String rESERVE)
	{
		RESERVE = rESERVE;
	}
	
}
