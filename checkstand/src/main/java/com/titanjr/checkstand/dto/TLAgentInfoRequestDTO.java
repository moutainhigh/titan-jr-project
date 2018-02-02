/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentInfoRequest.java
 * @author Jerry
 * @date 2017年12月25日 下午2:56:41  
 */
package com.titanjr.checkstand.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2017年12月25日 下午2:56:41  
 */
public class TLAgentInfoRequestDTO {
	
	//交易代码   {@link AgentTradeCodeEnum}
	@NotBlank
	private String TRX_CODE="";
	//版本  固定值 03
	@NotBlank
	private String VERSION="";
	//数据格式   固定2：xml格式
	@NotBlank
	private String DATA_TYPE="";
	//处理级别   0-9 0优先级最低
	@NotBlank
	private String LEVEL="";
	//用户名（私钥的名称+04）
	@NotBlank
	private String USER_NAME="";
	//密码
	@NotBlank
	private String USER_PASS="";
	//交易流水号（或者叫订单号，商户的唯一标示）
	@NotBlank
	private String REQ_SN="";
	private String REQTIME;
	//签名
	@NotBlank
	private String SIGNED_MSG="";
	
	public String getDATA_TYPE() {
		return DATA_TYPE;
	}
	public void setDATA_TYPE(String data_type) {
		DATA_TYPE = data_type;
	}
	public String getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(String level) {
		LEVEL = level;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String user_name) {
		USER_NAME = user_name;
	}
	public String getUSER_PASS() {
		return USER_PASS;
	}
	public void setUSER_PASS(String user_pass) {
		USER_PASS = user_pass;
	}
	public String getREQ_SN() {
		return REQ_SN;
	}
	public void setREQ_SN(String req_sn) {
		REQ_SN = req_sn;
	}
	public String getSIGNED_MSG() {
		return SIGNED_MSG;
	}
	public void setSIGNED_MSG(String signed_msg) {
		SIGNED_MSG = signed_msg;
	}
	public String getTRX_CODE() {
		return TRX_CODE;
	}
	public void setTRX_CODE(String trx_code) {
		TRX_CODE = trx_code;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String version) {
		VERSION = version;
	}
	public String getREQTIME() {
		return REQTIME;
	}
	public void setREQTIME(String rEQTIME) {
		REQTIME = rEQTIME;
	}

}
