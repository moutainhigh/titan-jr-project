package com.titanjr.checkstand.strategy;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public interface PayRequestStrategy {

    /**
     * 对请求参数使用对应的策略来处理
     * @param request
     * @return
     */
    String redirectResult(HttpServletRequest request);

}
