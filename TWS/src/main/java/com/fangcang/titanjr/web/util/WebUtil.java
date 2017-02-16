package com.fangcang.titanjr.web.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.common.lang3.StringUtils;

public class WebUtil {
	/***
	 * 获取请求过来的url
	 * @param request
	 * @return
	 */
	public static String getForwordUrl(HttpServletRequest request){
		String forwordUrl = "";
		if (request.getMethod().equalsIgnoreCase("post")) {
			forwordUrl = request.getHeader("referer");
		} else {
			forwordUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
			if (StringUtils.isNotEmpty(request.getQueryString())) {
				forwordUrl += "?" + request.getQueryString();
			}
		} 
		return forwordUrl;
	}
	/***
	 * 拼接参数
	 * @param prefixUrl  请求地址
	 * @param param 参数
	 * @return
	 */
	public static String appendRequestParam(String prefixUrl, Map<String, String> param){
		if(prefixUrl.indexOf("?")==-1){
			return prefixUrl+"?"+generatorParamString(param).toString();
		}
		if(prefixUrl.indexOf("?")>-1&&prefixUrl.indexOf("?")==(prefixUrl.length()-1)){//最后一个字符为?
			return prefixUrl+generatorParamString(param);
		}else{
			return prefixUrl+"&"+generatorParamString(param).toString();
		}
	}
	/**
	 * @description 生成请求参数字符串
	 * @param parameters
	 * @return
	 */
	public static String generatorParamString(Map<String, String> parameters) {
        StringBuffer params = new StringBuffer();
        if(parameters != null) {
        	for(Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext(); ) {
        		String name = iter.next();
        		String value = parameters.get(name);
        		params.append(name + "=");
                params.append(value);
                 
                if(iter.hasNext())
                	params.append("&");
            }
        }
        return params.toString();
    }
}
