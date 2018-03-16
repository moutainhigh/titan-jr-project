/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AccountDownloadService.java
 * @author Jerry
 * @date 2018年3月15日 下午5:14:56  
 */
package com.titanjr.checkstand.service;

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
	public RSResponse gatewayPayDownload(TLGatewayPayDownloadRequest tlGatewayPayDownloadRequest);
	
	/**
	 * 通联扫码/公众号支付对账文件下载
	 * @author Jerry
	 * @date 2018年3月16日 上午11:29:15
	 * @param tlQrCodeDownloadRequest
	 * @return
	 */
	public RSResponse qrCodePayDownload(TLQrCodeDownloadRequest tlQrCodeDownloadRequest);

}
