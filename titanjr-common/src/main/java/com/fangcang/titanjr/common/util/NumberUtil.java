package com.fangcang.titanjr.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fangcang.util.StringUtil;
/**
 * 数字转换
 * @author luoqinglong
 * @date   2016年12月29日
 */
public class NumberUtil {
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
		if(!StringUtil.isValidString(param1)){
			param1 = "0";
		}
		if(!StringUtil.isValidString(param2)){
			param2 = "0";
		}
		return new BigDecimal(param1).add(new BigDecimal(param2));
	}
	
}
