/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AccountDownloadService.java
 * @author Jerry
 * @date 2018年3月15日 下午5:14:56  
 */
package com.titanjr.checkstand.service;

import com.titanjr.checkstand.request.RBAgentDownloadRequest;
import com.titanjr.checkstand.request.TLAgentTradeRequest;
import com.titanjr.checkstand.request.TLGatewayPayDownloadRequest;
import com.titanjr.checkstand.request.TLQrCodeDownloadRequest;
import com.titanjr.checkstand.respnse.RSResponse;

/**
 * @author Jerry
 * @date 2018年3月15日 下午5:14:56  
 */
public interface AccountDownloadService {
	
	/**
	 * 通联网关支付对账文件下载
	 * @author Jerry
	 * @date 2018年3月14日 上午11:24:37
	 */
	public RSResponse tlGatewayPayDownload(TLGatewayPayDownloadRequest tlGatewayPayDownloadRequest);
	
	/**
	 * 通联扫码/公众号支付对账文件下载
	 * @author Jerry
	 * @date 2018年3月16日 上午11:29:15
	 * @param tlQrCodeDownloadRequest
	 * @return
	 */
	public RSResponse tlQrCodePayDownload(TLQrCodeDownloadRequest tlQrCodeDownloadRequest);
	
	
	/**
	 * 通联账户交易对账文件下载
	 * @author Jerry
	 * @return 
	 * @date 2017年12月29日 上午9:54:47
	 */
	public RSResponse tlAgentDownload(TLAgentTradeRequest tlAgentTradeRequest);
	
	/**
	 * 融宝对账文件下载
	 * @author Jerry
	 * @date 2018年2月6日 下午4:46:03
	 * @param tradeDate 交易时间   yyyy-MM-dd
	 */
	public RSResponse rbAccountDownload(RBAgentDownloadRequest rbAgentDownloadRequest);

}
