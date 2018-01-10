package com.titanjr.checkstand.controller;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.Wxutil;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.TitanPayDTO;
import com.titanjr.checkstand.request.RBQuickPayRequest;
import com.titanjr.checkstand.request.TLNetBankPayRequest;
import com.titanjr.checkstand.request.TLQrCodePayRequest;
import com.titanjr.checkstand.respnse.TitanQrCodePayResponse;
import com.titanjr.checkstand.respnse.TitanQuickPayResponse;
import com.titanjr.checkstand.service.RBQuickPayService;
import com.titanjr.checkstand.service.TLPaymentService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.pay.PayRequestStrategy;
import com.titanjr.checkstand.util.CommonUtil;
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
import javax.servlet.http.HttpServletResponse;

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
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Resource
    private TLPaymentService tlPaymentService;
	
	@Resource
	private RBQuickPayService rbQuickPayService;
	

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
    public String netBankPay(HttpServletRequest request, Model model) {
    	
        try {
        	
			TitanPayDTO payDTO = WebUtils.switch2RequestDTO(TitanPayDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(payDTO);
			if (!res.isSuccess()){
				logger.error(""
						+ "【通联-网银支付】参数错误：{}", res.getReturnMessage());
				return super.payFailedCallback(model);
			}
			
			//此处应调Service
			//查询订单是否存在
			//获取网关支付配置，封装相应渠道的请求参数
			//保存返回结果或者回调请求
			
			//test
			TLNetBankPayRequest tlNetBankPayRequest = new TLNetBankPayRequest();
			tlNetBankPayRequest.setInputCharset("1");
			tlNetBankPayRequest.setPickupUrl(SysConstant.TL_NB_CALLBACK_PAGE_URL);
			tlNetBankPayRequest.setReceiveUrl(SysConstant.TL_NB_NOTICE_URL);
			tlNetBankPayRequest.setVersion("v1.0");
			tlNetBankPayRequest.setLanguage("1");
			tlNetBankPayRequest.setSignType("0");
			tlNetBankPayRequest.setMerchantId(SysConstant.NETBANK_MERCHANT);
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
			
		} catch (Exception e) {
			
			logger.error("【通联-网银支付】异常：", e);
			return super.payFailedCallback(model);
			
		}
        
    }
    

    /**
     * 第三方扫码支付
     * @author Jerry
     * @date 2017年11月27日 上午11:15:27
     */
    //@ResponseBody
    @RequestMapping(value = "/qrCodePay", method = {RequestMethod.GET, RequestMethod.POST})
    public String qrCodePay(HttpServletRequest request, Model model) {
		
		TitanQrCodePayResponse titanQrPayResponse = new TitanQrCodePayResponse();
    	
    	try {
			
			TitanPayDTO payDTO = WebUtils.switch2RequestDTO(TitanPayDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(payDTO);
			if (!res.isSuccess()){
				logger.error("【通联-扫码支付】参数错误：{}", res.getReturnMessage());
				titanQrPayResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				//return titanQrPayResponse;
			}
			
			TLQrCodePayRequest tlQrCodePayRequest = new TLQrCodePayRequest();
			tlQrCodePayRequest.setCusid(SysConstant.QRCODE_CUSTID);
			tlQrCodePayRequest.setVersion("11");
			tlQrCodePayRequest.setTrxamt(Integer.parseInt(payDTO.getOrderAmount().toString()));
			tlQrCodePayRequest.setReqsn(payDTO.getOrderNo());
			tlQrCodePayRequest.setPaytype("W01");
			tlQrCodePayRequest.setRandomstr(CommonUtil.getValidatecode(8));
			tlQrCodePayRequest.setBody("body");
			tlQrCodePayRequest.setNotify_url(SysConstant.TL_QRCODE_NOTICE_URL);
			tlQrCodePayRequest.setRequestType(RequestTypeEnum.PUBLIC_PAY.getKey());
			
			titanQrPayResponse = tlPaymentService.qrCodePay(tlQrCodePayRequest);
			
			titanQrPayResponse.setPayType(payDTO.getPayType().toString());
			model.addAttribute("qrCode", titanQrPayResponse);
			
			return "payment/qrCodeTest"; //test
			//return titanQrPayResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-扫码支付】异常：", e);
			titanQrPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			//return titanQrPayResponse;
			return "payment/qrCodeTest"; //test
		}
        
    }
	
	
	/**
    * 快捷支付-签约支付
    * @author Jerry
    * @date 2017年11月27日 上午11:30:27
    */
   @ResponseBody
   @RequestMapping(value = "/quickPay", method = {RequestMethod.GET, RequestMethod.POST})
   public TitanQuickPayResponse quickPay(HttpServletRequest request, Model model) {
   	
   	TitanQuickPayResponse titanQuickPayResponse = new TitanQuickPayResponse();
   	
   	try {
   		
			TitanPayDTO payDTO = WebUtils.switch2RequestDTO(TitanPayDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(payDTO);
			if (!res.isSuccess() || !StringUtil.isValidString(payDTO.getPayerAccountType())){
				logger.error("【融宝-签约支付】参数错误：{}，payerAccountType={}", res.getReturnMessage(), payDTO.getPayerAccountType());
				titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
				return titanQuickPayResponse;
			}
			
			RBQuickPayRequest rbQuickPayRequest = new RBQuickPayRequest();
			rbQuickPayRequest.setMerchant_id(SysConstant.RB_QUICKPAY_MERCHANT);
			rbQuickPayRequest.setCard_no(payDTO.getPayerAccount());
			rbQuickPayRequest.setOwner(payDTO.getPayerName());
			rbQuickPayRequest.setCert_type("01");
			rbQuickPayRequest.setCert_no(payDTO.getIdCode());
			rbQuickPayRequest.setPhone(payDTO.getPayerPhone());
			rbQuickPayRequest.setOrder_no(payDTO.getOrderNo());
			rbQuickPayRequest.setTranstime(payDTO.getOrderTime());
			rbQuickPayRequest.setCurrency("156");
			rbQuickPayRequest.setTotal_fee(Integer.parseInt(payDTO.getOrderAmount().toString()));
			rbQuickPayRequest.setTitle("支付");//需要pay-app传过来
			rbQuickPayRequest.setBody("泰坦金融");//需要pay-app传过来
			rbQuickPayRequest.setMember_id("4534535er4");//需要pay-app传过来
			rbQuickPayRequest.setTerminal_type(payDTO.getTerminalType());
			rbQuickPayRequest.setTerminal_info(payDTO.getTerminalInfo());
			rbQuickPayRequest.setMember_ip(payDTO.getTerminalIp());
			rbQuickPayRequest.setSeller_email(SysConstant.RB_SELLER_EMAIL);
			rbQuickPayRequest.setNotify_url(payDTO.getNotifyUrl());
			rbQuickPayRequest.setToken_id(CommonUtil.getUUID());
			rbQuickPayRequest.setVersion(SysConstant.RB_VERSION);
			rbQuickPayRequest.setSign_type(SysConstant.RB_SIGN_TYPE);
			
			if(SysConstant.CARD_TYPE_DESPOSIT.equals(payDTO.getPayerAccountType())){
				
				rbQuickPayRequest.setRequestType(RequestTypeEnum.QUICK_PAY_DEPOSIT.getKey());
				
	       	}else if(SysConstant.CARD_TYPE_CREDIT.equals(payDTO.getPayerAccountType())){
	       		
	       		if(!StringUtil.isValidString(payDTO.getSafetyCode()) || !StringUtil.isValidString(payDTO.getValidthru())){
	       			logger.error("【融宝-信用卡签约】参数错误：safetyCode={}，validthru={}", payDTO.getSafetyCode(), payDTO.getValidthru());
	   				titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.PRAM_ERROR);
	   				return titanQuickPayResponse;
	       		}
	       		rbQuickPayRequest.setCvv2(payDTO.getSafetyCode());
	       		rbQuickPayRequest.setValidthru(payDTO.getValidthru());
	       		rbQuickPayRequest.setRequestType(RequestTypeEnum.QUICK_PAY_CREDIT.getKey());
	       	}
			
			titanQuickPayResponse = rbQuickPayService.contractPay(rbQuickPayRequest);
			return titanQuickPayResponse;
			
		} catch (Exception e) {
			
			logger.error("【融宝-签约支付】异常：", e);
			titanQuickPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanQuickPayResponse;
			
		}
       
   }
    
    
    /**
     * 获取二维码
     * @author Jerry
     * @date 2017年12月20日 下午6:02:26
     */
	@RequestMapping("wxPicture")
	public void getWxPicture(String url,HttpServletResponse response){
		try{
			Wxutil.createRqCode(url, CommonConstant.RQ_WIDTH, CommonConstant.RQ_HEIGH, response.getOutputStream());
		}catch(Exception e){
			logger.error("微信生成图片错误：", e);
		}
	}

}
