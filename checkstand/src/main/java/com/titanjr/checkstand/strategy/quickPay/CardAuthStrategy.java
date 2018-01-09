/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName DepositPayStrategy.java
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
package com.titanjr.checkstand.strategy.quickPay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.titanjr.checkstand.util.WebUtils;

/**
 * 卡密鉴权
 * @author Jerry
 * @date 2018年1月8日 下午4:13:43
 */
@Service("cardAuthStrategy")
public class CardAuthStrategy implements QuickPayStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return WebUtils.getRequestBaseUrl(request) + "/quick/cardAuth.shtml";
		
	}

}
