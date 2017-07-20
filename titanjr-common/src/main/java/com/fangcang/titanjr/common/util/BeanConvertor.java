package com.fangcang.titanjr.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

}
