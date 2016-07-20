package com.fangcang.titanjr.common.util;

public class DubboServerJDBCProperties {
	/**
	 * 金融邮箱服务
	 */
	private  static  String  jrEmailServer;
	private  static  String  jrEmailFrom;
	private  static  String  jrEmailUsername;
	private  static  String  jrEmailPassword;
	private  static  String  jrEmailPort;
	
	/**
	 * ftp
	 */
	private static String ftpServerIp;
	private static int ftpServerPort;
	private static String ftpServerUser;
	private static String ftpServerPassword;
	
	
	public  String getJrEmailServer() {
		return jrEmailServer;
	}
	public  void setJrEmailServer(String jrEmailServer) {
		DubboServerJDBCProperties.jrEmailServer = jrEmailServer;
	}
	public  String getJrEmailFrom() {
		return jrEmailFrom;
	}
	public  void setJrEmailFrom(String jrEmailFrom) {
		DubboServerJDBCProperties.jrEmailFrom = jrEmailFrom;
	}
	public  String getJrEmailUsername() {
		return jrEmailUsername;
	}
	public  void setJrEmailUsername(String jrEmailUsername) {
		DubboServerJDBCProperties.jrEmailUsername = jrEmailUsername;
	}
	public  String getJrEmailPassword() {
		return jrEmailPassword;
	}
	public  void setJrEmailPassword(String jrEmailPassword) {
		DubboServerJDBCProperties.jrEmailPassword = jrEmailPassword;
	}
	public  String getJrEmailPort() {
		return jrEmailPort;
	}
	public  void setJrEmailPort(String jrEmailPort) {
		DubboServerJDBCProperties.jrEmailPort = jrEmailPort;
	}
	public static String getFtpServerIp() {
		return ftpServerIp;
	}
	public static void setFtpServerIp(String ftpServerIp) {
		DubboServerJDBCProperties.ftpServerIp = ftpServerIp;
	}
	public static int getFtpServerPort() {
		return ftpServerPort;
	}
	public static void setFtpServerPort(int ftpServerPort) {
		DubboServerJDBCProperties.ftpServerPort = ftpServerPort;
	}
	public static String getFtpServerUser() {
		return ftpServerUser;
	}
	public static void setFtpServerUser(String ftpServerUser) {
		DubboServerJDBCProperties.ftpServerUser = ftpServerUser;
	}
	public static String getFtpServerPassword() {
		return ftpServerPassword;
	}
	public static void setFtpServerPassword(String ftpServerPassword) {
		DubboServerJDBCProperties.ftpServerPassword = ftpServerPassword;
	}
	
}
