package com.titanjr.checkstand.strategy.refundQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * 退款查询策略
 * @author Jerry
 * @date 2017年12月1日 上午10:23:20
 */
public interface RefundQueryStrategy {

    /**
     * 对请求参数使用对应的策略来处理
     * @param request
     * @return
     */
    String redirectResult(HttpServletRequest request);

}
