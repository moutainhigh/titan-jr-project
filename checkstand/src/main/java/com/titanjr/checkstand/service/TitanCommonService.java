/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanCommonService.java
 * @author Jerry
 * @date 2018年1月15日 下午3:00:54  
 */
package com.titanjr.checkstand.service;

import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.titanjr.checkstand.respnse.RBCardAuthResponse;
import com.titanjr.checkstand.respnse.TitanCardAuthResponse;
import com.titanjr.checkstand.respnse.TitanPayCallBackResponse;

/**
 * 泰坦金融服务
 * @author Jerry
 * @date 2018年1月15日 下午3:00:54  
 */
public interface TitanCommonService {
	
	/**
	 * 支付结果前台回调
	 * @author Jerry
	 * @date 2018年1月16日 上午11:15:48
	 */
	public TitanPayCallBackResponse payConfirmCallback(RechargeResultConfirmRequest confirmRequest);
	
	/**
	 * 支付结果后台通知
	 * @author Jerry
	 * @date 2018年1月16日 上午11:16:16
	 */
	public TitanPayCallBackResponse PayNotice(RechargeResultConfirmRequest confirmRequest);
	
	/**
	 * 卡密鉴权前台回调
	 * @author Jerry
	 * @date 2018年1月31日 下午2:49:41
	 */
	public TitanCardAuthResponse cardAuthPage(RBCardAuthResponse rbCardAuthResponse);

}
