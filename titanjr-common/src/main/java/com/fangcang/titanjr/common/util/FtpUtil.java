package com.fangcang.titanjr.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.fangcang.util.StringUtil;

/**
 * ftp文件上传下载帮助类
 * 引用其他开发者使用的类
 */
public class FtpUtil {
	/**
	 * ftp上传服务器默认配置
	 */
//	private static final String DEFAULT_FTP_IP = "192.168.2.100";
//	private static final int DEFAULT_FTP_PORT = 21;
//	private static final String DEFAULT_FTP_USERNAME = "fangcang168";
//	private static final String DEFAULT_FTP_PASSWORD = "fangcang168";
	
	/**
	 * 用户注册上传的资料
	 */
	public static final String UPLOAD_PATH_ORG_REGISTER = "/org_register";
	/**
	 * 授信申请上传的文件资料
	 */
	public static final String UPLOAD_PATH_CREDIT_APPLY = "/credit_apply";
	/**
	 * 贷款申请上传的文件资料
	 */
	public static final String UPLOAD_PATH_LOAN_APPLY="/loan_apply";
	//TODO 这个后面要删除掉最后的斜杠
	public static String baseLocation = "/data/image/upload/images/titanjr";
	
	public static String baseUrlPrefix = "http://image.fangcang.com/upload/images/titanjr";
	
	private FTPClient ftpClient;
	private  String serverIp;
	private  int serverPort;
	private  String serverUser;
	private  String serverPassword;
	private boolean isLogin;
	

	private static final Log logger = LogFactory.getLog(FtpUtil.class);

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getFormatDate(){
		return SIMPLE_DATE_FORMAT.format(new Date());
	}
	
	public static String createFileName(){
		long r = new Date().getTime();
		int b = 100000 + RandomUtils.nextInt(100000);
		return String.valueOf(r)+String.valueOf(b);
	}
	
	public FtpUtil(){
		
	}
	/**
	 * FtpUtil构造函数
	 */
	public FtpUtil(String strIp, int intPort, String user, String password) throws Exception {
		if (this.isBlank(strIp)) {
			throw new IllegalArgumentException("ftp ip不能为空");
		}
		if (this.isBlank(user)) {
			user = "";
		}

		if (this.isBlank(password)) {
			password = "";
		}

		this.serverIp = strIp;
		this.serverPort = intPort;
		this.serverUser = user;
		this.serverPassword = password;
		this.ftpClient = new FTPClient();
	}

	/**
	 * @return 判断是否登入成功
	 */
	public boolean ftpLogin() throws Exception {
		boolean isLogin = false;
		try {
			FTPClientConfig ftpClientConfig = new FTPClientConfig();
			ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
			this.ftpClient.setControlEncoding("GBK");
			this.ftpClient.configure(ftpClientConfig);

			if (this.serverPort > 0) {
				this.ftpClient.connect(this.serverIp, this.serverPort);
			} else {
				this.ftpClient.connect(this.serverIp);
			}

			// FTP服务器连接回答
			int reply = this.ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				this.ftpClient.disconnect();
				logger.error("登录FTP服务失败！ftpServerUser:"+this.serverUser+",ftpServerPassword:"+this.serverPassword+",ftpServerIp:"+this.serverIp+",ftpServerPort:"+this.serverPort);
				return isLogin;
			}

			this.ftpClient.login(this.serverUser, this.serverPassword);

			// 设置传输协议
			this.ftpClient.enterLocalPassiveMode();
			this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			this.ftpClient.setBufferSize(1024 * 2);
			this.ftpClient.setDataTimeout(30 * 1000);

			isLogin = true;
			this.isLogin = isLogin;
		} catch (Exception e) {
			logger.error("登录FTP服务失败！ftpServerUser:"+this.serverUser+",ftpServerPassword:"+this.serverPassword+",ftpServerIp:"+this.serverIp+",ftpServerPort:"+this.serverPort,e);
			throw e;
		}

