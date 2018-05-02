package com.fangcang.titanjr.web.util;

import java.security.interfaces.RSAPrivateKey;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.util.rsa.JsRSAUtil;

public class RSADecryptString {
	  private static final Log log = LogFactory.getLog(RSADecryptString.class);
	  
	  public static String decryptString(String result,HttpServletRequest request){
	    	try{
	    		RSAPrivateKey privateKey = (RSAPrivateKey)request.getServletContext().getAttribute("privateKey");
	    		String password = JsRSAUtil.decryptString(privateKey,result);
	        	return password;
	    	}catch(Exception e){
	    		log.error("解密失败"+e.getMessage(),e);
	    	}
	    	return null;
	    }
}
