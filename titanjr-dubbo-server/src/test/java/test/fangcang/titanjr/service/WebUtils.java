package test.fangcang.titanjr.service;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.api.DefaultFopClient;
import com.titanjr.fop.constants.CommonConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class WebUtils {

    private final static Logger logger = LoggerFactory.getLogger(DefaultFopClient.class);

    /**
     * post参数到远程
     *
     * @param serviceURL
     * @param paramMap
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     */
    public static String doPost(String serviceURL, Map<String, String> paramMap, int connectTimeout, int readTimeout) throws IOException {
        String responseJson = null;

        if (null == paramMap) {
            return responseJson;
        }
        if (connectTimeout <= 0) {
            connectTimeout = 60000;
        }
        if (readTimeout <= 0) {
            readTimeout = 60000;
        }

        List<NameValuePair> params = buildNameValuePair(paramMap);
        HttpPost httpPost = new HttpPost(serviceURL);

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(readTimeout).setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(60000).build();

        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.setConfig(requestConfig);
        HttpResponse response;

        if (CollectionUtils.isNotEmpty(params)) {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        }
        response = httpclient.execute(httpPost);
        if (null != response) {
            HttpEntity entity = response.getEntity();
            responseJson = EntityUtils.toString(entity);

            if (response.getStatusLine().getStatusCode() == 302) {
                Header header = response.getFirstHeader("Location");
                //重定向地址
                String location = header.getValue();
                responseJson = doPost(location, paramMap, connectTimeout, readTimeout);
            }

            logger.debug("网关请求返回结果：{}" + responseJson);
            return responseJson;
        } else {
            return null;
        }

    }

    private static List<NameValuePair> buildNameValuePair(Map<String, String> paramMap) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : paramMap.keySet()) {
            params.add(new BasicNameValuePair(key, paramMap.get(key)));
        }
        return params;
    }


    public static String buildQuery(Map<String, String> paramMap, String encode) throws IOException {
        if (paramMap != null && !paramMap.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            Set paramEntry = paramMap.entrySet();
            boolean needConn = false;
            Iterator iterator = paramEntry.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                if (StringUtil.isValidString(key) && StringUtil.isValidString(value)) {
                    if (needConn) {
                        builder.append("&");
                    } else {
                        needConn = true;
                    }
                    builder.append(key).append("=").append(URLEncoder.encode(value, encode));
                }
            }
            return builder.toString();
        } else {
            return null;
        }
    }

    /**
     * 先固定返回 实际路由到的地址
     *
     * @param initServerUrl
     * @param appKey
     * @param appSecret
     * @param session
     * @return
     */
    public static String getActualUrl(String initServerUrl, String appKey, String appSecret, String session) {
        initServerUrl = initServerUrl + CommonConstants.fetchUrl;
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appKey", appKey);
        paramMap.put("appSecret", appSecret);
        paramMap.put("session", session);
        String responseUrl = null;
        try {
            responseUrl = WebUtils.doPost(initServerUrl, paramMap, 60000, 60000);
        } catch (IOException e) {
            logger.error("获取业务请求地址失败", e);
        }
        return responseUrl;
    }

    public static boolean isValidUrl(String str) {
        Pattern exp=Pattern.compile("^((http|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = exp.matcher(str);
        return matcher.matches();
    }
}
