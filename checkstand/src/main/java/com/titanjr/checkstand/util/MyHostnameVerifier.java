/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName MyHostnameVerifier.java
 * @author Jerry
 * @date 2018年3月14日 下午3:54:40  
 */
package com.titanjr.checkstand.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author Jerry
 * @date 2018年3月14日 下午3:54:40  
 */
public class MyHostnameVerifier implements HostnameVerifier {

	/**
	 * 返回 true 时为通过认证 当方法体为空时，信任所有的主机名
	 */
	@Override
	public boolean verify(String arg0, SSLSession arg1) {
		
		return true;
	}

}
