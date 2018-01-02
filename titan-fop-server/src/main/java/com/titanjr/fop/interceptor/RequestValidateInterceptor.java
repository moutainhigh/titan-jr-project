package com.titanjr.fop.interceptor;

import com.fangcang.util.SpringContextUtil;
import com.fangcang.util.StringUtil;
import com.titanjr.fop.dao.RequestSessionDao;
import com.titanjr.fop.entity.RequestSession;
import com.titanjr.fop.util.FopUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 基础校验操作
 * 验证签名sign信息以及session信息
 * Created by zhaoshan on 2016/4/18.
 */
public class RequestValidateInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(RequestValidateInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String sign = httpServletRequest.getParameter("sign");
        TreeMap treeMap = new TreeMap();
        for (Map.Entry entry : httpServletRequest.getParameterMap().entrySet()) {
            if (!entry.getKey().equals("sign")) {
                treeMap.put(entry.getKey(), ((String[]) entry.getValue())[0]);
            }
        }
        //设置默认后台的secret
        String appSecret = "DC368712-18A4-4290-9A58-FF995DC161DC";
        Set entrySet = treeMap.entrySet();
        StringBuilder stringBuilder = new StringBuilder(appSecret);
        Iterator iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (StringUtil.isValidString((String) entry.getKey()) && StringUtil.isValidString((String) entry.getValue())) {
                stringBuilder.append((String) entry.getKey()).append((String) entry.getValue());
            }
        }
        byte[] encryptRequest = FopUtils.encryptMD5(stringBuilder.toString());
        String requestSig = FopUtils.byte2hex(encryptRequest);

        logger.info("传入的签名为：{}，校验计算出的签名为：{}", sign, requestSig);
        //将校验结果写入attribute中
        if (!sign.equals(requestSig)) {
            httpServletRequest.setAttribute("signValid", "false");
        } else {
            httpServletRequest.setAttribute("signValid", "true");
            httpServletRequest.setAttribute("appSecret",appSecret);
        }

        //验证session信息：
        RequestSessionDao requestSessionDao = (RequestSessionDao)SpringContextUtil.getBean("requestSessionDao");
        if (StringUtil.isValidString(httpServletRequest.getParameter("session"))){
            RequestSession requestSession = new RequestSession();
            requestSession.setAppKey(httpServletRequest.getParameter("appKey"));
            requestSession.setAppSecret(appSecret);
            requestSession.setSession(httpServletRequest.getParameter("session"));
            List<RequestSession> sessionList = requestSessionDao.queryReqSession(requestSession);
            if (CollectionUtils.isEmpty(sessionList)){
                httpServletRequest.setAttribute("sessionValid", "false");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
