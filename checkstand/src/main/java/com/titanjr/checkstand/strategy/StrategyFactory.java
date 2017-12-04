package com.titanjr.checkstand.strategy;

import com.fangcang.util.SpringContextUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.strategy.pay.PayRequestStrategy;
import com.titanjr.checkstand.strategy.payQuery.PayQueryStrategy;
import com.titanjr.checkstand.strategy.refund.OrderRefundStrategy;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class StrategyFactory {

	/**
	 * 支付策略
	 * @author zhaoshan
	 * @date 2017年12月1日 上午10:04:42
	 */
    public static PayRequestStrategy getPayRequestStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (PayRequestStrategy)SpringContextUtil.getBean("netBankPayStrategy");
        }

        if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || PayTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum)){
            return (PayRequestStrategy)SpringContextUtil.getBean("qrPayStrategy");
        }

        return null;
    }
    
    /**
     * 订单（支付结果）查询策略
     * @author Jerry
     * @date 2017年12月1日 上午10:04:23
     */
    public static PayQueryStrategy getPayQueryStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (PayQueryStrategy)SpringContextUtil.getBean("netBankPayQueryStrategy");
        }

        if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || PayTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum) || 
                PayTypeEnum.WECHAT.equals(payTypeEnum)){
            return (PayQueryStrategy)SpringContextUtil.getBean("qrPayQueryStrategy");
        }
        
        if (PayTypeEnum.QUICK_NEW.equals(payTypeEnum)){
            return (PayQueryStrategy)SpringContextUtil.getBean("quickPayQueryStrategy");
        }

        return null;
    }
    
    public static OrderRefundStrategy getRefundStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum) || PayTypeEnum.COMP_EBANK.equals(payTypeEnum)
                || PayTypeEnum.CREDIT_EBANK.equals(payTypeEnum)){
            return (OrderRefundStrategy)SpringContextUtil.getBean("netBnakOrderRefundStrategy");
        }

        /*if (PayTypeEnum.QR_WECHAT_URL.equals(payTypeEnum) || PayTypeEnum.QR_ALIPAY_URL.equals(payTypeEnum) ||
                PayTypeEnum.QR_ALIPAY.equals(payTypeEnum) || PayTypeEnum.QR_WECHAT.equals(payTypeEnum) || 
                PayTypeEnum.WECHAT.equals(payTypeEnum)){
            return (PayQueryStrategy)SpringContextUtil.getBean("qrPayQueryStrategy");
        }
        
        if (PayTypeEnum.QUICK_NEW.equals(payTypeEnum)){
            return (PayQueryStrategy)SpringContextUtil.getBean("qkPayQueryStrategy");
        }*/

        return null;
    }

}
