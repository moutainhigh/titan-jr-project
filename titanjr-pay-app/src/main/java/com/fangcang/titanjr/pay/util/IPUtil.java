package com.fangcang.titanjr.pay.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.fangcang.util.StringUtil;

/**
 * IP工具类
 * @author jerry
 * @date 2017.6.12
 */
public class IPUtil {
	
	/**
	 * 获取用户真实的IP
	 * @author Jerry
	 * @date 2017年7月25日 上午11:57:49
	 *
	 * @param HttpServletRequest
	 * @return 真实的IP
	 */
	public static String getUserRealIP(HttpServletRequest request){
		
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtil.isValidString(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtil.isValidString(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();

	}
	
	
	/**
	 * 判断IP是否在网段中
	 *
	 * @author jerry
	 * 
	 * @param ip	IP地址
	 * @param maskIP	带掩码的IP段，eg:192.168.0.0/24
	 * @return	true表示IP在网段中，false表示IP不在网段中
	 */
	public static boolean isInRange(String ip, String maskIP) {
		if (StringUtils.isBlank(ip) || StringUtils.isBlank(maskIP)
				|| !maskIP.contains("/")) {
			return false;
		}
        String[] networkips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(networkips[0]) << 24)
                | (Integer.parseInt(networkips[1]) << 16)
                | (Integer.parseInt(networkips[2]) << 8)
                | Integer.parseInt(networkips[3]);
        int type = Integer.parseInt(maskIP.replaceAll(".*/", ""));
        int mask1 = 0xFFFFFFFF << (32 - type);
        String maskIp = maskIP.replaceAll("/.*", "");
        String[] maskIps = maskIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)
                | (Integer.parseInt(maskIps[1]) << 16)
                | (Integer.parseInt(maskIps[2]) << 8)
                | Integer.parseInt(maskIps[3]);

        return (ipAddr & mask1) == (cidrIpAddr & mask1);
    }
	
	
	/**
	 * 获取客户端网卡的MAC地址
	 * @param ip
	 * @return
	 */
	public static String getMac(String ip){
		String mac = null;
		try {
		   Process p = Runtime.getRuntime().exec("arp -n");
		   InputStreamReader ir = new InputStreamReader(p.getInputStream());
		   LineNumberReader input = new LineNumberReader(ir);
		   p.waitFor();
		   boolean flag = true;
		   String ipStr = "(" + ip + ")";
		   while(flag) {
		       String str = input.readLine();
		       if (str != null) {
		          if (str.indexOf(ipStr) > 1) {
			          int temp = str.indexOf("at");
			          mac = str.substring(
			          temp + 3, temp + 20);
			          break;
		          }
		       } else
		    flag = false;
		   }
		} catch (Exception e) {
		   e.printStackTrace(System.out);
		}
		return mac;
    }
	
	
	

}
