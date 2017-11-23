package com.titanjr.checkstand.strategy;

import com.titanjr.checkstand.util.WebUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/20.
 */
@Service("QRPayStrategy")
public class QRPayStrategy implements PayRequestStrategy{

    @Override
    public String redirectResult(HttpServletRequest request) {
        //针对快捷支付的校验；
        //可能泛起请求，将参数封装；
        return WebUtils.getRequestBaseUrl(request) + "/pay/QRCodePay.shtml";
    }
}
