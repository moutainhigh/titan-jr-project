package com.titanjr.checkstand.controller;

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
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.dto.TitanRefundQueryDTO;
import com.titanjr.checkstand.request.TLNetBankRefundQueryRequest;
import com.titanjr.checkstand.respnse.TitanRefundQueryResponse;
import com.titanjr.checkstand.service.TLPayQueryService;
import com.titanjr.checkstand.service.TLRefundQueryService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.refundQuery.RefundQueryStrategy;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 退款查询
 * @author Jerry
 * @date 2017年12月5日 下午6:08:23
 */
@Controller
@RequestMapping(value = "/rfQuery")
public class RefundQueryController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 6704651168109119912L;
	private final static Logger logger = LoggerFactory.getLogger(RefundQueryController.class);
	
	@Resource
	private TLPayQueryService tlPayQueryService;
	
	@Resource
	private TLRefundQueryService tlRefundQueryService;
	
	
	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
        
		//查询订单，获取支付方式
        
        //根据支付方式获取查询策略，调对应的接口
		PayTypeEnum payTypeEnum = PayTypeEnum.PERSON_EBANK;
		RefundQueryStrategy refundQueryStrategy =  StrategyFactory.getRefundQueryStrategy(payTypeEnum);
        String redirectUrl = refundQueryStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        
        return "redirect:" + redirectUrl;
        
    }
    
    
    /**
     * 【网银支付】退款结果查询
     * @author Jerry
     * @date 2017年11月29日 上午10:39:01
     */
    @RequestMapping(value = "/netBankRefundQuery", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanRefundQueryResponse NetBankPayQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TLNetBankRefundQueryRequest tlNetBankRefundQueryRequest = new TLNetBankRefundQueryRequest();
    	TitanRefundQueryResponse titanRefundQueryResponse = new TitanRefundQueryResponse();
    	
    	try {
    		
			TitanRefundQueryDTO refundQueryDTO = WebUtils.switch2RequestDTO(TitanRefundQueryDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(refundQueryDTO);
			if (!res.isSuccess()){
				logger.error("参数错误：{}", res.getReturnMessage());
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanRefundQueryResponse;
			}
			
			tlNetBankRefundQueryRequest.setMerchantId(refundQueryDTO.getMerchantNo());
			tlNetBankRefundQueryRequest.setOrderNo(refundQueryDTO.getOrderNo());
			tlNetBankRefundQueryRequest.setMchtRefundOrderNo(refundQueryDTO.getRefundOrderno());
			tlNetBankRefundQueryRequest.setRefundAmount(refundQueryDTO.getRefundAmount());
			//tlNetBankRefundQueryRequest.setRefundDatetime("20171205145011");//退款请求会返回这个值
			tlNetBankRefundQueryRequest.setVersion("v2.4");
			tlNetBankRefundQueryRequest.setSignType("0");
			tlNetBankRefundQueryRequest.setRequestType(RequestTypeEnum.GATEWAY_REFUNDQUERY.getKey());
			
			titanRefundQueryResponse = tlRefundQueryService.netBankRefundQuery(tlNetBankRefundQueryRequest);
			return titanRefundQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("订单查询发生异常：{}", e);
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanRefundQueryResponse;
			
		}
        
    }
	
}
