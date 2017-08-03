package com.fangcang.titanjr.enums;

import com.fangcang.util.StringUtil;

/**
 * 融数支付方式
 *
 */
public enum PayTypeEnum {

    Personal_Banking("11","个人银行","2"),
    Corporate_Banking("12","企业银行","1"),
    Credit_Card("13","信用卡","3"),
    Wap("21","wap支付","6"),
    Certified_Payment("22","认证支付","7"),
    Quick_Payment("23","快捷wap支付",""),
    WECHAT_PAY("28","微信公众号支付","8"),
    SAFE_BANK_RECHARDE("29","平安银行线下充值",""),
    WECHAT_URL("30","微信扫码支付Url","9"),
    WECHAT_QRCODE("31","微信扫码支付二维码",""),
    ALIPAY_URL("32","支付宝扫码支付url","9"),
    ALIPAY_QRCODE("33","支付宝扫码支付url",""),
    QUICK_PAY_NEW("41","新快捷支付","11");
    
	public String key;
	
	public String value;
	
	//没有对接的支付此值为空（泰坦金融支付方式）
	public String linePayType;
	
	private PayTypeEnum(String key, String value,String linePayType) {
		this.key = key;
		this.value = value;
		this.linePayType = linePayType;
	}
	
	public static PayTypeEnum getPayTypeEnum(String linePayType){
		if(StringUtil.isValidString(linePayType)){
			for(PayTypeEnum payTypeEnum:PayTypeEnum.values()){
				if(linePayType.equals(payTypeEnum.linePayType)){
					return payTypeEnum;
				}
			}
		}
		return null;
	}
	
	public static PayTypeEnum getPayTypeEnumByKey(String key){
		if(StringUtil.isValidString(key)){
			for(PayTypeEnum payTypeEnum:PayTypeEnum.values()){
				if(key.equals(payTypeEnum.key)){
					return payTypeEnum;
				}
			}
		}
		return null;
	}
	
	public static boolean isRealTimeToAccount(String key){
		if(!StringUtil.isValidString(key)){
			return false;
		}
		if(PayTypeEnum.WECHAT_URL.key.equals(key)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断key是否在指定枚举集合中
	 */
	public static boolean between(String key,PayTypeEnum... enums){
		if(key == null){
			return false;
		}
		
		if(enums == null){
			return false;
		}
		
		for(PayTypeEnum e : enums){
			if(key.equals(e.getKey())){
				return true;
			}
		}
		
		return false;
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

	public String getLinePayType() {
		return linePayType;
	}

	public void setLinePayType(String linePayType) {
		this.linePayType = linePayType;
	}
	
}
