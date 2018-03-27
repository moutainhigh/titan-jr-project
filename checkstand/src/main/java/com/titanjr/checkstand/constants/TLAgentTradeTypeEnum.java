/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QrCodeTradeTypeEnum.java
 * @author Jerry
 * @date 2018年3月21日 下午5:13:01  
 */
package com.titanjr.checkstand.constants;

import com.fangcang.util.StringUtil;

/**
 * 扫码支付对账文件的交易类型
 * @author Jerry
 * @date 2018年3月21日 下午5:13:01  
 */
public enum TLAgentTradeTypeEnum {
	
	AGENT_PAY("0", "代付", "4"),
	AGENT_RECEIVE("1", "代收", "5"),//目前没有代收
	UNKONW_TYPE("WZ", "未知类型", "0");
	
	public String key;
	public String value;
	public String tradeType; //1充值  2退款 3冲销 4提现 5代收 0未知类型
	
	private TLAgentTradeTypeEnum(String key, String value, String tradeType) {
		this.key = key;
		this.value = value;
		this.tradeType = tradeType;
	}
	
	public static TLAgentTradeTypeEnum getByKey(String key){
		
		if(!StringUtil.isValidString(key)){
			return UNKONW_TYPE;
		}
		for (TLAgentTradeTypeEnum tradeTypeEnum : TLAgentTradeTypeEnum.values()) {
			if(key.equals(tradeTypeEnum.key)){
				return tradeTypeEnum;
			}
		}
		return UNKONW_TYPE;
		
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

}
