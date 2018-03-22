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
public enum QrCodeTradeTypeEnum {
	
	WECHAT_PAY("微信支付", "1"),
	ALIPAY_PAY("支付宝支付", "1"),
	WECHAT_REFUND("微信退货", "2"),
	ALIPAY_REFUND("支付宝退货", "2"),
	UNKONW_TRADE_TYPE("未知类型", "0");
	
	public String key;
	public String value;//1充值  2退款
	
	private QrCodeTradeTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static String getValue(String key){
		
		if(!StringUtil.isValidString(key)){
			return UNKONW_TRADE_TYPE.value;
		}
		for (QrCodeTradeTypeEnum qrCodeTradeTypeEnum : QrCodeTradeTypeEnum.values()) {
			if(key.equals(qrCodeTradeTypeEnum.key)){
				return qrCodeTradeTypeEnum.value;
			}
		}
		return UNKONW_TRADE_TYPE.value;
		
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

}
