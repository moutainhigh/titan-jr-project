package com.titanjr.checkstand.util;

import com.fangcang.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class WebUtils {

    private final static Logger logger = LoggerFactory.getLogger(WebUtils.class);

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

    /**
     * 暂时处理三种类型：Integer，Long,Integer
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    public static <T> T switch2RequestDTO(Class<T> clazz , HttpServletRequest request){
        T result = null;
        try {
            result = clazz.newInstance();
            Map paramMap =  WebUtil.getParameterMap(request);

            Class resultClass = (Class) result.getClass();
            Field[] resultFields = resultClass.getDeclaredFields();
            for ( Field field : resultFields ){
                field.setAccessible(true);
                String fieldType = field.getType().toString();
                if (paramMap.containsKey(field.getName()) && null != paramMap.get(field.getName())){
                    String param = URLDecoder.decode(paramMap.get(field.getName()).toString(),"UTF-8");
                    if (fieldType.endsWith("String")){
                        field.set(result,param);
                    }
                    if (fieldType.endsWith("Long") || fieldType.endsWith("long")){
                        field.set(result,Long.parseLong(param));
                    }
                    if (fieldType.endsWith("Integer") || fieldType.endsWith("int")){
                        field.set(result,Integer.parseInt(param));
                    }
                }

            }
        } catch (InstantiationException e) {
            logger.error("实例化失败", e);
            return null;
        } catch (IllegalAccessException e) {
            logger.error("实例化失败", e);
            return null;
        } catch (UnsupportedEncodingException e) {
            logger.error("实例化失败", e);
            return null;
        }
        return result;
    }
}
