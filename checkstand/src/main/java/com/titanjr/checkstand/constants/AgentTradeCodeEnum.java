/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentTradeCodeEnum.java
 * @author Jerry
 * @date 2017年12月25日 上午10:53:35  
 */
package com.titanjr.checkstand.constants;

/**
 * @author Jerry
 * @date 2017年12月25日 上午10:53:35  
 */
public enum AgentTradeCodeEnum {
	
	AGENT_PAY("100014", "单笔实时代付"),
	AGENT_QUERY("200004", "交易结果查询"),
	AGENT_DOWNLOAD("200002", "对账文件下载");
	
	private String code;
	private String remark;
	
	
	private AgentTradeCodeEnum(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
