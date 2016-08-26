package com.fangcang.titanjr.pay.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangcang.titanjr.common.enums.CashierDeskTypeEnum;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.dto.bean.GDPOrderDTO;
import com.fangcang.titanjr.dto.bean.PayTypeEnum;
import com.fangcang.titanjr.dto.request.AccountCheckRequest;
import com.fangcang.titanjr.dto.request.PaymentRequest;
import com.fangcang.titanjr.dto.request.RechargePageRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.AllowNoPwdPayResponse;
import com.fangcang.titanjr.dto.response.FinancialOrderResponse;
import com.fangcang.titanjr.dto.response.GDPOrderResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.pay.services.FinancialTradeService;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.util.StringUtil;
@Controller
@RequestMapping("/payment")
public class TitanPaymentController extends BaseController {

	private static final Log log = LogFactory.getLog(TitanPaymentController.class);
	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;
	
	@Resource
	private TitanPaymentService titanPaymentService;
	
	/**
	 * 需要充值的接口
	 * @param request
	 * @param paymentRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("packageRechargeData")
	public String packageRechargeData(HttpServletRequest request,TitanPaymentRequest titanPaymentRequest,Model model) throws Exception{
	
		log.info("网银支付请求参数:"+JsonConversionTool.toJson(titanPaymentRequest));
		model.addAttribute("result", "false");
		if(null == titanPaymentRequest || !StringUtil.isValidString(titanPaymentRequest.getTradeAmount()) 
				|| !StringUtil.isValidString(titanPaymentRequest.getPayAmount())){
			model.addAttribute("msg", "参数错误");
			return "checkstand-pay/genRechargePayment";
		}
		
        Map<String,Object> validResult = this.validPaymentData(titanPaymentRequest);
        if("true".equals(validResult.get("result"))){//合规性验证
        	model.addAttribute("msg", validResult.get("msg"));
			return "checkstand-pay/genRechargePayment";
        }
		
        TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService.createRsOrder(titanPaymentRequest);
        if(!transOrderCreateResponse.isResult() || !StringUtil.isValidString(transOrderCreateResponse.getOrderNo()) ){
        	log.error("call createTitanTransOrder fail msg["+ JsonConversionTool.toJson(transOrderCreateResponse)+"]");
        	model.addAttribute("msg", "创建单失败,请重新下单！");
        	return "checkstand-pay/genRechargePayment";
        }
        
        //设置支付方式
    	if(StringUtil.isValidString(titanPaymentRequest.getLinePayType())){
    		titanPaymentRequest.setPayType(PayTypeEnum.getPayTypeEnum(titanPaymentRequest.getLinePayType()));
    	}
    	titanPaymentRequest.setOrderid(transOrderCreateResponse.getOrderNo());
    	
    	RechargeResponse rechargeResponse = titanPaymentService.packageRechargeData(titanPaymentRequest);
    	if(!rechargeResponse.isResult()){
    		model.addAttribute("msg", rechargeResponse.getReturnMessage());
    		return "checkstand-pay/genRechargePayment";
    	}
		
    	model.addAttribute("result", "success");
    	model.addAttribute("rechargeDataDTO", rechargeResponse.getRechargeDataDTO());
    	log.info("支付请求的参数如下:"+JsonConversionTool.toJson(rechargeResponse.getRechargeDataDTO()));
    	//保存常用的支付方式
    	titanPaymentService.saveCommonPayMethod(titanPaymentRequest);
		return "checkstand-pay/genRechargePayment";
	}
	
	
    private Map<String,Object> validPaymentData(TitanPaymentRequest titanPaymentRequest){
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	//判断付款方是否存在
		boolean isExist = titanPaymentService.accountIsExist(titanPaymentRequest.getRecieveOrgName(),
				titanPaymentRequest.getRecieveTitanCode());
		
		if(!isExist){
			resultMap.put("msg", "收款方不存在");
			resultMap.put("result", false);
			return resultMap;
		}
		
		//TODO  支付请求发生时需要确定金额
		
		//交易金额
		BigDecimal tradeAmount = new BigDecimal(titanPaymentRequest.getTradeAmount());
		//在线支付金额
		BigDecimal  payAmount = new BigDecimal(titanPaymentRequest.getPayAmount());
		
		if(tradeAmount.subtract(payAmount).compareTo(BigDecimal.ZERO)==1){//有转账金额,需要输入密码
			boolean isAllowNoPwdPay = titanPaymentService.isAllowNoPwdPay(titanPaymentRequest.getUserid(), titanPaymentRequest.getTradeAmount());
	        if(!isAllowNoPwdPay){//不允许免密支付，需要输入密码
	        	boolean isTrue = titanPaymentService.checkPwd(titanPaymentRequest.getPayPassword(), titanPaymentRequest.getFcUserid());
	            if(!isTrue){
	    			resultMap.put("msg", "付款密码不正确");
	    			resultMap.put("result", false);
	    			return resultMap;
	            }
	        }
		}
		
		if(StringUtil.isValidString(titanPaymentRequest.getUserrelateid())){//收款方不为空，则判断是否自己给自己付款
			if(titanPaymentRequest.getUserrelateid().equals(titanPaymentRequest.getUserid())){
    			resultMap.put("msg", "收款账户不能和付款账户相同");
    			resultMap.put("result", false);
    			return resultMap;
			}
		}
		resultMap.put("result", true);
		return resultMap;
    }	
    
    
    
    
}
