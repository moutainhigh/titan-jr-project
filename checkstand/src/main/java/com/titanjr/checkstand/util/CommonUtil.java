/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName CommonUtil.java
 * @author Jerry
 * @date 2017年11月25日 下午5:21:21  
 */
package com.titanjr.checkstand.util;

import java.util.Map;
import java.util.Random;

/**
 * @author Jerry
 * @date 2017年11月25日 下午5:21:21  
 */
public class CommonUtil {
	
	@SuppressWarnings("rawtypes")
	public static String mapString(Map<String, String[]> map){
		StringBuffer sb = new StringBuffer("\n");
		for(Map.Entry entry: map.entrySet()){
			String[] vals = (String[])entry.getValue();
			sb.append(entry.getKey()+"=[");
			for(int i=0;i<vals.length;i++){ 
				if(i>0){
					sb.append(","); 
				}
				sb.append(vals[i]);
		    } 
			sb.append("]\n");
		}
		return sb.toString();
	}
	
	/**
	 * 生成随机码
	 * @param n
	 * @return
	 */
	public static String getValidatecode(int n) {
		Random random = new Random();
		String sRand = "";
		n = n == 0 ? 4 : n;// default 4
		for (int i = 0; i < n; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}

}
