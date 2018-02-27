/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName DepositPayStrategy.java
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
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
import com.titanjr.checkstand.dto.TitanReSendMsgDTO;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

/**
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
@Service("reSendMsgStrategy")
public class ReSendMsgStrategy implements QuickPayStrategy {

	private static final Logger logger = LoggerFactory.getLogger(ReSendMsgStrategy.class);

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	

	@Override
	public String redirectResult(HttpServletRequest request) {

		TitanReSendMsgDTO titanReSendMsgDTO = WebUtils.switch2RequestDTO(TitanReSendMsgDTO.class, request);
		ValidateResponse res = GenericValidate.validateNew(titanReSendMsgDTO);
		if (!res.isSuccess()){
			logger.error("【重发验证码】参数错误：{}", res.getReturnMessage());
			return null;
		}
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validateReSendMsgSign(titanReSendMsgDTO, config.getRsCheckKey())){
			return null;
		}
		
		return "/quick/reSendMsg.shtml";
		
	}

}
