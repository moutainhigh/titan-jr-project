package com.titanjr.checkstand.controller;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.dao.OrderPayRequestDao;
import com.titanjr.checkstand.dto.TitanPayDTO;
import com.titanjr.checkstand.request.TLNetBankPayRequest;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.pay.PayRequestStrategy;
import com.titanjr.checkstand.util.SignMsgBuilder;
import com.titanjr.checkstand.util.WebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/pay")
public class PaymentController extends BaseController {
    /** 
	 * 
	 */
	private static final long serialVersionUID = 2974835411917209774L;
	private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Resource
    private OrderPayRequestDao gateWayPayDao;
	

    /**
     * 所有支付入口，进入后分配到具体的支付接口返回不同的参数
     * @param request
     * @param attr
     * @throws Exception
     */
    @RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
        
        PayTypeEnum payTypeEnum = PayTypeEnum.getPayTypeEnum(request.getParameter("payType"));
        if(payTypeEnum == null){
        	logger.error("参数错误，未找到对应的支付方式，payType={}", request.getParameter("payType"));
        	return super.payFailedCallback(model);
        }
        
        //根据支付方式来判定走到具体哪个接口
        PayRequestStrategy payRequestStrategy =  StrategyFactory.getPayRequestStrategy(payTypeEnum);
        String redirectUrl = payRequestStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        
        return "redirect:" + redirectUrl;
        
    }
    
    
    /**
     * 网银支付
     * @author Jerry
     * @date 2017年11月27日 上午11:03:53
     */
    @RequestMapping(value = "/netBankPay", method = {RequestMethod.GET, RequestMethod.POST})
    public String netBankPay(HttpServletRequest request, Model model) throws Exception {
    	
        TitanPayDTO payDTO = WebUtils.switch2RequestDTO(TitanPayDTO.class, request);
        ValidateResponse res = GenericValidate.validateNew(payDTO);
        if (!res.isSuccess()){
        	logger.error("参数错误：{}", res.getReturnMessage());
        	return super.payFailedCallback(model);
        }
        
        //此处应调Service
        //查询订单是否存在
        //获取网关支付配置，封装相应渠道的请求参数
        //保存返回结果或者回调请求
        
        //test
        TLNetBankPayRequest tlNetBankPayRequest = new TLNetBankPayRequest();
        tlNetBankPayRequest.setInputCharset("1");
        tlNetBankPayRequest.setPickupUrl("http://192.168.0.77:8080/checkstand/callback/tlPayConfirmPage.action");
        tlNetBankPayRequest.setReceiveUrl("http://192.168.0.77:8080/checkstand/callback/tlPayNotify.action");
        tlNetBankPayRequest.setVersion("v1.0");
        tlNetBankPayRequest.setLanguage("1");
        tlNetBankPayRequest.setSignType("0");
        tlNetBankPayRequest.setMerchantId("100020091218001");
        tlNetBankPayRequest.setOrderNo(payDTO.getOrderNo());
        tlNetBankPayRequest.setOrderAmount(payDTO.getOrderAmount());
        tlNetBankPayRequest.setOrderCurrency("0");
        tlNetBankPayRequest.setOrderDatetime(payDTO.getOrderTime());
        tlNetBankPayRequest.setPayType("1");
        tlNetBankPayRequest.setIssuerId(payDTO.getBankInfo());
        tlNetBankPayRequest.setSignMsg(SignMsgBuilder.getSignMsgForGateWayPay(tlNetBankPayRequest));
        tlNetBankPayRequest.setServerUrl("http://ceshi.allinpay.com/gateway/index.do");
        model.addAttribute("tlNetBankPayRequest", tlNetBankPayRequest);
        
        model.addAttribute("tlNetBankPayRequest", tlNetBankPayRequest);
        
        return "payment/tl_gateWayPay";
        
    }
    

    /**
     * 第三方扫码支付
     * @author Jerry
     * @date 2017年11月27日 上午11:15:27
     */
    @ResponseBody
    @RequestMapping(value = "/qrCodePay", method = {RequestMethod.GET, RequestMethod.POST})
    public String qrCodePay(HttpServletRequest request, Model model) throws Exception {
    	
    	TitanPayDTO payDTO = WebUtils.switch2RequestDTO(TitanPayDTO.class, request);
        ValidateResponse res = GenericValidate.validateNew(payDTO);
        if (!res.isSuccess()){
        	logger.error("参数错误：{}", res.getReturnMessage());
        	return super.payFailedCallback(model);
        }
        
        //此处应调Service
        //查询订单是否存在
        //获取网关支付配置，封装相应渠道的请求参数
        //保存返回结果或者回调请求
        
        
        
        return null;
        
    }
    
    
    /**
     * 快捷支付
     * @author Jerry
     * @date 2017年11月27日 上午11:30:27
     */
    @ResponseBody
    @RequestMapping(value = "/quickPay", method = {RequestMethod.GET, RequestMethod.POST})
    public String quickPay(HttpServletRequest request, Model model) throws Exception {

        return null;
    }

}
