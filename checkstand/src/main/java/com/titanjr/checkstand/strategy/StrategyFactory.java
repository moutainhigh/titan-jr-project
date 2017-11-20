package com.titanjr.checkstand.strategy;

import com.titanjr.checkstand.constants.PayTypeEnum;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class StrategyFactory {

    public static PayRequestStrategy getPayRequestStrategy(PayTypeEnum payTypeEnum){

        if (PayTypeEnum.PERSON_EBANK.equals(payTypeEnum)){
            return new NetBankPayStrategy();
        }

        return null;
    }

}
