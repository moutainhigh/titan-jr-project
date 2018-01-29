/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName DepositPayStrategy.java
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
package com.titanjr.checkstand.strategy.pay;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @date 2018年1月4日 上午9:32:12  
 */
@Service("quickPayStrategy")
public class QuickPayStrategy implements PayRequestStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return "/pay/quickPay.shtml";
		
	}

}
