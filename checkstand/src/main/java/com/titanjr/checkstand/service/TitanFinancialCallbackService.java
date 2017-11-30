/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanFinancialCallbackService.java
 * @author Jerry
 * @date 2017年11月25日 上午9:44:12  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.titanjr.TitanPayCallbackRequest;

/**
 * 泰坦金融回调
 * @author Jerry
 * @date 2017年11月25日 上午9:44:12  
 */
public interface TitanFinancialCallbackService {
	
	/**
	 * 确认页面回调
	 * @author Jerry
	 * @date 2017年11月25日 上午9:49:52
	 * @param payCallbackRequest
	 */
	public void PayConfirmPageCallback(TitanPayCallbackRequest payCallbackRequest);
	
	/**
	 * 后台通知回调
	 * @author Jerry
	 * @date 2017年11月25日 上午9:50:07
	 * @param payCallbackRequest
	 */
	public void PayNotifyCallback(TitanPayCallbackRequest payCallbackRequest);

}
