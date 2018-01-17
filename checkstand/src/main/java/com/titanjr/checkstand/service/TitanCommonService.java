/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanCommonService.java
 * @author Jerry
 * @date 2018年1月15日 下午3:00:54  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.TLNetBankPayCallbackRequest;
import com.titanjr.checkstand.respnse.BaseResponse;
import com.titanjr.checkstand.respnse.TLPayCallBackResponse;

/**
 * 泰坦金融服务
 * @author Jerry
 * @date 2018年1月15日 下午3:00:54  
 */
public interface TitanCommonService {
	
	/**
	 * 网银支付前台回调
	 * @author Jerry
	 * @date 2018年1月16日 上午11:15:48
	 */
	public TLPayCallBackResponse payConfirmCallback(TLNetBankPayCallbackRequest 
			tlNetBankPayCallbackRequest);
	
	/**
	 * 网银支付后台通知
	 * @author Jerry
	 * @return 
	 * @date 2018年1月16日 上午11:16:16
	 */
	public TLPayCallBackResponse PayNotice(TLNetBankPayCallbackRequest tlNetBankPayCallbackRequest);

}
