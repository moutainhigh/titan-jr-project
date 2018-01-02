/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentPayStrategy.java
 * @author Jerry
 * @date 2017年12月25日 上午11:29:22  
 */
package com.titanjr.checkstand.strategy.agent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.titanjr.checkstand.util.WebUtils;

/**
 * 代付
 * @author Jerry
 * @date 2017年12月25日 上午11:29:22  
 */
@Service("agentPayStrategy")
public class AgentPayStrategy implements AgentTradeStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return WebUtils.getRequestBaseUrl(request) + "/agent/agentPay.shtml";
		
	}

}
