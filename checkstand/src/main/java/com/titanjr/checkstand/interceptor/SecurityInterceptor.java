package com.titanjr.checkstand.interceptor;

import com.fangcang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 为了实现基础的请求来源IP过滤
 * 包括可能的请求路径，频率等
 * 同时web.XML应包含xss跨站，基础的sql注入等问题
 * Created by zhaoshan on 2017/11/15.
 */
public class SecurityInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //logger.info("请求参数为{}，当前请求路径是{}", request.getParameterMap(), request.getRequestURI());

        String realIp = request.getHeader("X-Forwarded-For");
        if (StringUtil.isValidString(realIp) && !"unKnown".equalsIgnoreCase(realIp)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = realIp.indexOf(",");
            if (index != -1) {
                realIp = realIp.substring(0, index);
            }
        }
        realIp = request.getHeader("X-Real-IP");
        if (!StringUtil.isValidString(realIp) || "unKnown".equalsIgnoreCase(realIp)) {
            realIp = request.getRemoteAddr();
        }
        if (StringUtil.isValidString(realIp) && !validIpAddress()) {
            logger.error("IP来源不合法，参数为{}，请求路径是{}", request.getParameterMap(), request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    //判定IP来源是否合法，暂时不判断
    private boolean validIpAddress() {
        return true;
    }

}
