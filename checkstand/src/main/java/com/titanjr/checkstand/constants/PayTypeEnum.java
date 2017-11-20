package com.titanjr.checkstand.constants;

/**
 * Created by zhaoshan on 2017/11/15.
 */
public enum PayTypeEnum {

    PERSON_EBANK("11","","个人银行"),
    COMP_EBANK("12","","企业银行"),
    CREDIT_EBANK("13","","信用卡"),
    STD_WAP("21","","标准wap支付"),
    STD_AUTH_WAP("22","","标准认证wap支付"),
    QUICK_WAP("23","","快捷wap支付"),
    AUTH_WAP("27","","认证wap支付"),
    WECHAT("28","","微信支付"),
    PINGAN_OFFLINE("29","","平安银行线下充值"),
    QR_WECHAT_URL("30","","微信扫码支付Url"),
    QR_WECHAT("31","","微信扫码支付二维码"),
    QR_ALIPAY_URL("32","","支付宝扫码支付url"),
    QR_ALIPAY("33","","支付宝扫码支付二维码"),
    QUICK_NEW("41","","新快捷支付");




    public String typeKey;
    public String channelTypeKey;
    public String typeValue;

    PayTypeEnum(String typeKey,String channelTypeKey,String typeValue){
        this.typeKey = typeKey;
        this.channelTypeKey = channelTypeKey;
        this.typeValue = typeValue;
    }


}
