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
 * 通联对账文件的交易类型
 * @author Jerry
 * @date 2018年3月21日 下午5:13:01  
 */
public enum TLTradeTypeEnum {
	
	NETBANK_PAY("ZF", "网银支付", "1"),
	NETBANK_REFUND("TH", "网银退款", "2"),
	NETBANK_OFFSET("CX", "网银冲销", "3"),
	WECHAT_PAY("微信支付", "微信支付", "1"),
	ALIPAY_PAY("支付宝支付", "支付宝支付", "1"),
	WECHAT_REFUND("微信退货", "微信退款", "2"),
	ALIPAY_REFUND("支付宝退货", "支付宝退款", "2"),
	AGENT_PAY("0", "代付", "4"),
	AGENT_RECEIVE("1", "代收", "5"),//目前没有代收
	UNKNOW_TYPE("WZ", "未知类型", "0");
	
	public String key;
	public String value;
	public String tradeType; //房仓对应交易类型  1充值  2退款 3冲销  4提现（代付）5代收  0未知
	
	private TLTradeTypeEnum(String key, String value, String tradeType) {
		this.key = key;
		this.value = value;
		this.tradeType = tradeType;
	}
	
	public static String getTradeType(String key){
		
		if(!StringUtil.isValidString(key)){
			return UNKNOW_TYPE.tradeType;
		}
		for (TLTradeTypeEnum tradeTypeEnum : TLTradeTypeEnum.values()) {
			if(key.equals(tradeTypeEnum.key)){
				return tradeTypeEnum.tradeType;
			}
		}
		return UNKNOW_TYPE.tradeType;
		
	}
	
	public static TLTradeTypeEnum getByKey(String key){
		
		if(!StringUtil.isValidString(key)){
			return UNKNOW_TYPE;
		}
		for (TLTradeTypeEnum tradeTypeEnum : TLTradeTypeEnum.values()) {
			if(key.equals(tradeTypeEnum.key)){
				return tradeTypeEnum;
			}
		}
		return UNKNOW_TYPE;
		
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
