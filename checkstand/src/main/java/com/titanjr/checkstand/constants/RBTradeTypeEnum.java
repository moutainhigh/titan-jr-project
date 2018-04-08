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
 * 融宝对账文件的交易类型
 * @author Jerry
 * @date 2018年3月21日 下午5:13:01  
 */
public enum RBTradeTypeEnum {
	
	RB_PAY("", "充值", "1"),
	RB_REFUND("", "退款", "2"),
	RB_AGENT("", "代付", "4"),
	UNKNOW_TYPE("", "未知类型", "0");
	
	public String key;
	public String value;
	public String tradeType; //房仓对应交易类型  1充值  2退款 3冲销  4提现（代付）5代收  0未知
	
	private RBTradeTypeEnum(String key, String value, String tradeType) {
		this.key = key;
		this.value = value;
		this.tradeType = tradeType;
	}
	
	public static String getTradeType(String key){
		
		if(!StringUtil.isValidString(key)){
			return UNKNOW_TYPE.tradeType;
		}
		for (RBTradeTypeEnum tradeTypeEnum : RBTradeTypeEnum.values()) {
			if(key.equals(tradeTypeEnum.key)){
				return tradeTypeEnum.tradeType;
			}
		}
		return UNKNOW_TYPE.tradeType;
		
	}
	
	public static String getValueByTradeType(String tradeType){
		
		if(!StringUtil.isValidString(tradeType)){
			return UNKNOW_TYPE.value;
		}
		for (RBTradeTypeEnum tradeTypeEnum : RBTradeTypeEnum.values()) {
			if(tradeType.equals(tradeTypeEnum.tradeType)){
				return tradeTypeEnum.value;
			}
		}
		return UNKNOW_TYPE.value;
		
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
