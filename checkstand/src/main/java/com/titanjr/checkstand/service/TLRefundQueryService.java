/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLRefundQueryService.java
 * @author Jerry
 * @date 2017年12月5日 下午6:42:54  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.TLNetBankRefundQueryRequest;
import com.titanjr.checkstand.request.TLQrTradeQueryRequest;
import com.titanjr.checkstand.respnse.TitanRefundQueryResponse;

/**
 * 通联退款查询服务接口
 * @author Jerry
 * @date 2017年12月5日 下午6:42:54  
 */
public interface TLRefundQueryService {
	
	/**
	 * 网银支付退款查询
	 * @author Jerry
	 * @date 2017年12月5日 下午6:47:11
	 */
	TitanRefundQueryResponse netBankRefundQuery(TLNetBankRefundQueryRequest tlNetBankRefundQueryRequest);

}
