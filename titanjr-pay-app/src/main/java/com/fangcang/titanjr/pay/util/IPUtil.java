package com.fangcang.titanjr.pay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * IP工具类
 * @author jerry
 * @date 2017.6.12
 */
public class IPUtil {
	
	/**
	 * 获取真实的IP
	 *
	 * @author jerry
	 * @createDate 2017年1月5日 上午11:53:22
	 * 
	 * @param request	请求
	 * @return	真实的IP
	 */
	public static String getUserIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")||ip.equals("localhost")){
            	//根据网卡取本机配置的IP
            	InetAddress inet;
				try {
					inet = InetAddress.getLocalHost();
					ip= inet.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
        }
        
        if (ip != null && ip.trim().length() > 0) {  
        	String[] ipArray = ip.split(",");
            return ipArray[0];  
        }  
        return ip;
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
	
	
	public static String getMAC() {  
        String mac = null;  
        try {  
            Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");  
  
            InputStream is = pro.getInputStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
            String message = br.readLine();  
  
            int index = -1;  
            while (message != null) {  
                if ((index = message.indexOf("Physical Address")) > 0) {  
                    mac = message.substring(index + 36).trim();  
                    break;  
                }  
                message = br.readLine();  
            }  
            System.out.println(mac);  
            br.close();  
            pro.destroy();  
        } catch (IOException e) {  
            System.out.println("Can't get mac address!");  
            return null;  
        }  
        return mac;  
    }

}
