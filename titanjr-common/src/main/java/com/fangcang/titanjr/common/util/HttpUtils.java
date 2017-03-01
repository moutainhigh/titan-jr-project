package com.fangcang.titanjr.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class HttpUtils {
	private static final Log LOG = LogFactory.getLog(HttpUtils.class);
	
	public static HttpClient getHttpClientFactory()
	{
		return new DefaultHttpClient();
	}
	/**
	 * 获取默认的HTTP请求配置
	 * 
	 * @return
	 */
	public static RequestConfig getDefRequestConfig() {
		RequestConfig.Builder config = RequestConfig.custom();
		config.setConnectTimeout(3000);
		config.setConnectionRequestTimeout(3000);
		config.setSocketTimeout(3000);
		return config.build();
	}

	public static HttpPost buildPostForm(String url, Map<String, String> params)
			throws UnsupportedEncodingException {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		return httpost;
	}
	/**
	 * post请求
	 * @param url
	 * @param keyvalue  参数，格式：aaaa=111&bbb=222&ccc=333
	 * @return
	 * @throws IOException
	 */
	public static String postRequest(URL url, String keyvalue) throws IOException {
		HttpURLConnection conn = null;
		OutputStream out = null;
		InputStream in = null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.connect();
			
			// 写入POST数据
			out = conn.getOutputStream();
			byte[] params = keyvalue.getBytes();
			out.write(params, 0, params.length);
			out.flush();
			
			// 读取响应数据
			in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			StringBuilder buffer = new StringBuilder();
			char[] buf = new char[1000];
			int len = 0;
			while (len >= 0) {
				buffer.append(buf, 0, len);
				len = reader.read(buf);
			}
			String result = buffer.toString();
			LOG.info("http client request ,url:"+url.toString()+",param:"+keyvalue+",result:"+result);
			return result;
		} catch(IOException e) {
			LOG.error("http request fail， url:"+url.toString()+",param:"+keyvalue);
			throw e;
		} finally {
			close(in);
			close(out);
			if(conn != null){
				conn.disconnect();
			}
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param parameters 参数
	 * @return
	 * @throws IOException
	 */
	public static String postRequest(URL url, Map<String, String> parameters) throws IOException {
		return postRequest(url, generatorParamString(parameters));
	}
	/**
	 * @description 生成请求参数字符串
	 * @param parameters
	 * @return
	 */
	public static String generatorParamString(Map<String, String> parameters) {
        StringBuffer params = new StringBuffer();
        if(parameters != null) {
        	for(Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext(); ) {
        		String name = iter.next();
        		String value = parameters.get(name);
        		params.append(name + "=");
                try {
                    params.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                	throw new RuntimeException(e.getMessage(), e);
                } catch (Exception e) {
                	throw new RuntimeException(String.format("'%s'='%s'", name, value), e);
                }
                if(iter.hasNext())
                	params.append("&");
            }
        }
        return params.toString();
    }
	/**
	 * @description 关闭对象
	 * @param f
	 */
	public static void close(Closeable f) {
		if(f != null){
			try{
				f.close();
			}catch(IOException e){
				Logger.getAnonymousLogger().info(e.toString());
			}
		}
	}
}
