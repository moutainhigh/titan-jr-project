package com.fangcang.titanjr.dto.bean;

import com.fangcang.util.StringUtil;

public enum PayTypeEnum {

    Personal_Banking("11","个人银行","2"),Corporate_Banking("12","企业银行","1"),
    Credit_Card("13","信用卡","3"),Wap("21","wap支付","6"),Certified_Payment("22","认证支付","7")
    ,WECHAT_PAY("28","微信支付","8"),WECHAT_URL("30","微信扫码支付Url","9"),WECHAT_QRCODE("31","微信扫码支付二维码","10")
    ,SAFE_BANK_RECHARDE("29","平安银行线下充值","11");
    
	public String key;
	
	public String value;
	
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
