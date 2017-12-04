package com.titanjr.checkstand.controller;

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
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.dto.TitanRefundRequestDTO;
import com.titanjr.checkstand.request.TLNetBankOrderRefundRequest;
import com.titanjr.checkstand.respnse.TitanOrderRefundResponse;
import com.titanjr.checkstand.service.TLOrderRefundService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.refund.OrderRefundStrategy;
import com.titanjr.checkstand.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/17.
 */
@Controller
@RequestMapping(value = "/refund")
public class RefundController extends BaseController {
    /** 
	 * 
	 */
	private static final long serialVersionUID = -7373722251042176114L;
	private final static Logger logger = LoggerFactory.getLogger(RefundController.class);
	
	@Resource
	private TLOrderRefundService tlOrderRefundService;
	

	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {

		//查询订单，获取支付方式
        
        //根据支付方式获取查询策略，调对应的接口
		PayTypeEnum payTypeEnum = PayTypeEnum.PERSON_EBANK;
		OrderRefundStrategy refundStrategy =  StrategyFactory.getRefundStrategy(payTypeEnum);
        String redirectUrl = refundStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        
        return "redirect:" + redirectUrl;
		
    }
	
	
	@RequestMapping(value = "/netBankOrderRefund", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanOrderRefundResponse NetBankPayQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
    	TLNetBankOrderRefundRequest tlNetBankOrderRefundRequest = new TLNetBankOrderRefundRequest();
    	
    	try {
    		
    		TitanRefundRequestDTO refundRequestDTO = WebUtils.switch2RequestDTO(TitanRefundRequestDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(refundRequestDTO);
			if (!res.isSuccess()){
				logger.error("参数错误：{}", res.getReturnMessage());
				titanOrderRefundResponse.putErrorResult(res.getReturnMessage());
				return titanOrderRefundResponse;
			}
			
			tlNetBankOrderRefundRequest.setMerchantId(refundRequestDTO.getMerchantNo());
			tlNetBankOrderRefundRequest.setOrderNo(refundRequestDTO.getOrderNo());
			tlNetBankOrderRefundRequest.setRefundAmount(Integer.parseInt(refundRequestDTO.getRefundAmount()));//校验
			tlNetBankOrderRefundRequest.setMchtRefundOrderNo(refundRequestDTO.getRefundOrderno());
			tlNetBankOrderRefundRequest.setVersion("v2.3");
			tlNetBankOrderRefundRequest.setSignType("1");
			tlNetBankOrderRefundRequest.setOrderDatetime(refundRequestDTO.getOrderTime());
			tlNetBankOrderRefundRequest.setRequestType(RequestTypeEnum.GATEWAY_PAY_QUERY_REFUND.getKey());
			
			titanOrderRefundResponse = tlOrderRefundService.netBankOrderRefund(tlNetBankOrderRefundRequest);
			return titanOrderRefundResponse;
			
		} catch (Exception e) {
			
			logger.error("订单查询发生异常：{}", e);
			titanOrderRefundResponse.putErrorResult("系统异常");
			return titanOrderRefundResponse;
			
		}
        
    }

}
