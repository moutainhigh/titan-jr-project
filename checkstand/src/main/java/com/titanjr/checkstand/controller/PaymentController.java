package com.titanjr.checkstand.controller;

import java.util.Date;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.util.DateUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.dao.OrderPayRequestDao;
import com.titanjr.checkstand.dto.OrderPayRequestDTO;
import com.titanjr.checkstand.request.TLGateWayPayRequest;
import com.titanjr.checkstand.service.TitanFinancialCallbackService;
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
	
	@Resource
    private TitanFinancialCallbackService titanFinancialCallbackService;
	

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
        	logger.error("参数错误，未找到对应的支付方式，payType="+request.getParameter("payType"));
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
    	
        OrderPayRequestDTO payDTO = WebUtils.switch2RequestDTO(OrderPayRequestDTO.class, request);
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
        TLGateWayPayRequest tlGateWayPayRequest = new TLGateWayPayRequest();
        tlGateWayPayRequest.setInputCharset("1");
        tlGateWayPayRequest.setPickupUrl("http://192.168.0.77:8080/checkstand/callback/tlPayConfirmPage.action");
        tlGateWayPayRequest.setReceiveUrl("http://192.168.0.77:8080/checkstand/callback/tlPayNotify.action");
        tlGateWayPayRequest.setVersion("v1.0");
        tlGateWayPayRequest.setLanguage("1");
        tlGateWayPayRequest.setSignType("1");
        tlGateWayPayRequest.setMerchantId(payDTO.getMerchantNo());
        tlGateWayPayRequest.setOrderNo(payDTO.getOrderNo());
        tlGateWayPayRequest.setOrderAmount(payDTO.getOrderAmount());
        tlGateWayPayRequest.setOrderCurrency("0");
        tlGateWayPayRequest.setOrderDatetime(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        tlGateWayPayRequest.setPayType("1");
        tlGateWayPayRequest.setIssuerId("vbank");
        tlGateWayPayRequest.setSignMsg(SignMsgBuilder.getSignMsgForGateWayPay(tlGateWayPayRequest));
        tlGateWayPayRequest.setServerUrl("http://ceshi.allinpay.com/gateway/index.do");
        model.addAttribute("tlGateWayPayRequest", tlGateWayPayRequest);
        
        model.addAttribute("tlGateWayPayRequest", tlGateWayPayRequest);
        
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
