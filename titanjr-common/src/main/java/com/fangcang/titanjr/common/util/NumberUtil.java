package com.fangcang.titanjr.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcang.util.StringUtil;
/**
 * 数字转换
 * @author luoqinglong
 * @date   2016年12月29日
 */
public class NumberUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(NumberUtil.class);
	private static final DecimalFormat decimalFormat = new DecimalFormat("##0.00");
	/***
	 * 元转分
	 * @param number 0.5元
	 * @return  50 分
	 */
	public static String covertToCents(String number) {
	        String cents = null;
	        if (StringUtil.isValidString(number)) {
	            BigDecimal amount = new BigDecimal(number);
	            BigDecimal transfer = new BigDecimal(100);
	            //将元转换为分
	            amount = amount.multiply(transfer);
	            int index = amount.toString().indexOf(".");
	            if (index > 0) {
	                cents = amount.toString().substring(0, index);
	            } else {
	                cents = amount.toString();
	            }
	        }
	        return cents;
	}
	/***
	 * 分转为元(保留两位小数)
	 * @param fen 2分
	 * @return 0.02元
	 */
	public static String covertToYuan(String fen) {
        if (StringUtil.isValidString(fen)) {
            BigDecimal fenBigDecimal = new BigDecimal(fen);
            BigDecimal transfer = new BigDecimal(100);
            fenBigDecimal = fenBigDecimal.divide(transfer);
           return decimalFormat.format(fenBigDecimal);
        }else{
        	return "0.00";
        }
    }
	/***
	 * 两个数相加 
	 * @param param1 
	 * @param param2 
	 * @return
	 */
	public static BigDecimal add(String param1,String param2){
		if(param1==null||"".equals(param1.toString())){
			param1 = "0";
		}
		if(param2==null||"".equals(param2.toString())){
			param2 = "0";
		}
		try {
			return new BigDecimal(param1.toString()).add(new BigDecimal(param2.toString()));
		} catch (Exception e) {
			LOGGER.error("数字加法异常,参数param1:"+param1.toString()+",参数param2:"+param2.toString(),e);
			return BigDecimal.ZERO;
		}
	}
	
	/***
	 * 两个数相减
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static BigDecimal subtract(Object param1,Object param2){
		if(param1==null||"".equals(param1.toString())){
			param1 = "0";
		}
		if(param2==null||"".equals(param2.toString())){
			param2 = "0";
		}
		try {
			return new BigDecimal(param1.toString()).subtract(new BigDecimal(param2.toString()));
		} catch (Exception e) {
			LOGGER.error("数字减法异常,参数param1:"+param1.toString()+",参数param2:"+param2.toString(),e);
			return BigDecimal.ZERO;
		}
	}
	/***
	 * 两个数相乘
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static BigDecimal multiply(Object param1,Object param2){
		if(param1==null||"".equals(param1.toString())){
			param1 = "0";
		}
		if(param2==null||"".equals(param2.toString())){
			param2 = "0";
		}
		try {
			return new BigDecimal(param1.toString()).multiply(new BigDecimal(param2.toString()));
		} catch (Exception e) {
			LOGGER.error("数字减法异常,参数param1:"+param1.toString()+",参数param2:"+param2.toString(),e);
			return BigDecimal.ZERO;
		}
	}
	/***
	 * 两个数相除
	 * @param param1 被除数
	 * @param param2 除数
	 * @param scale  小数保留位数
	 * @return
	 */
	public static BigDecimal divide(Object param1,Object param2,int scale){
		if(param1==null||"".equals(param1.toString())){
			param1 = "0";
		}
		if(param2==null||"".equals(param2.toString())){
			param2 = "0";
		}
		if(scale<0){
			scale = 0;
		}
		try {
			return new BigDecimal(Double.valueOf(param1.toString())).divide(new BigDecimal(Double.valueOf(param2.toString())),scale,RoundingMode.HALF_UP);
		} catch (Exception e) {
			LOGGER.error("数字减法异常,参数param1:"+param1.toString()+",参数param2:"+param2.toString(),e);
			return BigDecimal.ZERO;
		}
	}
	/***
	 * 两个数相除,默认保留两位小数 
	 * @param param1 被除数
	 * @param param2 除数
	 * @return
	 */
	public static BigDecimal divide(Object param1,Object param2){
		if(param1==null||"".equals(param1.toString())){
			param1 = "0";
		}
		if(param2==null||"".equals(param2.toString())){
			param2 = "0";
		}
		return divide(param1,param2,2);//默认保留两位有效数字
	}
	
	
	public static void main(String[] arg){
    	System.out.println(subtract("3.05","0.21").toString());
    }
}
