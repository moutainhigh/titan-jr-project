package com.titanjr.checkstand.strategy.pay;

import com.titanjr.checkstand.constants.PayTypeEnum;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/20.
 */
@Service("qrCodePayStrategy")
public class qrCodePayStrategy implements PayRequestStrategy{

    @Override
    public String redirectResult(HttpServletRequest request) {
    	
    	PayTypeEnum payTypeEnum = PayTypeEnum.getPayTypeEnum(request.getParameter("payType"));
    	String redirectUrl = "/pay/qrCodePay.shtml";
    	//微信公众号
    	if(PayTypeEnum.WECHAT.equals(payTypeEnum)){
    		redirectUrl = "/pay/wechatPay.shtml";
    	}
    	
        return redirectUrl;
        
    }
}
