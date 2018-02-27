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
import com.titanjr.checkstand.dto.TitanBindCardQueryDTO;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 用户绑卡列表查询
 * @author Jerry
 * @date 2018年1月8日 下午6:13:11
 */
@Service("bindCardListQueryStrategy")
public class BindCardListQueryStrategy implements QuickPayStrategy {

	private static final Logger logger = LoggerFactory.getLogger(BindCardListQueryStrategy.class);

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	

	@Override
	public String redirectResult(HttpServletRequest request) {

		TitanBindCardQueryDTO titanBindCardQueryDTO = WebUtils.switch2RequestDTO(TitanBindCardQueryDTO.class, request);
		ValidateResponse res = GenericValidate.validateNew(titanBindCardQueryDTO);
		if (!res.isSuccess()){
			logger.error("【查询绑卡列表】参数错误：{}", res.getReturnMessage());
			return null;
		}
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validateBindCardListSign(titanBindCardQueryDTO, config.getRsCheckKey())){
			return null;
		}
		
		return "/quick/bindCardList.shtml";
		
	}

}
