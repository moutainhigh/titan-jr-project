/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName MyX509TrustManager.java
 * @author Jerry
 * @date 2018年3月14日 下午3:51:40  
 */
package com.titanjr.checkstand.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * @author Jerry
 * @date 2018年3月14日 下午3:51:40  
 */
public class MyX509TrustManager implements X509TrustManager {

	/**
	 * 该方法体为空时信任所有客户端证书
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {

	}

	/**
	 * 该方法体为空时信任所有服务器证书
	 */
	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {

	}

	/**
	 * 返回信任的证书
	 */
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
