/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLAgentTradeService.java
 * @author Jerry
 * @date 2017年12月25日 下午6:21:55  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.RBAgentDownloadRequest;
import com.titanjr.checkstand.request.RBAgentPayQueryRequest;
import com.titanjr.checkstand.request.RBAgentPayRequest;
import com.titanjr.checkstand.respnse.RSResponse;
import com.titanjr.checkstand.respnse.TitanAgentPayResponse;
import com.titanjr.checkstand.respnse.TitanAgentQueryResponse;

/**
 * 融宝代付服务
 * @author Jerry
 * @date 2018年2月5日 下午6:15:26
 */
public interface RBAgentTradeService {
	
	/**
	 * 融宝代付请求
	 * @author Jerry
	 * @date 2018年2月5日 下午4:34:12
	 */
	public TitanAgentPayResponse agentPay(RBAgentPayRequest rbAgentPayRequest);
	
	/**
	 * 代付查询
	 * @author Jerry
	 * @date 2018年2月6日 下午4:46:37
	 */
	public TitanAgentQueryResponse agentQuery(RBAgentPayQueryRequest rbAgentPayQueryRequest);
	
	/**
	 * 对账文件下载，对账内容只包括代付支付成功的交易
	 * @author Jerry
	 * @date 2018年2月6日 下午4:46:03
	 * @param tradeDate 交易时间   yyyy-MM-dd
	 */
	public RSResponse agentDownload(RBAgentDownloadRequest rbAgentDownloadRequest);

}
