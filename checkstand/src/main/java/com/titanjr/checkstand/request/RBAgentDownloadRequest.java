/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBAgentDownloadRequest.java
 * @author Jerry
 * @date 2018年2月7日 上午11:59:47  
 */
package com.titanjr.checkstand.request;

/**
 * @author Jerry
 * @date 2018年2月7日 上午11:59:47  
 */
public class RBAgentDownloadRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 2196952689434557472L;
	
	/**
	 * 交易时间   yyyyMMdd
	 */
	private String tradeDate;

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

}
