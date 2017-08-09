/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanFinancialUpgradeService.java
 * @author Jerry
 * @date 2017年8月4日 下午3:39:07  
 */
package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.entity.TitanTransOrder;

/**
 * 泰坦金融二期服务接口
 * @author Jerry
 * @date 2017年8月4日 下午3:39:07  
 */
public interface TitanFinancialUpgradeService {
	
	/**
	 * 设置订单收付款信息并且校验余额支付权限（返回值包含是否可以用余额支付）
	 * @author Jerry
	 * @date 2017年8月4日 下午3:42:37
	 */
	TransOrderCreateResponse setBaseUserInfo(TitanOrderRequest titanOrderRequest, 
			TitanTransOrder titanTransOrder);

}
