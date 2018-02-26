package com.titanjr.checkstand.util;

import com.titanjr.checkstand.constants.BusiCodeEnum;
import com.titanjr.checkstand.dto.TitanPayDTO;
import com.titanjr.checkstand.dto.TitanRefundDTO;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoshan on 2017/11/20.
 */
public class JRBeanUtils {

    private final static Logger logger = LoggerFactory.getLogger(JRBeanUtils.class);


    /**
     * 根据类注解，获取此类所有不为空的字段列表
     * @param cls
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Set<String> getRequiredFiledName(Class cls){

        Set<String> fieldResult = new HashSet<String>();

        Field[] fields =  cls.getDeclaredFields();
        for(Field field : fields) {
            String filedName = field.getName();
            logger.debug("当前属性名:{}",filedName);

            //获取属性上的指定类型的注释
            Annotation emptyAnno = field.getAnnotation(NotEmpty.class);
            Annotation blankAnno = field.getAnnotation(NotBlank.class);
            Annotation nullAnno = field.getAnnotation(NotNull.class);
            //有该类型的注释存在
            if (emptyAnno != null || blankAnno != null || nullAnno != null){
                fieldResult.add(filedName);
            }
        }

        return fieldResult;
    }

    /**
     * 根据请求参数类型，识别当前请求属于何种操作
     * @param paramKeySet
     * @return
     */
    public static BusiCodeEnum recognizeRequestType(Set<String> paramKeySet){
    	
        Set<String> requiredFields = JRBeanUtils.getRequiredFiledName(TitanPayDTO.class);
        Set<String> refundFields = JRBeanUtils.getRequiredFiledName(TitanRefundDTO.class);

        boolean isPayRequest = true;
        for (String requireField : requiredFields){
            if (!paramKeySet.contains(requireField)){
                isPayRequest = false;
                break;
            }
        }

        boolean isRefundRequest = true;
        for (String requireField : refundFields){
            if (!paramKeySet.contains(requireField)){
                isRefundRequest = false;
                break;
            }
        }

        if (isPayRequest) {
            return BusiCodeEnum.PAY_REQUEST;
        }

        if (isRefundRequest) {
            return BusiCodeEnum.REFUND_REQUEST;
        }

        return null;
    }
    
    /**
     * @author Jerry
     * @date 2017年12月1日 下午4:21:13
     */
    public static BusiCodeEnum getBusiCode(HttpServletRequest request){
    	BusiCodeEnum busiCodeEnum = null;
    	for (String paramKey : request.getParameterMap().keySet()) {
			if("busiCode".equals(paramKey)){
				String paramValue = request.getParameterMap().get("busiCode")[0];
				busiCodeEnum = BusiCodeEnum.getEnumByKey(paramValue);
			}
		}
    	return busiCodeEnum;
    }
    
    
    /**
     * 将对象有效字段用"&"连接起来
     * @author Jerry
     * @date 2018年2月11日 下午6:23:08
     */
    public static String beanToString(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
  
        StringBuffer sb = new StringBuffer(""); 
        
        for (Field field : obj.getClass().getDeclaredFields()) {    
            field.setAccessible(true);  
            if(!String.valueOf(field.get(obj)).equals("null") 
            		&& !String.valueOf(field.get(obj)).equals("")
            		&& !"serialVersionUID".equals(field.getName())
            		&& !"respJs".equals(field.getName())){
            	sb.append(field.getName()+"="+field.get(obj)+"&");
            }
        }
        for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
        	field.setAccessible(true);  
            if(!String.valueOf(field.get(obj)).equals("null") 
            		&& !String.valueOf(field.get(obj)).equals("")
            		&& !"serialVersionUID".equals(field.getName())){
            	sb.append(field.getName()+"="+field.get(obj)+"&");
            }
        }
        for (Field field : obj.getClass().getDeclaredFields()) {    
            field.setAccessible(true);  
            if("respJs".equals(field.getName())){
            	sb.append(field.getName()+"="+field.get(obj)+"&");
            }
        }
        if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
  
        return sb.toString();  
    }

}
