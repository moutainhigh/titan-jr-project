package com.titanjr.checkstand.strategy;

import com.titanjr.checkstand.util.WebUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/20.
 */
@Service
public class NetBankPayStrategy implements PayRequestStrategy{


    @Override
    public String redirectResult(HttpServletRequest request) {

        //针对快捷支付的参数校验，如果失败返回失败页面


        return WebUtil.getRequestBaseUrl(request) + "/pay/gateWayPay.shtml";
    }
}
