package com.titanjr.checkstand.controller;

import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.strategy.PayRequestStrategy;
import com.titanjr.checkstand.strategy.StrategyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhaoshan on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/pay")
public class PaymentController extends BaseController {

    /**
     * 所有支付入口，进入后分配到具体的支付接口返回不同的参数
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public void entrance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //付款入口,根据付款类型来判定走到具体哪个接口
        //需根据得到的参数类型来做校验
        PayTypeEnum payTypeEnum = getPayType(request);
        PayRequestStrategy payRequestStrategy =  StrategyFactory.getPayRequestStrategy(payTypeEnum);
        String redirectUrl = payRequestStrategy.redirectResult(request);
        response.sendRedirect(redirectUrl);
    }

    @RequestMapping(value = "/gateWayPay", method = {RequestMethod.GET, RequestMethod.POST})
    public String batchQuery(HttpServletRequest request, Model model) throws Exception {

        return "gateWayPay";
    }


    @ResponseBody
    @RequestMapping(value = "/QRCodePay", method = {RequestMethod.GET, RequestMethod.POST})
    public String payPage(HttpServletRequest request, Model model) throws Exception {

        return "QRCodePayJsonString";
    }


    /**
     * 测试返回；
     * @param request
     * @return
     */
    private PayTypeEnum getPayType(HttpServletRequest request){
        if ("01".equals(request.getParameterMap().get("payType")[0])){
            return PayTypeEnum.PERSON_EBANK;
        }
        if ("02".equals(request.getParameterMap().get("payType")[0])){
            return PayTypeEnum.QR_WECHAT_URL;
        }
       return PayTypeEnum.QR_WECHAT_URL;
    }


}
