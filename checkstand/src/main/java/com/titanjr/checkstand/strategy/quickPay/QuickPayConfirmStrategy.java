/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName PayConfirmStrategy.java
 * @author Jerry
 * @date 2018年1月5日 下午2:09:16  
 */
package com.titanjr.checkstand.strategy.quickPay;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.titanjr.checkstand.dto.TitanPayConfirmDTO;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

/**
 * @author Jerry
 * @date 2018年1月5日 下午2:09:16  
 */
@Service("quickPayConfirmStrategy")
public class QuickPayConfirmStrategy implements QuickPayStrategy {

	private static final Logger logger = LoggerFactory.getLogger(QuickPayConfirmStrategy.class);

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	

	@Override
	public String redirectResult(HttpServletRequest request) {

		TitanPayConfirmDTO titanPayConfirmDTO = WebUtils.switch2RequestDTO(TitanPayConfirmDTO.class, request);
		ValidateResponse res = GenericValidate.validateNew(titanPayConfirmDTO);
		if (!res.isSuccess()){
			logger.error("【确认支付】参数错误：{}", res.getReturnMessage());
			return null;
		}
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validatePayConfirmSign(titanPayConfirmDTO, config.getRsCheckKey())){
			return null;
		}
		
		return "/quick/payConfirm.shtml";
		
	}

}
