package com.titanjr.checkstand.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/11/17.
 */
public class AccessLimitConfig {

    //请求接口每秒访问次数配置，比如支付接口，一秒钟最多访问多少次
    public static final Map<OperateTypeEnum,Long> frequencyConfigMap = new HashMap<OperateTypeEnum, Long>();

    //请求间隔访问配置，比如支付订单，同一张单两次重复支付要求间隔3s，key是类型，value是毫秒
    public static final Map<OperateTypeEnum,Long> intervalConfigMap = new HashMap<OperateTypeEnum, Long>();

    static {

        frequencyConfigMap.put(OperateTypeEnum.PAY_REQUEST,3L);

        intervalConfigMap.put(OperateTypeEnum.PAY_REQUEST,2000L);

    }

}
