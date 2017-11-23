package com.titanjr.checkstand.strategy;

import com.titanjr.checkstand.util.WebUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 网银支付的策略，校验逻辑不同
 * Created by zhaoshan on 2017/11/20.
 */
@Service("netBankPayStrategy")
public class NetBankPayStrategy implements PayRequestStrategy{


    @Override
    public String redirectResult(HttpServletRequest request) {

        //针对快捷支付的参数校验，如果失败返回失败页面


        return WebUtils.getRequestBaseUrl(request) + "/pay/gateWayPay.shtml";
    }
}
