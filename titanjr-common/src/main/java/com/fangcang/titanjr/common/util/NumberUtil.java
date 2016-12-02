package com.fangcang.titanjr.common.util;

import java.math.BigDecimal;

import com.fangcang.util.StringUtil;

public class NumberUtil {
	
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
}
