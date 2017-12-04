package com.titanjr.checkstand.interceptor;

import com.titanjr.checkstand.constants.OperateTypeEnum;
import com.titanjr.checkstand.util.AccessLimiter;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.JRBeanUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求频率限制的拦截器
 * 需要得到传入的请求参数的某些字段才能判定出来
 * Created by zhaoshan on 2017/11/17.
 */
public class AccessLimitInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AccessLimitInterceptor.class);

    @Autowired
    private AccessLimiter accessLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //暂时只处理支付入口位置的请求
        if ("/checkstand/payment.shtml".equals(request.getRequestURI())){
            //OperateTypeEnum operateTypeEnum = JRBeanUtils.recognizeRequestType(request.getParameterMap().keySet());
        	OperateTypeEnum operateTypeEnum = JRBeanUtils.getOperateType(request);
            if(operateTypeEnum == null){
            	logger.info("未匹配到操作类型，请求参数为{}; 当前请求路径是{}", CommonUtil.mapString(request.getParameterMap()), request.getRequestURI());
                return true;//此处不处理
            }
            boolean isFrequency = accessLimiter.accessFrequency(operateTypeEnum);
            if (isFrequency) {
                logger.info("满足访问频次，执行下一步");
                return true;
            } else {
                logger.error("访问频率过高");
                //TODO 转走

            }
        } else {
            //暂不处理，但是可能还是需要考虑
            return true;
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
