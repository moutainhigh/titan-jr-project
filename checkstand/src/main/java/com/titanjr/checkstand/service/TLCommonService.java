/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLCommonService.java
 * @author Jerry
 * @date 2017年12月20日 下午2:16:22  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.TLQrTradeQueryRequest;
import com.titanjr.checkstand.respnse.TLQrTradeQueryResponse;

/**
 * 通联公共服务接口
 * @author Jerry
 * @date 2017年12月20日 下午2:16:22  
 */
public interface TLCommonService {
	
	/**
	 * 第三方扫码交易查询，包括支付、撤销、退款结果的查询统一接口
	 * @author Jerry
	 * @date 2017年12月20日 上午11:29:53
	 */
	public TLQrTradeQueryResponse qrCodeTradeQuery(TLQrTradeQueryRequest tlQrTradeQueryRequest);

}
