package com.fangcang.titanjr.common.factory;

/**
 * 维护所有请求连接地址前缀的常量类
 * Created by zhaoshan on 2016/4/20.
 */
public class ProxyFactoryConstants {

    public static String merchantServerUrl;
    
    public static String messageServiceUrl;
    
    public static String financeSearchRemoteUrl;
    
    public static String orderServiceUrl;
    
    public static String securityUrl;

    public String getMerchantServerUrl() {
        return merchantServerUrl;
    }

    public void setMerchantServerUrl(String merchantServerUrl) {
        ProxyFactoryConstants.merchantServerUrl = merchantServerUrl;
    }

	public void setMessageServiceUrl(String messageServiceUrl) {
		ProxyFactoryConstants.messageServiceUrl = messageServiceUrl;
	}

	public void setFinanceSearchRemoteUrl(String financeSearchRemoteUrl) {
		ProxyFactoryConstants.financeSearchRemoteUrl = financeSearchRemoteUrl;
	}

    public void setSecurityUrl(String securityUrl) {
        ProxyFactoryConstants.securityUrl = securityUrl;
    }

	public static void setOrderServiceUrl(String orderServiceUrl) {
		ProxyFactoryConstants.orderServiceUrl = orderServiceUrl;
	}
	
	
}
