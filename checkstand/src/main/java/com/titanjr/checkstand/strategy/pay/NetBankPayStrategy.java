package com.titanjr.checkstand.strategy.pay;

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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 网银支付的策略，校验逻辑不同
 * Created by zhaoshan on 2017/11/20.
 */
@Service("netBankPayStrategy")
public class NetBankPayStrategy implements PayRequestStrategy{

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
			logger.error("【网银支付】参数错误：{}", res.getReturnMessage());
			return null;
		}
		if(!titanCommonService.isOrderCanPay(payDTO)){
			return null;
		}
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validatePaySign(payDTO, config.getRsCheckKey())){
			return null;
		}

        return "/pay/netBankPay.shtml";
    }
    
}
