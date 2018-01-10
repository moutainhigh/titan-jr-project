package com.titanjr.checkstand.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/11/17.
 */
public class AccessLimitConfig {

    //请求接口每秒访问次数配置，比如支付接口，一秒钟最多访问多少次
    public static final Map<BusiCodeEnum,Long> frequencyConfigMap = new HashMap<BusiCodeEnum, Long>();

    //请求间隔访问配置，比如支付订单，同一张单两次重复支付要求间隔3s，key是类型，value是毫秒
    public static final Map<BusiCodeEnum,Long> intervalConfigMap = new HashMap<BusiCodeEnum, Long>();

    static {

        frequencyConfigMap.put(BusiCodeEnum.PAY_REQUEST,300L);
        frequencyConfigMap.put(BusiCodeEnum.PAY_QUERY,300L);
        frequencyConfigMap.put(BusiCodeEnum.REFUND_REQUEST,300L);
        frequencyConfigMap.put(BusiCodeEnum.REFUND_QUERY,300L);
        frequencyConfigMap.put(BusiCodeEnum.AGENT_TRADE,300L);
        frequencyConfigMap.put(BusiCodeEnum.CARD_BIN_QUERY,300L);
        frequencyConfigMap.put(BusiCodeEnum.QUICK_PAY_CONFIRM,300L);
        frequencyConfigMap.put(BusiCodeEnum.RE_SEND_MSG,300L);

        intervalConfigMap.put(BusiCodeEnum.PAY_REQUEST,2000L);

    }

}
