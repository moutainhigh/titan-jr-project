/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentDownloadStrategy.java
 * @author Jerry
 * @date 2017年12月28日 下午6:52:22  
 */
package com.titanjr.checkstand.strategy.agent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.AgentTradeCodeEnum;

/**
 * 账户交易-对账文件下载
 * @author Jerry
 * @date 2017年12月28日 下午6:52:22  
 */
@Service("agentDownloadStrategy")
public class AgentDownloadStrategy implements AgentTradeStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		String tradeCode = request.getParameter("tradeCode");
		
		if(!StringUtil.isValidString(tradeCode)){
			return null;
		}
		if (AgentTradeCodeEnum.TL_AGENT_DOWNLOAD.getCode().equals(tradeCode)){
			return "/agent/tlAgentDownload.shtml";
        }
		if (AgentTradeCodeEnum.RB_AGENT_DOWNLOAD.getCode().equals(tradeCode)){
			return "/agent/rbAgentDownload.shtml";
        }
		
		return null;
	}

}
