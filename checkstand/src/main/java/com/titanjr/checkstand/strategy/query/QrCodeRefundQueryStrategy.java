/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QrOrderRefundQueryStrategy.java
 * @author Jerry
 * @date 2017年12月20日 上午10:33:24  
 */
package com.titanjr.checkstand.strategy.query;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.titanjr.checkstand.dto.TitanRefundQueryDTO;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 通联扫码支付退款订单查询
 * @author Jerry
 * @date 2017年12月20日 上午10:33:24  
 */
@Service("qrOrderRefundQueryStrategy")
public class QrCodeRefundQueryStrategy implements QueryStrategy {

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	

	@Override
	public String redirectResult(HttpServletRequest request) {
    	
    	TitanRefundQueryDTO refundQueryDTO = WebUtils.switch2RequestDTO(TitanRefundQueryDTO.class, request);
    	
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validateRefundQuerySign(refundQueryDTO, config.getRsCheckKey())){
			return null;
		}
		
		return "/rfQuery/qrOrderRefundQuery.shtml";
		
	}

}
