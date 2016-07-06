package com.fangcang.titanjr.web.util;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;

import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.util.StringUtil;

public class TFSTools {
	/**
     * 校验注册验证码(短信或者邮件)
     * @return
     */
    public static String validateRegCode(HttpSession session,String receiveAddress,String pageInput){
    	if(!(StringUtil.isValidString(receiveAddress)&&StringUtil.isValidString(pageInput))){
    		return "WRONG";
    	}
    	String regcode_time = (String)session.getAttribute(CommonConstant.SESSION_KEY_REG_CODE+"_"+receiveAddress);
    	if(StringUtil.isValidString(regcode_time)){
    		String[] timeCode = regcode_time.split("_");
    		
    		Date codeCreateTime = DateUtil.toDataYYYYMMDDHHmmss(timeCode[0]);
    		Date nowDate = new Date();
    		boolean isExpire = DateUtils.addHours(codeCreateTime, CommonConstant.REG_CODE_TIME_OUT_HOUR).before(nowDate);
    		boolean isRightCode = timeCode[1].equals(pageInput);
    		if(!isExpire && isRightCode){
    			return "SUCCESS";
    		}else if(isExpire){
    			//过了有效期
    			return "EXPIRE";
    		}else if(!isRightCode){
    			//不正确
    			return "WRONG";
    		}
    		return "NOTEXIST";
    	}else{
    		//验证码已经失效
    		return "NOTEXIST";
    	}
    }
}
