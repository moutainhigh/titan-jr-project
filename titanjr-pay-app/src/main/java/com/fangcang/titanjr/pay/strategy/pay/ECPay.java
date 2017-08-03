package com.fangcang.titanjr.pay.strategy.pay;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;

import com.fangcang.titanjr.common.enums.BusinessLog;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.request.AddPayLogRequest;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.util.SpringContextUtil;

/**
 * 网银支付
 * @author Jerry
 *
 */
public class ECPay implements PayStrategy {
	
	private static final Log log = LogFactory.getLog(ECPay.class);
	private BusinessLogService businessLogService;

	@Override
	public String pay(RechargeDataDTO rechargeDataDTO, String payOrderNo,Model model) {
		
		businessLogService = (BusinessLogService) SpringContextUtil.getBean("businessLogService");
		
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.CyberBankStep, OrderKindEnum.PayOrderNo, payOrderNo));
		model.addAttribute(CommonConstant.RESULT, CommonConstant.RETURN_SUCCESS);
    	model.addAttribute("rechargeDataDTO", rechargeDataDTO);
    	log.info("支付请求的参数如下:"+JsonConversionTool.toJson(rechargeDataDTO));
    	
		return CommonConstant.GATE_WAY_PAYGE;
		
	}

}
