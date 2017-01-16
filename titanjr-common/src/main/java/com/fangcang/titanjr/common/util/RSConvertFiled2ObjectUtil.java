package com.fangcang.titanjr.common.util;

import java.lang.reflect.Field;

public class RSConvertFiled2ObjectUtil {
	 public static <T> T convertField2Object(Class<T> cls,String str) throws Exception{
		   T ref = cls.newInstance();
		   String[]  a = str.split("&");
		   for(int i=0;i<a.length;i++){
	    	   String key = a[i].split("=")[0];
	    	   String value = a[i].split("=").length>1?a[i].split("=")[1]:"";
	    	   for (Field field : cls.getDeclaredFields()) {
	               if (field.getName().equals(key)) {
	                   field = cls.getDeclaredField(key);
	                   field.setAccessible(true);
	                   field.set(ref, value);// 鍔ㄦ�璁剧疆鍊�
	                   break;
	               }
	           }
	    	  
	       }
		   return  ref;
	   }
}
