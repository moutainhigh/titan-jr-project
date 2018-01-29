/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName PayConfirmStrategy.java
 * @author Jerry
 * @date 2018年1月5日 下午2:09:16  
 */
package com.titanjr.checkstand.strategy.quickPay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @date 2018年1月5日 下午2:09:16  
 */
@Service("quickPayConfirmStrategy")
public class QuickPayConfirmStrategy implements QuickPayStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		return "/quick/payConfirm.shtml";
	}

}
