/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName NetBnakOrderRefundStrategy.java
 * @author Jerry
 * @date 2017年12月4日 上午11:01:05  
 */
package com.titanjr.checkstand.strategy.refund;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.titanjr.checkstand.dto.TitanRefundDTO;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 网银支付订单退款
 * @author Jerry
 * @date 2017年12月4日 上午11:01:05  
 */
@Service("netBnakOrderRefundStrategy")
public class NetBnakOrderRefundStrategy implements OrderRefundStrategy {

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	

	@Override
	public String redirectResult(HttpServletRequest request) {
    	
		TitanRefundDTO refundDTO = WebUtils.switch2RequestDTO(TitanRefundDTO.class, request);
    	
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validateRefundSign(refundDTO, config.getRsCheckKey())){
			return null;
		}
		
		return "/refund/netBankOrderRefund.shtml";
		
	}

}
