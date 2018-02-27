package com.titanjr.checkstand.strategy.query;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.titanjr.checkstand.dto.TitanPayQueryDTO;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 网银支付订单查询
 * @author Jerry
 * @date 2017年12月1日 上午10:54:53
 */
@Service("netBankPayQueryStrategy")
public class NetBankPayQueryStrategy implements QueryStrategy{

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;


    @Override
    public String redirectResult(HttpServletRequest request) {
    	
    	TitanPayQueryDTO payQueryDTO = WebUtils.switch2RequestDTO(TitanPayQueryDTO.class, request);
    	
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validatePayQuerySign(payQueryDTO, config.getRsCheckKey())){
			return null;
		}

        return "/query/netBankPayQuery.shtml";
    }
}
