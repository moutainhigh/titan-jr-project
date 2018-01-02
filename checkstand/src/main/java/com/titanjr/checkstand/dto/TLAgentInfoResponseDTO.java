/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLAgentInfoResponseDTO.java
 * @author Jerry
 * @date 2017年12月26日 上午10:25:53  
 */
package com.titanjr.checkstand.dto;

/**
 * @author Jerry
 * @date 2017年12月26日 上午10:25:53  
 */
public class TLAgentInfoResponseDTO {
	
	private String TRX_CODE="";
	private String VERSION="";
	private String DATA_TYPE="";
	private String REQ_SN="";
	private String RET_CODE="";	
	private String ERR_MSG="";	
	private String REPTIME="";	
	private String SIGNED_MSG="";
	
	public static TLAgentInfoResponseDTO packRsp(TLAgentInfoRequestDTO inforeq, String errcode,String strMsg) {
		
		TLAgentInfoResponseDTO info = new TLAgentInfoResponseDTO();
		if (inforeq != null) {
			
			if(errcode==null) errcode="1000";
			info.setDATA_TYPE(inforeq.getDATA_TYPE());
			info.setERR_MSG(strMsg);
			info.setREPTIME(inforeq.getREQTIME());
			info.setREQ_SN(inforeq.getREQ_SN());
			info.setRET_CODE(errcode);
			info.setTRX_CODE(inforeq.getTRX_CODE());
			info.setVERSION(inforeq.getVERSION());
			
		} else {
			
			if(errcode==null) errcode="1001";
			info.setDATA_TYPE("");
			info.setERR_MSG(strMsg);
			info.setREPTIME("");
			info.setREQ_SN("");
			info.setRET_CODE("1001");
			info.setTRX_CODE("");
			info.setVERSION("");
		}
		
		return info;
	}
	
	
	public String getTRX_CODE() {
		return TRX_CODE;
	}
	public void setTRX_CODE(String tRXCODE) {
		TRX_CODE = tRXCODE;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(String dATATYPE) {
		DATA_TYPE = dATATYPE;
	}
	public String getREQ_SN() {
		return REQ_SN;
	}
	public void setREQ_SN(String rEQSN) {
		REQ_SN = rEQSN;
	}
	public String getRET_CODE() {
		return RET_CODE;
	}
	public void setRET_CODE(String rETCODE) {
		RET_CODE = rETCODE;
	}
	public String getERR_MSG() {
		return ERR_MSG;
	}
	public void setERR_MSG(String eRRMSG) {
		ERR_MSG = eRRMSG;
	}
	public String getREPTIME() {
		return REPTIME;
	}
	public void setREPTIME(String rEPTIME) {
		REPTIME = rEPTIME;
	}
	public String getSIGNED_MSG() {
		return SIGNED_MSG;
	}
	public void setSIGNED_MSG(String sIGNEDMSG) {
		SIGNED_MSG = sIGNEDMSG;
	}

}
