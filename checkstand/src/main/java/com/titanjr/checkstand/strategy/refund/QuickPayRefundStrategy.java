/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName DepositPayStrategy.java
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
package com.titanjr.checkstand.strategy.refund;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.titanjr.checkstand.util.WebUtils;

/**
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
@Service("quickPayRefundStrategy")
public class QuickPayRefundStrategy implements OrderRefundStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return WebUtils.getRequestBaseUrl(request) + "/refund/quickPayRefund.shtml";
		
	}

}
