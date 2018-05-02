package com.fangcang.titanjr.web.controller;

import com.fangcang.titanjr.dto.request.JudgeAllowNoPwdPayRequest;
import com.fangcang.titanjr.dto.response.AllowNoPwdPayResponse;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 简化controller命名和前缀
 *
 */
@Controller
@RequestMapping("/trade")
public class FinancialTradeController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(FinancialTradeController.class);
	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;

	/**
	 * 确认是否免密支付
	 * @param userid
	 * @param totalAmount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("allownopwdpay")
	public Map<String,String> validateIsAllowAoAwdpay(String userid,String totalAmount){
		Map<String,String> resultMap = new HashMap<String, String>();
		if(!StringUtil.isValidString(userid)){
			userid = this.getUserId();
		}
		if(StringUtil.isValidString(userid)){
			AllowNoPwdPayResponse allowNoPwdPayResponse = isAllowNoPwdPay(userid,totalAmount);
            if(allowNoPwdPayResponse.isAllowNoPwdPay()){
            	resultMap.put(WebConstant.RESULT, WebConstant.SUCCESS);
            	return resultMap;
            }
		}
		resultMap.put(WebConstant.RESULT, "failure");
		return resultMap;
	}
	
	private AllowNoPwdPayResponse isAllowNoPwdPay(String userid,String totalAmount){
		JudgeAllowNoPwdPayRequest judgeAllowNoPwdPayRequest = new JudgeAllowNoPwdPayRequest();
		judgeAllowNoPwdPayRequest.setMoney(totalAmount);
		judgeAllowNoPwdPayRequest.setUserid(userid);
		AllowNoPwdPayResponse allowNoPwdPayResponse = titanFinancialTradeService.isAllowNoPwdPay(judgeAllowNoPwdPayRequest);
	    return allowNoPwdPayResponse;
	}
	
}

