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
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.TradeCodeEnum;

/**
 * 代付
 * @author Jerry
 * @date 2017年12月25日 上午11:29:22  
 */
@Service("agentPayStrategy")
public class AgentPayStrategy implements AgentTradeStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		String tradeCode = request.getParameter("tradeCode");
		
		if(!StringUtil.isValidString(tradeCode)){
			return null;
		}
		if (TradeCodeEnum.TL_AGENT_PAY.getCode().equals(tradeCode)){
			return "/agent/tlAgentPay.shtml";
        }
		if (TradeCodeEnum.RB_AGENT_PAY.getCode().equals(tradeCode)){
			return "/agent/rbAgentPay.shtml";
        }
		
		return null;
	}

}
