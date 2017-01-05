package com.fangcang.titanjr.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.fangcang.util.StringUtil;


/**
 * 上传图片生成不同尺寸图用到的流
 * 
 * @author luoqinglong
 * @2016年5月14日
 */
public class ImageIOExtUtil {
	/**
	 * image流 转成 inputsteam
	 * @param bufferedImage
	 * @param suffix 后缀名
	 * @return
	 */
	public static InputStream createInputStream(BufferedImage bufferedImage,String suffix) {
		InputStream newInputStream = null;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream imOut;
		if(!StringUtil.isValidString(suffix)){
			suffix = "jpg";
		}
		try {
			imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(bufferedImage, suffix, imOut);
			newInputStream = new ByteArrayInputStream(bs.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newInputStream;
	}

	/**
	 * 生成可重复读取流
	 * 
	 * @return
	 * @throws IOException
	 */
	public static InputStream createCycleInputStream(InputStream old)
			throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BufferedInputStream br = new BufferedInputStream(old);
		byte[] b = new byte[1024];
		for (int c = 0; (c = br.read(b)) != -1;) {
			bos.write(b, 0, c);
		}
		b = null;
		br.close();

		InputStream bis = new ByteArrayInputStream(bos.toByteArray());

		return bis;
	}
	
	/**
	 * 获取文件名后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName){
		Pattern pattern = Pattern.compile("(.+)\\.(\\w+)$");
		Matcher m = pattern.matcher(fileName);
		String suffix = "";
		if(m.matches()){
			suffix = m.group(2);
		}
		return suffix;
	}
	
	public static void main(String[] agr){
		//System.out.println(getFileSuffix("01741befb92b42142376b8149dd24f7b.jpg"));
	}
	/***
	 * 去除url中的尺寸编号，如果去掉"-50",:2016-05-14/1463209387148145575-50.jpg
	 * @param imgUrl
	 * @return
	 */
	public static String clearImgSize(String imgUrl){
		String  reg = "-\\d+(\\.\\w+)$";
		return imgUrl.replaceAll(reg, "$1");
	}
	/**
	 * 给地址加上尺寸编号，只有【sizeType大于10】时，才处理
	 * @param imgUrl
	 * @param sizeType
	 * @return
	 */
	public static String addImgSize(String imgUrl,int sizeType){
		
		if(sizeType>=10){
			String tempString  = clearImgSize(imgUrl);
			String  reg = "(\\.\\w+)$";
			return tempString.replaceAll(reg, "-"+sizeType+"$1");
		}else{
			return imgUrl;
		}
	}
}
