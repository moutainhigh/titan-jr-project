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

import com.titanjr.checkstand.util.WebUtils;

/**
 * 账户交易-对账文件下载
 * @author Jerry
 * @date 2017年12月28日 下午6:52:22  
 */
@Service("agentDownloadStrategy")
public class AgentDownloadStrategy implements AgentTradeStrategy {

	@Override
	public String redirectResult(HttpServletRequest request) {
		
		return WebUtils.getRequestBaseUrl(request) + "/agent/agentDownload.shtml";
		
	}

}
