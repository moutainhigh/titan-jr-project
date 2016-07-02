package com.fangcang.titanjr.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class HttpUtils {

    public static HttpPost buildPostForm(String url, Map<String, String> params) throws UnsupportedEncodingException {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for(String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        return httpost;
    }
}
