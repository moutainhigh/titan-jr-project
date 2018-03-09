package com.titanjr.fop.constants;

import com.ctol.fangcang.common.ParamServiceImpl;

/**
 * Created by zhaoshan on 2018/1/8.
 */
public class CommonConstants {

    /**
     * 请求所需appSecret，不同环境不一样
     */
    public static String appSecret;

    /**
     * 实际的请求地址列表，不同环境不一样
     * 可做路由与负载均衡所有
     */
    public static String actualUrl;

    public static final String fetchUrl = "/fetchServiceUrl.shtml";

    //设置默认提现通道，可通过接口调整修改
    public static WithDrawChannelEnum defaultChannel = WithDrawChannelEnum.TL_CHANNEL;

    public void initParam() {
        CommonConstants.appSecret = ParamServiceImpl.getInstance().getConfValue("{pay.appSecret}");
        CommonConstants.actualUrl = ParamServiceImpl.getInstance().getConfValue("{pay.serviceUrl}");
    }

}
