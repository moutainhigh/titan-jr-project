package com.fangcang.titanjr.dto.response;

import java.io.Serializable;
/**
 * ftp配置参数
 * @author luoqinglong
 *
 */
public class FTPConfigResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4182864450694904535L;
	private  String ftpServerIp;
	private  int ftpServerPort;
	private  String ftpServerUser;
	private  String ftpServerPassword;
	public String getFtpServerIp() {
		return ftpServerIp;
	}
	public void setFtpServerIp(String ftpServerIp) {
		this.ftpServerIp = ftpServerIp;
	}
	public int getFtpServerPort() {
		return ftpServerPort;
	}
	public void setFtpServerPort(int ftpServerPort) {
		this.ftpServerPort = ftpServerPort;
	}
	public String getFtpServerUser() {
		return ftpServerUser;
	}
	public void setFtpServerUser(String ftpServerUser) {
		this.ftpServerUser = ftpServerUser;
	}
	public String getFtpServerPassword() {
		return ftpServerPassword;
	}
	public void setFtpServerPassword(String ftpServerPassword) {
		this.ftpServerPassword = ftpServerPassword;
	}
	
	
}
