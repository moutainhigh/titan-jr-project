package com.titanjr.checkstand.constants;

import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2017/11/15.
 */
public enum PayTypeEnum {

    PERSON_EBANK("11","1","个人银行","1"),
    COMP_EBANK("12","4","企业银行","1"),
    CREDIT_EBANK("13","11","信用卡","1"),
    WECHAT("28","W02","微信公众号支付","2"),
    PINGAN_OFFLINE("29","","平安银行线下充值",null),
    QR_WECHAT_URL("30","W01","微信扫码支付Url","2"),
    QR_WECHAT("31","","微信扫码支付二维码","2"),
    QR_ALIPAY_URL("32","A01","支付宝扫码支付url","2"),
    QR_ALIPAY("33","","支付宝扫码支付二维码","2"),
    QUICK_NEW("41","","新快捷支付","3"),
    AGENT_TRADE("AT","","账户交易","4");

    public String typeKey; //泰坦金融收银台传过来的值
    public String channelTypeKey; //上送渠道的值
    public String typeValue;
    public String combPayType; //用于获取网关地址

    PayTypeEnum(String typeKey,String channelTypeKey,String typeValue, String combPayType){
        this.typeKey = typeKey;
        this.channelTypeKey = channelTypeKey;
        this.typeValue = typeValue;
        this.combPayType = combPayType;
    }

    /**
     * 根据类型获取支付枚举
     * @param typeKey
     * @return
     */
    public static PayTypeEnum getPayTypeEnum(String typeKey){
    	if(!StringUtil.isValidString(typeKey)){
    		return null;
    	}
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()){
            if (typeKey.equals(payTypeEnum.typeKey)){
                return payTypeEnum;
            }
        }
        return null;
    }

}
