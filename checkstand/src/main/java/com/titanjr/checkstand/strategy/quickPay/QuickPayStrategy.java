package com.titanjr.checkstand.strategy.quickPay;

import javax.servlet.http.HttpServletRequest;

/**
 * 快捷支付策略
 * @author Jerry
 * @date 2018年1月4日 下午6:45:34
 */
public interface QuickPayStrategy {

    /**
     * 对请求参数使用对应的策略来处理
     * @param request
     * @return
     */
    String redirectResult(HttpServletRequest request);

}
