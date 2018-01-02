/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentTradeStrategy.java
 * @author Jerry
 * @date 2017年12月25日 上午11:26:50  
 */
package com.titanjr.checkstand.strategy.agent;

import javax.servlet.http.HttpServletRequest;

/**
 * 代收付交易策略
 * @author Jerry
 * @date 2017年12月25日 上午11:26:50  
 */
public interface AgentTradeStrategy {
	
	String redirectResult(HttpServletRequest request);

}
