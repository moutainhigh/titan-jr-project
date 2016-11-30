package com.fangcang.titanjr.common.util.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpClient {
	
    private static final Log log = LogFactory.getLog(HttpClient.class);

	 public static HttpResponse httpRequest(List<NameValuePair> params, String url , HttpPost httpPost) {
	        RequestConfig defaultRequestConfig = RequestConfig.custom()
	                .setSocketTimeout(10000)
	                .setConnectTimeout(10000)
	                .setConnectionRequestTimeout(5000)
	                .build();
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultRequestConfig(defaultRequestConfig)
	                .build();
	        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
//	        HttpPost httpPost = new HttpPost(url);
//	        httpPost.setConfig(requestConfig);
	        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	        httpPost.setConfig(requestConfig);
	        HttpResponse response = null;
	        try {
	            if (CollectionUtils.isNotEmpty(params)) {
	                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	            }
	            response = httpclient.execute(httpPost);
	        } catch (UnsupportedEncodingException e) {
	            log.error("UnsupportedEncodingException:", e);
	        } catch (ClientProtocolException e) {
	            log.error("ClientProtocolException:", e);
	        } catch (IOException e) {
	            log.error("IOException", e);
	        }
	        return response;
	    }
}
