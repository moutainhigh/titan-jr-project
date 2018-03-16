/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentTradeCodeEnum.java
 * @author Jerry
 * @date 2017年12月25日 上午10:53:35  
 */
package com.titanjr.checkstand.constants;

/**
 * 融宝的代付没有tradeCode，我们自己定义处理
 * @author Jerry
 * @date 2017年12月25日 上午10:53:35  
 */
public enum TradeCodeEnum {
	
	TL_AGENT_PAY("100014", "通联-单笔实时代付"),
	TL_AGENT_QUERY("200004", "通联-交易结果查询"),
	TL_AGENT_DOWNLOAD("200002", "通联-账户交易-对账文件下载"),
	TL_GATEWAY_DOWNLOAD("200005", "通联-网关支付-对账文件下载"),
	TL_QRCODE_DOWNLOAD("200006", "通联-扫码/公众号-对账文件下载"),
	RB_AGENT_PAY("300001", "融宝-单笔实时代付"),
	RB_AGENT_QUERY("300002", "融宝-代付查询"),
	RB_AGENT_DOWNLOAD("300003", "融宝-对账文件下载");
	
	private String code;
	private String remark;
	
	
	private TradeCodeEnum(String code, String remark) {
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
