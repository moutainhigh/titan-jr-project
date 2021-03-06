package com.fangcang.titanjr.web.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.*;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.*;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.enums.OperTypeEnum;
import com.fangcang.titanjr.enums.OrderTypeEnum;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.service.*;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;
import net.sf.json.JSONSerializer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

