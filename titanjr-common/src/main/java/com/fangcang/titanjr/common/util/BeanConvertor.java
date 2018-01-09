package com.fangcang.titanjr.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * 自定义转换器
 * @author jerry
 * 
 * @date 2017.6.15
 *
 */
public final class BeanConvertor {
	
	/**
	 * 将对象转换成 List<NameValuePair>
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static List<NameValuePair> beanToList (Object object) throws Exception{
		
		List<NameValuePair> list = new ArrayList<NameValuePair>();
 
        @SuppressWarnings("rawtypes")
		Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(!"serialVersionUID".equals(field.getName()) && !String.valueOf(field.get(object)).equals("null") 
            		&& !String.valueOf(field.get(object)).equals("")){
                list.add(new BasicNameValuePair(field.getName(), field.get(object)+""));
            }
        }
        return list;
        
    }
	
	
	/**
	 * 将对象转换成Map
	 * @author Jerry
	 * @date 2018年1月3日 下午3:55:06
	 */
	public static Map<String, Object> beanToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
  
        Map<String, Object> map = new HashMap<String, Object>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            if(!String.valueOf(field.get(obj)).equals("null") 
            		&& !String.valueOf(field.get(obj)).equals("")){
            	map.put(field.getName(), field.get(obj));
            }
        }    
  
        return map;  
    }

}
