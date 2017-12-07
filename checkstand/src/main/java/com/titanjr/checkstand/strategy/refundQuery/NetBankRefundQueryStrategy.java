package com.titanjr.checkstand.strategy.refundQuery;

import com.titanjr.checkstand.util.WebUtils;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 网银支付订单查询
 * @author Jerry
 * @date 2017年12月1日 上午10:54:53
 */
@Service("netBankRefundQueryStrategy")
public class NetBankRefundQueryStrategy implements RefundQueryStrategy{


    @Override
    public String redirectResult(HttpServletRequest request) {

        return WebUtils.getRequestBaseUrl(request) + "/rfQuery/netBankRefundQuery.shtml";
    }
}
