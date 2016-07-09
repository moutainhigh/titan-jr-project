package com.fangcang.titanjr.common.util;

import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.fangcang.util.StringUtil;

/**
 * 其他无法归类的工具方法
 * @author luoqinglong
 * @2016年5月31日
 */
public class Tools {
	public final static String REGEX_EMAILL = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	public final static String REGEX_PHONE = "^13[0-9]{9}$|^14[0-9]{9}$|^15[0-9]{9}$|^18[0-9]{9}$|^17[0-9]{9}$";
	
	
	/**
	 * 检查是否是邮件地址
	 * @param input
	 * @return
	 */
	public static boolean isEmailAddress(String input){
		return Pattern.matches(REGEX_EMAILL, input);
	}
	/***
	 * 检查是否是邮件地址
	 * @param input
	 * @return
	 */
	public static boolean isNotEmailAddress(String input){
		return !isEmailAddress(input);
	}
	/**
	 * 检查时候是手机号码
	 * @param input
	 * @return
	 */
	public static boolean isPhone(String input){
		return Pattern.matches(REGEX_PHONE, input);
	}
	/**
	 * 检查是否是手机号码
	 * @param input
	 * @return
	 */
	public static boolean isNotPhone(String input){
		return !isPhone(input);
	}
	/**
	 * 获取验证码，去掉了和字母相近的数字
	 * @return
	 */
	public static String getRegCode(){
		return RandomStringUtils.random(6, new char[]{'2','3','4','5','6','7','9'});
	}
	/**
	 * 字符串空转null
	 * @param obj
	 * @return
	 */
	public static String blanktoNull(String obj){
		if(!StringUtil.isValidString(obj)){
			return null;
		}else{
			return obj;
		}
	}
	public static void main(String[] arg){
		///System.out.println(getRegCode());
	}
}