		return isLogin;
	}

	/**
	 * 退出关闭服务器链接
	 */
	public void ftpLogOut() throws Exception {
		if (null != this.ftpClient && this.ftpClient.isConnected()) {
			try {
				boolean reuslt = this.ftpClient.logout();// 退出FTP服务器
				if (reuslt) {
					logger.info("成功退出服务器");
				}
			} catch (IOException e) {
				logger.warn("退出FTP服务器异常！", e);
				throw e;
			} finally {
				try {
					this.ftpClient.disconnect();// 关闭FTP服务器的连接
				} catch (IOException e) {
					logger.warn("关闭FTP服务器的连接异常！", e);
					throw e;
				}
			}
		}
	}

	public static void makeLocalDirectory(String path){
		File file = new File(path);
		file.mkdirs();
	}

	/**
	 * 创建远程目录
	 *
	 * @param romoteUploadePath 远程目录 ,如/data
	 */
	public boolean makeDirectory(String romoteUploadePath) throws Exception {
		if (this.isBlank(romoteUploadePath)) {
			return false;
		}
		try {
			String[] dirs = (baseLocation + romoteUploadePath).split("/");
			String tempString = "";
			for(String dir : dirs ){
				if(StringUtil.isValidString(dir)){
					tempString = tempString + "/" + dir;
					boolean flag = this.ftpClient.changeWorkingDirectory(tempString);
					if(!flag){
						boolean makeState = this.ftpClient.makeDirectory(dir);
						if(!makeState){
							logger.error("mk dir error,pwd:"+tempString+"dir:"+dir);
							return false;
						}
						this.ftpClient.changeWorkingDirectory(tempString);
					}
				}
			}
			boolean cdState = this.ftpClient.changeWorkingDirectory(tempString);
			return cdState;
		} catch (IOException e) {
			logger.error("创建目录失败[" + romoteUploadePath + "]", e);
			throw e;
		}
	}
	
	public List<String> listFiles(String folderPath)throws Exception
	{
		List<String> list = new ArrayList<String>();
		try {
			String folder = baseLocation + folderPath;
			 FTPFile[] ftpFile = ftpClient.listFiles(folder);
			 for (FTPFile ftpFile2 : ftpFile) {
				 list.add(ftpFile2.getName()); 
			}
		} catch (IOException e) {
			logger.error("文件列表访问失败:",e);
			throw e;
			
		}
		return list;
	}
	
	/**
	 * 
	 * @param folderPath 格式:/person/im
	 * @return
	 */
	public boolean existsDirectory(String folderPath) {
		try {
			String folder = baseLocation + folderPath;
			boolean flag = ftpClient.changeWorkingDirectory(folder);
			return flag;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteFile(String filePath) throws Exception {
		if (this.isBlank(filePath)) {
			return false;
		}
		try {
			if(!filePath.startsWith("/")){
				filePath ="/"+filePath;
			}
			this.ftpClient.deleteFile(baseLocation + filePath);
			return true;
		} catch (Exception e) {
			logger.error("删除文件失败[" + filePath + "]", e);
			throw e;
		}
	}

	/**
	 * 上传Ftp文件
	 *
	 * @param localFile        本地文件
	 * @param remoteUploadePath 上传服务器路径,应该以/结束
	 */
	public boolean uploadFile(File localFile, String remoteUploadePath) throws Exception {
		if (!this.isLogin) {
			return false;
		}

		if (localFile == null || !localFile.exists()) {
			return false;
		}

		if (this.isBlank(remoteUploadePath)) {
			return false;
		}

		BufferedInputStream inStream = null;
		boolean success = false;
		try {
			if (!existsDirectory(remoteUploadePath)) {
				this.makeDirectory(remoteUploadePath);
			}
			this.ftpClient.changeWorkingDirectory(remoteUploadePath);// 改变工作路径

			inStream = new BufferedInputStream(new FileInputStream(localFile));

			logger.info(localFile.getName() + "开始上传.....");

			success = this.ftpClient.storeFile(localFile.getName(), inStream);

			if (success == true) {
				logger.info(localFile.getName() + "上传成功");
				return success;
			}
		} catch (FileNotFoundException e) {
			logger.error(localFile + "未找到", e);
			throw e;
		} catch (IOException e) {
			logger.error("上传文件出现错误", e);
			throw e;
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					logger.error("上传文件出现错误", e);
					throw e;
				}
			}
		}

		return success;
	}
	/**
	 * 上传数据流至FTP服务器
	 * 
	 * @param fileName 文件名称,如123.jpg
	 * @param inStream 文件流
	 * @param remoteUploadePath 上传服务器路径,应该以/结束
	 * */
	public boolean uploadStream(String fileName,InputStream inStream, String remoteUploadePath) throws Exception{
		if(!this.isLogin){
			return false;
		}
		boolean success = false;
		try {
			if (!existsDirectory(remoteUploadePath)){
				this.makeDirectory(remoteUploadePath);
			}
			this.ftpClient.changeWorkingDirectory(remoteUploadePath);// 改变工作路径
			logger.info(fileName+"开始上传至.....");
			success = this.ftpClient.storeFile(fileName, inStream);
			if (success == true) {
				logger.info(fileName+ "上传成功");
				return success;
			}
			logger.info("上传失败");
		}catch (IOException e) {
			e.printStackTrace();
			logger.error("上传文件出现错误", e);
			throw e;
		} 
		
		return success;
	}

	/**
	 * 下载文件
	 *
	 * @param remoteFileName     待下载的ftp文件名称,如：aaa.jpg
	 * @param localDires         本地磁盘路径,如:/data/10001
	 * @param remoteDownLoadPath ftp文件所在的磁盘路径,/data/person
	 */

	public boolean downloadFile(String remoteFileName, String localDires,
								String remoteDownLoadPath) throws Exception {
		if (this.isBlank(remoteFileName)
				|| this.isBlank(localDires)
				|| this.isBlank(remoteDownLoadPath)) {
			return false;
		}

		String strFilePath = localDires +"/"+ FileHelp.getFileName(remoteFileName);
		BufferedOutputStream outStream = null;
		boolean success = false;
		try {
			boolean dirStatus = this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);//改变工作目录
			outStream = new BufferedOutputStream(new FileOutputStream(strFilePath));

			logger.info(remoteDownLoadPath+"/"+remoteFileName + "开始下载....");

			success = this.ftpClient.retrieveFile(remoteFileName, outStream);

			if (success == true) {
				logger.info(remoteDownLoadPath+"/"+remoteFileName + "成功下载到" + strFilePath);
				return success;
			}
		} catch (Exception e) {
			logger.error(remoteDownLoadPath+"/"+remoteFileName + "下载失败", e);
			throw e;
		} finally {
			if (null != outStream) {
				try {
					outStream.flush();
					outStream.close();
				} catch (IOException e) {
					logger.error("下载文件时关闭流失败[" + strFilePath + "]", e);
					throw e;
				}
			}
		}

		if (success == false) {
			logger.error(remoteFileName + "下载失败!!!");
		}
		return success;
	}
	
	/**
	 * 上传文件夹
	 *
	 * @param localDirectory      本地文件夹
	 * @param remoteDirectoryPath Ftp服务器路径 以目录"/"结束
	 */
	public boolean uploadDirectory(String localDirectory,
								   String remoteDirectoryPath) throws Exception {
		if (this.isBlank(localDirectory) || this.isBlank(remoteDirectoryPath)) {
			return false;
		}

		File src = new File(localDirectory);
		if (!src.exists()) {
			return false;
		}

		try {
			remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";
			this.ftpClient.makeDirectory(remoteDirectoryPath);
		} catch (IOException e) {
			logger.error(remoteDirectoryPath + "目录创建失败");
		}

		File[] allFile = src.listFiles();
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (!allFile[currentFile].isDirectory()) {
				String srcName = allFile[currentFile].getPath().toString();
				uploadFile(new File(srcName), remoteDirectoryPath);
			}
		}
		for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
			if (allFile[currentFile].isDirectory()) {
				// 递归
				uploadDirectory(allFile[currentFile].getPath().toString(),
						remoteDirectoryPath);
			}
		}

		return true;
	}

	/**
	 * 下载文件夹
	 *
	 * @param localDirectoryPath 本地磁盘目录
	 * @param remoteDirectory    远程文件夹
	 */
	public boolean downLoadDirectory(String localDirectoryPath,
									 String remoteDirectory) throws Exception {
		try {
			String fileName = new File(remoteDirectory).getName();
			localDirectoryPath = localDirectoryPath + fileName + "//";
			new File(localDirectoryPath).mkdirs();
			FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);
			for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
				if (!allFile[currentFile].isDirectory()) {
					downloadFile(allFile[currentFile].getName(),
							localDirectoryPath, remoteDirectory);
				}
			}
			for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
				if (allFile[currentFile].isDirectory()) {
					String strremoteDirectoryPath = remoteDirectory + "/"
							+ allFile[currentFile].getName();
					downLoadDirectory(localDirectoryPath,
							strremoteDirectoryPath);
				}
			}
		} catch (IOException e) {
			logger.error("下载文件夹失败", e);
			throw e;
		}

		return true;
	}

	private boolean isBlank(String s) {
		return s == null || "".equals(s);
	}

	/**
	 * 
	 * @param file
	 * @param pathPrefix
	 * @return
	 * @throws Exception
	 */
	public static boolean uploadFileExt(File file, String remoteUploadePath) {
		FtpUtil ftp = null;
		try {
			ftp = new FtpUtil(DubboServerJDBCProperties.getFtpServerIp(), DubboServerJDBCProperties.getFtpServerPort(), DubboServerJDBCProperties.getFtpServerUser(), DubboServerJDBCProperties.getFtpServerPassword());
			ftp.ftpLogin();
			ftp.uploadFile(file, remoteUploadePath);
			ftp.ftpLogOut();
		} catch (Exception e) {
			logger.error("文件上传失败,pathPrefix:"+remoteUploadePath+",fileName:"+file.getName(),e);
			return false;
		}finally{
			if(ftp!=null){
				try {
					ftp.ftpLogOut();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	/***
	 * 上传图片  
	 * @param fileName
	 * @param inStream
	 * @param remoteUploadePath
	 * @return  格式：http://image.fangcang.com/upload/titanjr/org_register/123.jpg
	 */
	public static String uploadStreamExt(String fileName,InputStream inStream, String remoteUploadePath) {
		FtpUtil ftp = null;
		try {
			ftp = new FtpUtil(DubboServerJDBCProperties.getFtpServerIp(), DubboServerJDBCProperties.getFtpServerPort(), DubboServerJDBCProperties.getFtpServerUser(), DubboServerJDBCProperties.getFtpServerPassword());
			ftp.ftpLogin();
			ftp.uploadStream(fileName, inStream, remoteUploadePath);
			ftp.ftpLogOut();
		} catch (Exception e) {
			logger.error("文件上传失败,remoteUploadePath:"+remoteUploadePath+",fileName:"+fileName,e);
			return "";
		}finally{
			if(ftp!=null){
				try {
					ftp.ftpLogOut();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return baseUrlPrefix + remoteUploadePath + "/" + fileName;
	}
	
	public static void main(String[] args) throws Exception {
		String localEnterpriseDocumentInfoPath = "F:/creditimg";//TitanFinancialLoanCreditServiceImpl.class.getClassLoader().getResource("").getPath()+"/tmp"+File.separator+FtpUtil.UPLOAD_PATH_CREDIT_APPLY+File.separator+loanCreditOrder.getOrgCode()+File.separator+"EnterpriseCreditPackage"+File.separator+"EnterpriseDocumentInfo";
		//担保人证件资料本地路径
		String localGuarantorDocumentsInfoPath = "F:/creditimg";//TitanFinancialLoanCreditServiceImpl.class.getClassLoader().getResource("").getPath()+"/tmp"+File.separator+FtpUtil.UPLOAD_PATH_CREDIT_APPLY+File.separator+loanCreditOrder.getOrgCode()+File.separator+"EnterpriseCreditPackage"+File.separator+"GuarantorDocumentsInfo";
		
		//先删除文件
		
		FtpUtil ftpUtil = new FtpUtil("192.168.2.100",21,"fangcang168","fangcang168");
		ftpUtil.ftpLogin();
		//boolean s = ftpUtil.ftpClient.changeWorkingDirectory("/data/image/upload/images/titanjr/credit_apply\TJM10000109");
		ftpUtil.downloadFile("license.jpg", localEnterpriseDocumentInfoPath+"/", FtpUtil.baseLocation+FtpUtil.UPLOAD_PATH_CREDIT_APPLY+"/"+"TJM10000109");
		
		ftpUtil.ftpLogOut();
		
		//uploadFileExt(new File("F:\\aa7c2b8f77beb251c2156b32a56ee78f.jpg"),"/enterprise_account");
		//String orgUrl = uploadStreamExt("123.png",new FileInputStream(new File("F:\\20160524164934.png")) ,UPLOAD_PATH_ORG_REGISTER);
		//System.out.println(orgUrl);
//		String src = "http://image.fangcang.com/upload/images/titanjr/org_register/2016-05-14/1463209387148145575-50.jpg";
//		System.out.println(src);
//		System.out.println(clearImgSize(src));
//		System.out.println(addImgSize(src,60));
	}
}
