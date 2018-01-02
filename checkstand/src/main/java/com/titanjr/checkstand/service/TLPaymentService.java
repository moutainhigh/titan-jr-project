/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLOrderRefundService.java
 * @author Jerry
 * @date 2017年12月4日 下午2:39:44  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.TLQrCodePayRequest;
import com.titanjr.checkstand.respnse.TitanQrCodePayResponse;

/**
 * 通联支付服务接口
 * @author Jerry
 * @date 2017年12月7日 下午6:30:13
 */
public interface TLPaymentService {
	
	/**
	 * 通联第三方扫码支付
	 * @author Jerry
	 * @date 2017年12月7日 下午6:31:40
	 */
	public TitanQrCodePayResponse qrCodePay(TLQrCodePayRequest tlQrCodePayRequest);

}
