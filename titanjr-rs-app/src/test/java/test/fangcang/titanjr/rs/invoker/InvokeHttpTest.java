package test.fangcang.titanjr.rs.invoker;

import com.fangcang.util.HttpUtil;
import com.fangcang.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshan on 2016/7/21.
 */
public class InvokeHttpTest {

    private static final Log log = LogFactory.getLog(HttpUtil.class);

    public static void main(String[] args) {
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("payOrderCode", "TW16072019471"));
        params.add(new BasicNameValuePair("merchantCode", "M10000001"));
        params.add(new BasicNameValuePair("titanPayOrderCode", "TJO0501145786"));
        params.add(new BasicNameValuePair("payResult", "1"));
        params.add(new BasicNameValuePair("code", "valid"));
        try {
            HttpResponse resp = httpRequest(params, "http://local.fangcang.com:8080/HFP/titanPayCallBack");
            HttpEntity entity = resp.getEntity();
            response = EntityUtils.toString(entity);
        } catch (IOException e) {
            log.error("调用http请求通知支付失败", e);
        }
        log.info("调用http请求通知支付支付结果完成：" + response);
        if (!StringUtil.isValidString(response)) {
        	
            log.info("");
        } else {

        }
    }

    public static HttpResponse httpRequest(List<NameValuePair> params, String url) {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(5000)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "text/xml; charset=UTF-8");
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
