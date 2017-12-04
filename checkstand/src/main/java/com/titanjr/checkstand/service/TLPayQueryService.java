/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName OrderService.java
 * @author Jerry
 * @date 2017年11月29日 下午5:32:58  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.TLNetBankPayQueryRequest;
import com.titanjr.checkstand.respnse.TitanPayQueryResponse;

/**
 * @author Jerry
 * @date 2017年11月29日 下午5:32:58  
 */
public interface TLPayQueryService {
	
	/**
	 * 网银支付结果查询
	 * @author Jerry
	 * @date 2017年12月1日 下午9:08:40
	 */
	public TitanPayQueryResponse netBankPayQuery(TLNetBankPayQueryRequest tlNetBankPayQueryRequest);

}
