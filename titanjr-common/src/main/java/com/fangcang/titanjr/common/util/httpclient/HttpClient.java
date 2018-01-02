package com.fangcang.titanjr.common.util.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.util.StringUtil;

public class HttpClient {

	private static final Log log = LogFactory.getLog(HttpClient.class);

	/**
	 * 获取response header中Content-Disposition中的filename值
	 * 
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getFileName(HttpResponse response) {
		Header contentHeader = response.getFirstHeader("Content-Disposition");
		String filename = null;
		if (contentHeader != null) {
			HeaderElement[] values = contentHeader.getElements();
			if (values.length == 1) {
				NameValuePair param = values[0].getParameterByName("filename");
				if (param != null) {
					try {
						filename = new String(param.getValue().getBytes(
								"ISO8859-1"), "GBK");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return filename;
	}

	private static void closeRes(HttpGet httpGet, InputStream in,
			OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (httpGet != null) {
			httpGet.releaseConnection();
		}
	}

	/**
	 * 下载文本保存到本地，并返回文件名称
	 * 
	 * @Title: dowloadFileToLoacl
	 * @Description: TODO
	 * @param remoteUrl
	 * @param param
	 * @param loalDir
	 * @return
	 * @return: String
	 * @throws IOException
	 * @throws
	 */
	public static String dowloadResToLoacl(String remoteUrl,
			Map<String, String> param, String loalDir , String loaclFileName) throws Exception {

		File localFileDir = new File(loalDir);
		if (!localFileDir.isDirectory()) {
			throw new Exception("is not exists dir");
		}
		StringBuilder builder = new StringBuilder();
		builder.append(remoteUrl);
		if (param != null) {

			Iterator<Entry<String, String>> iterator = param.entrySet()
					.iterator();

			Entry<String, String> entry = null;

			while (iterator.hasNext()) {
				entry = iterator.next();
				if (builder.toString().indexOf("?") == -1) {
					builder.append("?").append(entry.getKey()).append("=")
							.append(entry.getValue());
				} else {
					builder.append("&").append(entry.getKey()).append("=")
							.append(entry.getValue());
				}
			}
		}

		log.info("download http request uri = " + builder.toString());

		HttpGet httpGet = new HttpGet(builder.toString());

		InputStream in = null;
		FileOutputStream out = null;
		try {

			HttpResponse resp = HttpClient.httpRequest(httpGet);

			if (null != resp && resp.getStatusLine().getStatusCode() == 200) {

				in = resp.getEntity().getContent();
				byte by[] = new byte[2048];
				String fileName = loaclFileName;

				log.info("download fileName  = " + fileName);

				if (!StringUtil.isValidString(fileName)) {
					throw new Exception("remote file name is not exists");
				}
				out = new FileOutputStream(localFileDir.getAbsolutePath() + "/"
						+ fileName);

				log.info("download write loacl file path"
						+ localFileDir.getAbsolutePath() + "/" + fileName);

				int length = -1;
				while ((length = in.read(by)) != -1) {
					out.write(by, 0, length);
				}
			} else {
				if (resp != null) {
					log.error("download file response fail! respCode = "
							+ resp.getStatusLine().getStatusCode());
				}
				throw new Exception("dowload file response error !");
			}

		} finally {
			closeRes(httpGet, in, out);
		}
		return null;
	}

	public static HttpResponse httpRequest(HttpGet httpGet) throws Exception {
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(60000)
				.setConnectionRequestTimeout(60000).build();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultRequestConfig(defaultRequestConfig).build();
		RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
				.build();

		httpGet.setConfig(requestConfig);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
		} catch (UnsupportedEncodingException e) {
			log.error("httpGet url:"+httpGet.getURI(), e);
			throw e;
		} catch (ClientProtocolException e) {
			log.error("httpGet url:"+httpGet.getURI(), e);
			throw e;
		} catch (IOException e) {
			log.error("httpGet url:"+httpGet.getURI(), e);
			throw e;
		}
		return response;
	}
	

	public static HttpResponse httpRequest(List<NameValuePair> params,
			HttpPost httpPost) {
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(60000)
				.setConnectionRequestTimeout(60000).build();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultRequestConfig(defaultRequestConfig).build();
		RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
				.build();
		httpPost.setHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setConfig(requestConfig);
		HttpResponse response = null;
		try {
			if (CollectionUtils.isNotEmpty(params)) {
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			}
			response = httpclient.execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			log.error(Tools.getStringBuilder().append("httpclient 请求 ,UnsupportedEncodingException异常，url:  ").append(httpPost.getURI()).append(",param:").append(Tools.gsonToString(params))+"，错误信息："+e.getMessage(), e);
		} catch (ClientProtocolException e) {
			log.error(Tools.getStringBuilder().append("httpclient 请求 ,ClientProtocolException异常，url:  ").append(httpPost.getURI()).append(",param:").append(Tools.gsonToString(params))+"，错误信息："+e.getMessage(), e);
		} catch (IOException e) {
			log.error(Tools.getStringBuilder().append("httpclient 请求 ,IOException异常，url:  ").append(httpPost.getURI()).append(",param:").append(Tools.gsonToString(params))+"，错误信息："+e.getMessage(), e);
		}
		return response;
	}
	
}
