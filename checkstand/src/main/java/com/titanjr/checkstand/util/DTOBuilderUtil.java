/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName ParamBuilderUtil.java
 * @author Jerry
 * @date 2017年11月25日 上午11:03:12  
 */
package com.titanjr.checkstand.util;

import com.titanjr.checkstand.request.TitanPayCallbackRequest;

/**
 * 对象构建工具类
 * @author Jerry
 * @date 2017年11月25日 上午11:03:12  
 */
public class DTOBuilderUtil {
	
	/**
	 * 参数错误时，获取前台回调的请求参数
	 * @author Jerry
	 */
	public static TitanPayCallbackRequest getPayFailedCallbackRequest(){
		
		TitanPayCallbackRequest payCallbackRequest = new TitanPayCallbackRequest();
		payCallbackRequest.setOrderNo("2017081714095100001");
		payCallbackRequest.setPayStatus("4");
		payCallbackRequest.setPayAmount("100");
		payCallbackRequest.setPayMsg("支付失败");
		payCallbackRequest.setOrderPayTime("yyyyMMddHHmmss");
		payCallbackRequest.setTitanjrGateWayUrl("http://192.168.0.77:8084/titanjr-pay-app/payment/payConfirmPage.action");
		
		return payCallbackRequest;
		
	}
 
}
