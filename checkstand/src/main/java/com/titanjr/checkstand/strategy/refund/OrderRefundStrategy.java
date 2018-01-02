package com.titanjr.checkstand.strategy.refund;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单退款策略
 * @author Jerry
 * @date 2017年12月4日 上午10:58:40
 */
public interface OrderRefundStrategy {

    /**
     * 对请求参数使用对应的策略来处理
     * @param request
     * @return
     */
    String redirectResult(HttpServletRequest request);

}
