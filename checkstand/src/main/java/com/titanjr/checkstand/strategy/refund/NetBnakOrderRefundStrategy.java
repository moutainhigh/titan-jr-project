/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName NetBnakOrderRefundStrategy.java
 * @author Jerry
 * @date 2017年12月4日 上午11:01:05  
 */
package com.titanjr.checkstand.strategy.refund;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * 网银支付订单退款
 * @author Jerry
 * @date 2017年12月4日 上午11:01:05  
 */
@Service("netBnakOrderRefundStrategy")
public class NetBnakOrderRefundStrategy implements OrderRefundStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return "/refund/netBankOrderRefund.shtml";
		
	}

}
