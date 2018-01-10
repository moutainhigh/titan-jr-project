package com.titanjr.checkstand.strategy.query;

import com.titanjr.checkstand.util.WebUtils;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三方支付订单查询
 * @author Jerry
 * @date 2017年12月1日 上午10:54:39
 */
@Service("qrPayQueryStrategy")
public class QrCodePayQueryStrategy implements QueryStrategy{

    @Override
    public String redirectResult(HttpServletRequest request) {

        return WebUtils.getRequestBaseUrl(request) + "/query/qrCodePayQuery.shtml";
    }
}
