package com.titanjr.checkstand.strategy;

import com.fangcang.util.SpringContextUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class StrategyFactory {

    public static PayRequestStrategy getPayRequestStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (PayRequestStrategy)SpringContextUtil.getBean("netBankPayStrategy");
        }

        if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || payTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum)){
            return (PayRequestStrategy)SpringContextUtil.getBean("QRPayStrategy");
        }

        return null;
    }

}
