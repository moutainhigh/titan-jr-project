package com.fangcang.titanjr.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.fangcang.titanjr.common.util.rsa.RSAUtil;

/**
 * 文件
 * 
 * @author luoqinglong
 * @date 2016年11月30日
 */
public class FileHelp {
	public static void main(String[] arg) {
		System.out.println(getFileName("data/fdfdf/22131.jpg"));
		//deleteFile("G:\\apache-tomcat-8.0.21-8030\\webapps\\titanjr-dubbo-server\\WEB-INF\\classes\\tmp\\credit_apply\\TJM10000109");
	}

	/**
	 * 压缩文件
	 * 
	 * @param filePath
	 *            文件物理位置，如:/data/file/bbbb
	 * @param destFileName
	 *            目标文件名，如aaa.zip
	 * @return
	 */
	public static File zipFile(String filePath, String destFileName) {
		File target = null;
		File source = new File(filePath);
		if (source.exists()) {
			target = new File(source.getParent(), destFileName);
			if (target.exists()) {
				target.delete(); // 删除旧的文件
			}
			FileOutputStream fos = null;
			ZipOutputStream zos = null;
			try {
				fos = new FileOutputStream(target);
				zos = new ZipOutputStream(new BufferedOutputStream(fos));
				// 添加对应的文件Entry
				addEntry("/", source, zos);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				closeQuietly(zos, fos);
			}
		}
		return target;
	}

	/**
	 * 解压文件
	 * 
	 * @param filePath
	 *            压缩文件路径
	 */
	public static void unzipFile(String filePath) {
		File source = new File(filePath);
		if (source.exists()) {
			ZipInputStream zis = null;
			BufferedOutputStream bos = null;
			try {
				zis = new ZipInputStream(new FileInputStream(source));
				ZipEntry entry = null;
				while ((entry = zis.getNextEntry()) != null
						&& !entry.isDirectory()) {
					File target = new File(source.getParent(), entry.getName());
					if (!target.getParentFile().exists()) {
						// 创建文件父目录
						target.getParentFile().mkdirs();
					}
					// 写入文件
					bos = new BufferedOutputStream(new FileOutputStream(target));
					int read = 0;
					byte[] buffer = new byte[1024 * 10];
					while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
						bos.write(buffer, 0, read);
					}
					bos.flush();
				}
				zis.closeEntry();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				closeQuietly(zis, bos);
			}
		}
	}

	/**
	 * 扫描添加文件Entry
	 * 
	 * @param base
	 *            基路径
	 * 
	 * @param source
	 *            源文件
	 * @param zos
	 *            Zip文件输出流
	 * @throws IOException
	 */
	private static void addEntry(String base, File source, ZipOutputStream zos)
			throws IOException {
		// 按目录分级，形如：/aaa/bbb.txt
		String entry = base + source.getName();
		if (source.isDirectory()) {
			for (File file : source.listFiles()) {
				// 递归列出目录下的所有文件，添加文件Entry
				addEntry(entry + "/", file, zos);
			}
		} else {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				byte[] buffer = new byte[1024 * 10];
				fis = new FileInputStream(source);
				bis = new BufferedInputStream(fis, buffer.length);
				int read = 0;
				zos.putNextEntry(new ZipEntry(entry));
				while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
					zos.write(buffer, 0, read);
				}
				zos.closeEntry();
			} finally {
				closeQuietly(bis, fis);
			}
		}
	}

	public static void close(Closeable... closeables) throws IOException {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					closeable.close();
				}
			}
		}
	}

	/**
	 * 关闭一个或多个流对象
	 * 
	 * @param closeables
	 *            可关闭的流对象列表
	 */
	public static void closeQuietly(Closeable... closeables) {
		try {
			close(closeables);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除文件
	 */
	public static void deleteFile(String fileName) {
		deleteFile(new File(fileName));
	}

	public static void deleteFile(File file) {
		if(file==null){
			return ; 
		}
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
				file.delete();// 删除文件夹
			}
		}
	}

	/**
	 * 输出到文件
	 * 
	 * @param content
	 *            内容
	 * @param fileName
	 *            目标文件
	 */
	public static void writeToFile(byte[] content, File fileName) {
		// 创建输出流
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(fileName);
			// 写入数据
			outStream.write(content);
			// 关闭输出流
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从文件读字节
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static byte[] readFromFile(File fileName) throws Exception {

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			byte[] buf = new byte[(int) fileName.length()];
			fis.read(buf);
			fis.close();
			return buf;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/****
	 * 融数rsa加密文件,用于上传
	 * 
	 * @param srcFileName
	 *            源文件
	 * @param destFileName
	 *            加密后的目标文件
	 * @return
	 * @throws Exception
	 */
	public static void encryptRSFile(String srcFileName, String destFileName)
			throws Exception {
		encryptRSFile(new File(srcFileName), destFileName);
	}

	public static void encryptRSFile(File srcFile, String destFileName)
			throws Exception {
		byte[] srcFileByte = readFromFile(srcFile);
		byte[] destFileByte = RSAUtil.encryptByPublicKey(srcFileByte,
				RSAUtil.UPLOAD_FILE_RSA_PUBLIC_KEY);
		writeToFile(destFileByte, new File(destFileName));
	}
	/**
	 * 只截取文件名
	 * @param filePathName   /data/person/123.jpg
	 * @return 123.jpg
	 */
	public static String getFileName(String filePathName){
		return filePathName.substring(filePathName.lastIndexOf("/")+1);
	}
	
}
