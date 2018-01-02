/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLAgentTradeService.java
 * @author Jerry
 * @date 2017年12月25日 下午6:21:55  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.TLAgentTradeRequest;
import com.titanjr.checkstand.respnse.TitanAgentPayResponse;
import com.titanjr.checkstand.respnse.TitanAgentQueryResponse;

/**
 * 通联账户交易服务（代付）
 * @author Jerry
 * @date 2017年12月25日 下午6:21:55  
 */
public interface TLAgentTradeService {
	
	/**
	 * 代付请求
	 * @author Jerry
	 * @date 2017年12月25日 下午6:29:51
	 */
	public TitanAgentPayResponse agentPay(TLAgentTradeRequest tlAgentTradeRequest);
	
	
	/**
	 * 交易查询
	 * @author Jerry
	 * @date 2017年12月27日 下午6:48:56
	 */
	public TitanAgentQueryResponse agentQuery(TLAgentTradeRequest tlAgentTradeRequest);
	
	
	/**
	 * 对账文件下载
	 * @author Jerry
	 * @date 2017年12月29日 上午9:54:47
	 */
	public void agentDownload(TLAgentTradeRequest tlAgentTradeRequest);

}
