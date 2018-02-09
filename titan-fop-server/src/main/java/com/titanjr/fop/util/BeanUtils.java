package com.titanjr.fop.util;

import com.fangcang.util.DateUtil;
import com.fangcang.util.WebUtil;
import com.titanjr.fop.api.FopController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/27.
 */
public class BeanUtils {

    private final static Logger logger = LoggerFactory.getLogger(FopController.class);

    /**
     * 暂时处理三种类型：Integer，Long,Integer
     *
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    public static <T> T switch2RequestDTO(Class<T> clazz, HttpServletRequest request) {
        T result = null;
        try {
            result = clazz.newInstance();
            Map paramMap = WebUtil.getParameterMap(request);

            Class resultClass = (Class) result.getClass();
            Field[] resultFields = resultClass.getDeclaredFields();
            Field[] superFields = resultClass.getSuperclass().getDeclaredFields();
            for (Field field : resultFields) {
                field.setAccessible(true);
                String fieldType = field.getType().toString();
                if (paramMap.containsKey(field.getName()) && null != paramMap.get(field.getName())) {
                    String param = URLDecoder.decode(paramMap.get(field.getName()).toString(), "UTF-8");
                    if (fieldType.endsWith("String")) {
                        field.set(result, param);
                    }
                    if (fieldType.endsWith("Date")) {
                        field.set(result, DateUtil.stringToDate(param, "yyyy-MM-dd HH:mm:ss"));
                    }
                    if (fieldType.endsWith("Long") || fieldType.endsWith("long")) {
                        field.set(result, Long.parseLong(param));
                    }
                    if (fieldType.endsWith("Integer") || fieldType.endsWith("int")) {
                        field.set(result, Integer.parseInt(param));
                    }
                }

            }
            //父类中均为字符串类型
            for (Field field : superFields) {
                field.setAccessible(true);
                if (paramMap.containsKey(field.getName()) && null != paramMap.get(field.getName())) {
                    String param = URLDecoder.decode(paramMap.get(field.getName()).toString(), "UTF-8");
                    field.set(result, param);
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
