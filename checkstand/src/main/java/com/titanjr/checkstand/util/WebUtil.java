package com.titanjr.checkstand.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class WebUtil {

    /**
     * 获取请求的基础URL,截止到上下文
     */
    public static String getRequestBaseUrl(HttpServletRequest req) {
        String contextPath = req.getContextPath();
        String host = req.getServerName();
        if (req.getServerPort() != 80) {
            host += ":" + req.getServerPort();
        }
        String url = req.getScheme() + "://" + host + contextPath;
        return url;
    }
}
