/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLAgentTransDTO.java
 * @author Jerry
 * @date 2017年12月25日 下午4:58:16  
 */
package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2017年12月25日 下午4:58:16  
 */
public class TLAgentPayTransDTO {
	
	//业务代码  
	@NotBlank
	private String BUSINESS_CODE;
	//商户号  
	@NotBlank
	private String MERCHANT_ID;
	//提交时间   YYYYMMDDHHMMSS  
	@NotBlank
	private String SUBMIT_TIME;
	private String TERM_CODE;
	//有效期  YYYYMMDD，用于信用卡
	private String VALIDATE;
	private String TRACK2;
	private String TRACK3;
	private String PINCODE;
	private String E_USER_CODE;
	//银行代码，存折必须填写
	private String BANK_CODE;
	//账号类型   00银行卡，01存折，02信用卡。不填默认为银行卡00。
	private String ACCOUNT_TYPE;
	//银行卡或存折号码  
	@NotBlank
	private String ACCOUNT_NO;
	//银行卡或存折上的所有人姓名  
	@NotBlank
	private String ACCOUNT_NAME;
	//账号属性   0私人，1公司。不填时，默认为私人0  
	@NotBlank
	private String ACCOUNT_PROP;
	//金额，单位分  
	@NotBlank
	private String AMOUNT;
	//货币类型  人民币：CNY, 港元：HKD，美元：USD。不填时，默认为人民币
	private String CURRENCY;
	//证件类型   0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5. 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7. 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
	private String ID_TYPE;
	//证件号
	private String ID;
	//手机号
	private String TEL;
	//自定义用户号
	private String CUST_USERID;
	//备注
	private String REMARK;
	//业务代码
	private String RECVCHNL;
	//业务代码
	private String SETTACCT;
	private String TLTMERID; //posp专用
	private String INSTID; //posp专用
	private String BIZINFO; //posp专用
	private String F46; //posp专用
	private String RESERVED; //posp专用
	private String SETTGROUPFLAG; //分组清算标志
	private String SUMMARY; //交易附言

	
	public String getSETTGROUPFLAG() {
		return SETTGROUPFLAG;
	}
	public void setSETTGROUPFLAG(String sETTGROUPFLAG) {
		SETTGROUPFLAG = sETTGROUPFLAG;
	}
	public String getSUMMARY() {
		return SUMMARY;
	}
	public void setSUMMARY(String sUMMARY) {
		SUMMARY = sUMMARY;
	}
	public String getTLTMERID()
	{
		return TLTMERID;
	}
	public void setTLTMERID(String tLTMERID)
	{
		TLTMERID = tLTMERID;
	}
	public String getINSTID()
	{
		return INSTID;
	}
	public void setINSTID(String iNSTID)
	{
		INSTID = iNSTID;
	}
	public String getBIZINFO()
	{
		return BIZINFO;
	}
	public void setBIZINFO(String bIZINFO)
	{
		BIZINFO = bIZINFO;
	}
	public String getF46()
	{
		return F46;
	}
	public void setF46(String f46)
	{
		F46 = f46;
	}
	public String getRESERVED()
	{
		return RESERVED;
	}
	public void setRESERVED(String rESERVED)
	{
		RESERVED = rESERVED;
	}
	public String getSETTACCT() {
		return SETTACCT;
	}
	public void setSETTACCT(String settacct) {
		SETTACCT = settacct;
	}
	public String getBUSINESS_CODE()
	{
		return BUSINESS_CODE;
	}
	public void setBUSINESS_CODE(String bUSINESSCODE)
	{
		BUSINESS_CODE = bUSINESSCODE;
	}
	public String getMERCHANT_ID()
	{
		return MERCHANT_ID;
	}
	public void setMERCHANT_ID(String mERCHANTID)
	{
		MERCHANT_ID = mERCHANTID;
	}
	public String getSUBMIT_TIME()
	{
		return SUBMIT_TIME;
	}
	public void setSUBMIT_TIME(String sUBMITTIME)
	{
		SUBMIT_TIME = sUBMITTIME;
	}
	public String getTERM_CODE()
	{
		return TERM_CODE;
	}
	public void setTERM_CODE(String tERMCODE)
	{
		TERM_CODE = tERMCODE;
	}
	public String getVALIDATE()
	{
		return VALIDATE;
	}
	public void setVALIDATE(String vALIDATE)
	{
		VALIDATE = vALIDATE;
	}
	public String getTRACK2()
	{
		return TRACK2;
	}
	public void setTRACK2(String tRACK2)
	{
		TRACK2 = tRACK2;
	}
	public String getTRACK3()
	{
		return TRACK3;
	}
	public void setTRACK3(String tRACK3)
	{
		TRACK3 = tRACK3;
	}
	public String getPINCODE()
	{
		return PINCODE;
	}
	public void setPINCODE(String pINCODE)
	{
		PINCODE = pINCODE;
	}
	public String getE_USER_CODE()
	{
		return E_USER_CODE;
	}
	public void setE_USER_CODE(String eUSERCODE)
	{
		E_USER_CODE = eUSERCODE;
	}
	public String getBANK_CODE()
	{
		return BANK_CODE;
	}
	public void setBANK_CODE(String bANKCODE)
	{
		BANK_CODE = bANKCODE;
	}
	public String getACCOUNT_TYPE()
	{
		return ACCOUNT_TYPE;
	}
	public void setACCOUNT_TYPE(String aCCOUNTTYPE)
	{
		ACCOUNT_TYPE = aCCOUNTTYPE;
	}
	public String getACCOUNT_NO()
	{
		return ACCOUNT_NO;
	}
	public void setACCOUNT_NO(String aCCOUNTNO)
	{
		ACCOUNT_NO = aCCOUNTNO;
	}
	public String getACCOUNT_NAME()
	{
		return ACCOUNT_NAME;
	}
	public void setACCOUNT_NAME(String aCCOUNTNAME)
	{
		ACCOUNT_NAME = aCCOUNTNAME;
	}
	public String getACCOUNT_PROP()
	{
		return ACCOUNT_PROP;
	}
	public void setACCOUNT_PROP(String aCCOUNTPROP)
	{
		ACCOUNT_PROP = aCCOUNTPROP;
	}
	public String getAMOUNT()
	{
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT)
	{
		AMOUNT = aMOUNT;
	}
	public String getCURRENCY()
	{
		return CURRENCY;
	}
	public void setCURRENCY(String cURRENCY)
	{
		CURRENCY = cURRENCY;
	}
	public String getID_TYPE()
	{
		return ID_TYPE;
	}
	public void setID_TYPE(String iDTYPE)
	{
		ID_TYPE = iDTYPE;
	}
	public String getID()
	{
		return ID;
	}
	public void setID(String iD)
	{
		ID = iD;
	}
	public String getTEL()
	{
		return TEL;
	}
	public void setTEL(String tEL)
	{
		TEL = tEL;
	}
	public String getCUST_USERID()
	{
		return CUST_USERID;
	}
	public void setCUST_USERID(String cUSTUSERID)
	{
		CUST_USERID = cUSTUSERID;
	}
	public String getREMARK()
	{
		return REMARK;
	}
	public void setREMARK(String rEMARK)
	{
		REMARK = rEMARK;
	}
	public String getRECVCHNL()
	{
		return RECVCHNL;
	}
	public void setRECVCHNL(String rECVCHNL)
	{
		RECVCHNL = rECVCHNL;
	}

}
