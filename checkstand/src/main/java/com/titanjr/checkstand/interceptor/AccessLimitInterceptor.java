package com.titanjr.checkstand.interceptor;

import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.BusiCodeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.respnse.RSResponse;
import com.titanjr.checkstand.util.AccessLimiter;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.JRBeanUtils;

import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) {

        try {
            logger.info("accessLimitInterceptor address：{}", request.getRequestURI());
            //单独处理同一张单，一个时间间隔内不能重复支付的问题
            if ("/checkstand/resultPage.shtml".equals(request.getRequestURI()) || "/checkstand/pathTest.shtml".equals(request.getRequestURI())) {
                return true;
            }
            if (request.getRequestURI().indexOf("/pay") > -1 && !"/checkstand/payment.shtml".equals(request.getRequestURI()) &&
                    request.getRequestURI().indexOf("/entrance") < 0) {
                BusiCodeEnum busiCodeEnum = JRBeanUtils.getBusiCode(request);
                if (busiCodeEnum != null && StringUtil.isValidString(request.getParameter("orderNo"))) {
                    StringBuilder builder = new StringBuilder(request.getRequestURI());
                    builder.append(request.getParameter("orderNo"));
                    boolean isInterval = accessLimiter.accessInterval(busiCodeEnum, builder.toString());
                    if (!isInterval) {
                        logger.error("同一张单访问频率过高，单号为：{}", request.getParameter("orderNo"));
                        if (needGoPage(request.getRequestURI())) {
                            httpServletResponse.sendRedirect("resultPage.shtml");
                        } else {
                            resolveJsonResult(httpServletResponse);
                        }
                        return false;
                    }
                }
            }

            //只处理最终端的请求
            if (!"/checkstand/payment.shtml".equals(request.getRequestURI()) &&
                    !"/resultPage.shtml".equals(request.getRequestURI()) &&
                    request.getRequestURI().indexOf("entrance") < 0 &&
                    request.getRequestURI().indexOf("callback") < 0) {
                BusiCodeEnum busiCodeEnum = JRBeanUtils.getBusiCode(request);
                logger.info("拦截器3，频率检查:" + busiCodeEnum);
                if (busiCodeEnum == null) {
                    logger.info("未匹配到操作类型，请求参数为{}; 当前请求路径是{}",
                            CommonUtil.mapString(request.getParameterMap()), request.getRequestURI());
                    if (needGoPage(request.getRequestURI())) {
                        httpServletResponse.sendRedirect("resultPage.shtml");
                    } else {
                        resolveJsonResult(httpServletResponse);
                    }
                    return false;
                }
                boolean isFrequency = accessLimiter.accessFrequency(busiCodeEnum);
                if (isFrequency) {
                    logger.info("满足访问频次，执行下一步");
                    return true;
                } else {
                    logger.error("当前接口：{},请求路径：{}，访问频率过高", busiCodeEnum, request.getRequestURI());
                    if (needGoPage(request.getRequestURI())) {
                        httpServletResponse.sendRedirect("resultPage.shtml");
                    } else {
                        resolveJsonResult(httpServletResponse);
                    }
                    return false;
                }
            } else {
                //暂不处理，需要在 SecurityInterceptor 核实所有链接
                logger.info("当前地址无需处理，请求地址：{}", request.getRequestURI());
                return true;
            }
        } catch (IOException e) {
            logger.error("accessLimitInterceptor拦截器异常：", e);
            return false;
        }

    }

    private boolean needGoPage(String url) {
        if (url.indexOf("wxPicture") > -1 || url.indexOf("netBankPay") > -1) {
            return true;
        }
        return false;
    }

    private void resolveJsonResult(HttpServletResponse httpServletResponse) {
        RSResponse rsResponse = new RSResponse();
        rsResponse.putErrorResult(RSErrorCodeEnum.REQUEST_MORE);
        PrintWriter writer;
        try {
            writer = httpServletResponse.getWriter();
            writer.write(JSONSerializer.toJSON(rsResponse).toString());
        } catch (IOException e) {
            logger.error("获取输出异常：{}", e.getMessage(), e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
