/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentQueryStrategy.java
 * @author Jerry
 * @date 2017年12月25日 上午11:31:13  
 */
package com.titanjr.checkstand.strategy.agent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.TradeCodeEnum;

/**
 * 代收付交易结果查询
 * @author Jerry
 * @date 2017年12月25日 上午11:31:13  
 */
@Service("agentQueryStrategy")
public class AgentQueryStrategy implements AgentTradeStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		String tradeCode = request.getParameter("tradeCode");
		
		if(!StringUtil.isValidString(tradeCode)){
			return null;
		}
		if (TradeCodeEnum.TL_AGENT_QUERY.getCode().equals(tradeCode)){
			return "/agent/tlAgentQuery.shtml";
        }
		if (TradeCodeEnum.RB_AGENT_QUERY.getCode().equals(tradeCode)){
			return "/agent/rbAgentQuery.shtml";
        }
		
		return null;
		
	}

}
