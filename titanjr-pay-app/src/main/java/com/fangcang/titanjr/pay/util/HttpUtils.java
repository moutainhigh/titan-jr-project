package com.fangcang.titanjr.pay.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author WENGXITAO
 * @Description 接口调用工具类
 */
@SuppressWarnings("unused")
public class HttpUtils {
	private static final Log logger = LogFactory
			.getLog(HttpUtils.class);
	private static int defaultTimeOut = 4;
	public static final String GET = "GET";
	public static final String POST = "POST";

	public static String doPost(String requestUrl, Map<String, String> params) throws Exception {
		return doRequest(requestUrl, POST, null, 0, params, false);
	}
	public static String doPost(String requestUrl, String requestBody) throws Exception {
		return doRequest(requestUrl, POST, requestBody, 0, null, false);
	}

	public static String doGet(String requestUrl, Map<String, String> params, boolean isEncode)
			throws Exception {
		return doRequest(requestUrl, GET, null, 0, params, isEncode);// 无超时
	}

	/**
	 * HTTP请求的核心方法
	 *
	 * @param method
	 * @param requestUrl
	 * @param requestBody
	 * @param timeout
	 * @param params
	 * @return
	 * @throws Exception
	 * @date 2014年12月12日
	 */
	private static String doRequest(String requestUrl, String method, String requestBody,
			int timeout, Map<String, String> params, boolean isEncode) throws Exception {
		InputStream in = null;
		try {
			String queryStr = convertToQueryStr(params, isEncode);
			System.out.println("requestUrl + queryStr:" + requestUrl + queryStr);
			URLConnection conn=null;
			if(POST.equalsIgnoreCase(method)){
				conn = new URL(requestUrl).openConnection();
				requestBody=queryStr;
			}else if(GET.equalsIgnoreCase(method)){
				conn = new URL(requestUrl + "?" + queryStr).openConnection();
			}
			// 微信5秒超时设定，因此调用第三方接口建议4秒超时
			conn.setReadTimeout(timeout);// 0秒为不限超时时间
			conn.setDefaultUseCaches(false);
			conn.setUseCaches(false);
			// conn.setRequestProperty("content-type","text/plain;charset=utf-8");
			// conn.setRequestProperty("charset", "gbk");
			if (POST.equalsIgnoreCase(method)) {// POST特有逻辑
				conn.setDoOutput(true);// POST标志
				OutputStream out = conn.getOutputStream();
				out.write(requestBody.getBytes("UTF-8"));// 写入内容到流缓存
				close(out);// 正式发起请求
			}
			in = conn.getInputStream();
			return readStreamAsStr(in);
		} finally {
			close(in);
		}
	}


	/**
	 * 把Map转为URL的查询字符串
	 *
	 * @param params
	 * @return
	 * @date 2014年12月8日
	 */
	public static String convertToQueryStr(Map<String, String> params, boolean isEncode) {
		// 拼凑参数
		if (params != null) {
			StringBuffer queryStr = new StringBuffer("");
			boolean isFirst = true;
			for (Map.Entry<String, String> en : params.entrySet()) {
				String value = en.getValue();
				if (value != null) {// 空值不转换
				// URL编码
					String kv = en.getKey() + "=" + (isEncode ? URLEncoder.encode(value) : value);
					if (isFirst) {
						// queryStr.append("?");
						isFirst = false;
					} else {
						queryStr.append("&");
					}
					queryStr.append(kv);
				}
			}
			// logger.debug("converToQueryStr:" + queryStr);
			return queryStr.toString();
		} else {
			return "";
		}
	}

	/**
	 * 把流读取为字符串
	 *
	 * @param in
	 * @return
	 * @throws java.io.IOException
	 * @date 2014年12月8日
	 */
	private static String readStreamAsStr(InputStream in) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		StringBuffer content = new StringBuffer("");
		boolean isFirst = true;
		for (String str; (str = reader.readLine()) != null;) {
			if (isFirst) {// 第一行不需要换行
				content.append(str);
				isFirst = false;
			} else {
				content.append(System.getProperty("line.separator") + str);
			}
		}
		return content.toString();
	}

	/**
	 * 关闭流
	 *
	 * @param stream
	 * @throws java.io.IOException
	 * @date 2014年12月9日
	 */
	private static void close(Closeable stream) throws IOException {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("流关闭失败！", new Exception());
				throw e;
			}
		} else {
			logger.error("流为null");
		}
	}
}