/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentTradeStrategy.java
 * @author Jerry
 * @date 2017年12月25日 上午11:26:50  
 */
package com.titanjr.checkstand.strategy.download;

import javax.servlet.http.HttpServletRequest;

/**
 * 下载策略
 * @author Jerry
 * @date 2018年3月15日 下午4:58:50
 */
public interface DownloadStrategy {
	
	String redirectResult(HttpServletRequest request);

}
