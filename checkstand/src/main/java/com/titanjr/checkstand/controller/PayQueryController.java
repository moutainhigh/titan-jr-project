package com.titanjr.checkstand.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.util.DateUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.dto.PayQueryDTO;
import com.titanjr.checkstand.request.TLNetBankPayQueryRequest;
import com.titanjr.checkstand.respnse.TitanPayQueryResponse;
import com.titanjr.checkstand.service.TLPayQueryService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.payQuery.PayQueryStrategy;
import com.titanjr.checkstand.util.WebUtils;

/**
 * Created by zhaoshan on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/query")
public class PayQueryController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 6704651168109119912L;
	private final static Logger logger = LoggerFactory.getLogger(PayQueryController.class);
	
	@Resource
	private TLPayQueryService tlPayQueryService;
	
	
	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
        
		//查询订单，获取支付方式
        
        //根据支付方式获取查询策略，调对应的接口
		PayTypeEnum payTypeEnum = PayTypeEnum.PERSON_EBANK;
		PayQueryStrategy payQueryStrategy =  StrategyFactory.getPayQueryStrategy(payTypeEnum);
        String redirectUrl = payQueryStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        
        return "redirect:" + redirectUrl;
        
    }
    
    
    /**
     * 【网银支付】单笔订单查询
     * @author Jerry
     * @date 2017年11月29日 上午10:39:01
     */
    @RequestMapping(value = "/netBankPayQuery", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanPayQueryResponse NetBankPayQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TLNetBankPayQueryRequest tlNetBankPayQueryRequest = new TLNetBankPayQueryRequest();
    	TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
    	
    	try {
    		
			PayQueryDTO payQueryDTO = WebUtils.switch2RequestDTO(PayQueryDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(payQueryDTO);
			if (!res.isSuccess()){
				logger.error("参数错误：{}", res.getReturnMessage());
				titanPayQueryResponse.putErrorResult(res.getReturnMessage());
				return titanPayQueryResponse;
			}
			
			tlNetBankPayQueryRequest.setMerchantId(payQueryDTO.getMerchantNo());
			tlNetBankPayQueryRequest.setOrderNo(payQueryDTO.getOrderNo());
			tlNetBankPayQueryRequest.setVersion("v1.5");
			tlNetBankPayQueryRequest.setSignType("1");
			tlNetBankPayQueryRequest.setOrderDatetime(payQueryDTO.getOrderTime());
			tlNetBankPayQueryRequest.setQueryDatetime(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
			tlNetBankPayQueryRequest.setRequestType(RequestTypeEnum.GATEWAY_PAY_QUERY_REFUND.getKey());
			
			titanPayQueryResponse = tlPayQueryService.netBankPayQuery(tlNetBankPayQueryRequest);
			return titanPayQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("订单查询发生异常：{}", e);
			titanPayQueryResponse.putErrorResult("系统异常");
			return titanPayQueryResponse;
			
		}
        
    }
	
}
