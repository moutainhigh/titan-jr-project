/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName DepositPayStrategy.java
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
package com.titanjr.checkstand.strategy.query;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.titanjr.checkstand.util.WebUtils;

/**
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
@Service("quickPayQueryStrategy")
public class QuickPayQueryStrategy implements QueryStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return WebUtils.getRequestBaseUrl(request) + "/query/quickPayQuery.shtml";
		
	}

}
