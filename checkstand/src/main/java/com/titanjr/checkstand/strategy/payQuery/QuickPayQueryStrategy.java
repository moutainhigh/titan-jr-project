package com.titanjr.checkstand.strategy.payQuery;

import com.titanjr.checkstand.util.WebUtils;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 快捷支付订单查询
 * @author Jerry
 * @date 2017年12月1日 上午10:54:30
 */
@Service("quickPayQueryStrategy")
public class QuickPayQueryStrategy implements PayQueryStrategy{

    @Override
    public String redirectResult(HttpServletRequest request) {

        return WebUtils.getRequestBaseUrl(request) + "/payOrder/quickPayQuery.shtml";
    }
}
