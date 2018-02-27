/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName DepositPayStrategy.java
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
package com.titanjr.checkstand.strategy.pay;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.titanjr.checkstand.dto.TitanPayDTO;
import com.titanjr.checkstand.service.TitanCommonService;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

/**
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
@Service("quickPayStrategy")
public class QuickPayStrategy implements PayRequestStrategy {

	private static final Logger logger = LoggerFactory.getLogger(NetBankPayStrategy.class);

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	
	@Resource
	private TitanCommonService titanCommonService;

	@Override
	public String redirectResult(HttpServletRequest request) {

		TitanPayDTO payDTO = WebUtils.switch2RequestDTO(TitanPayDTO.class, request);
		ValidateResponse res = GenericValidate.validateNew(payDTO);
		if (!res.isSuccess()){
			logger.error("【快捷支付】参数错误：{}", res.getReturnMessage());
			return null;
		}
		if(!titanCommonService.isOrderCanPay(payDTO)){
			return null;
		}
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validatePaySign(payDTO, config.getRsCheckKey())){
			return null;
		}
		
		return "/pay/quickPay.shtml";
		
	}

}
