/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName QrOrderRefundStrategy.java
 * @author Jerry
 * @date 2017年12月20日 上午9:46:36  
 */
package com.titanjr.checkstand.strategy.refund;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @date 2017年12月20日 上午9:46:36  
 */
@Service("qrOrderRefundStrategy")
public class QrOrderRefundStrategy implements OrderRefundStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return "/refund/qrOrderRefund.shtml";
		
	}

}
