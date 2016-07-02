package com.fangcang.titanjr.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

/**
 * 图片
 * @author luoqinglong
 * @2016年5月5日
 */
public class ImageResizer {

	private final BufferedImage source;

	private int sourceWidth;
	private int sourceHeight;

	private int targetWidth;
	private int targetHeight;
	private boolean crop;
	private int cropPosition;
	private boolean upscale;
	private BufferedImage watermark;
	private int watermarkPosition;
	private int watermarkOffsetX;
	private int watermarkOffsetY;

	private int scaledWidth;
	private int scaledHeight;
	private int destinationWidth;
	private int destinationHeight;
	private int positionX;
	private int positionY;
	private BufferedImage destination;

	public ImageResizer(BufferedImage source) {
		this.source = source;
		sourceWidth = source.getWidth();
		sourceHeight = source.getHeight();
	}

	private void computePhotoSize() {
		float widthScale = (float) targetWidth / sourceWidth;
		float heightScale = (float) targetHeight / sourceHeight;

		if ((sourceWidth < targetWidth || sourceHeight < targetHeight)
				&& upscale == false) {
			// 不需要缩放的情况
			if (sourceWidth < targetWidth && sourceHeight < targetHeight) {
				// 高和宽不需要截取
				destinationWidth = scaledWidth = sourceWidth;
				destinationHeight = scaledHeight = sourceHeight;
			} else if ((sourceWidth < targetWidth && targetWidth <= 0 && upscale == false)
					|| (sourceHeight < targetHeight && targetWidth <= 0 && upscale == false)) {
				destinationWidth = scaledWidth = sourceWidth;
				destinationHeight = scaledHeight = sourceHeight;
			} else if (sourceWidth < targetWidth) {
				if (crop) {
					// 把高截掉
					destinationWidth = scaledWidth = sourceWidth;
					scaledHeight = sourceHeight;
					destinationHeight = targetHeight;
				} else {
					// 缩放高度
					destinationHeight = scaledHeight = targetHeight;
					destinationWidth = scaledWidth = (int) (sourceWidth * heightScale);
				}
			} else if (sourceHeight < targetHeight) {
				if (crop) {
					// 把宽截掉
					destinationWidth = targetWidth;
					scaledWidth = sourceWidth;
					destinationHeight = scaledHeight = sourceHeight;
				} else {
					// 缩放宽度
					destinationWidth = scaledWidth = targetWidth;
					destinationHeight = scaledHeight = (int) (sourceHeight * widthScale);
				}
			}
		} else {
			// 需要缩放的情况
			if (targetWidth > 0 && targetHeight <= 0) {
				// 缩放宽度，高度不做限制
				destinationWidth = scaledWidth = targetWidth;
				destinationHeight = scaledHeight = (int) (sourceHeight * widthScale);
			} else if (targetWidth <= 0 && targetHeight > 0) {
				// 缩放高度，宽度不做限制
				destinationWidth = scaledWidth = (int) (sourceWidth * heightScale);
				destinationHeight = scaledHeight = targetHeight;
			} else {
				// 高度和宽度都有限制
				if (crop == false) {
					// 不需要裁切
					if (widthScale > heightScale) {
						// 缩放高度
						destinationHeight = scaledHeight = targetHeight;
						destinationWidth = scaledWidth = (int) (sourceWidth * heightScale);
					} else {
						// 缩放宽度
						destinationWidth = scaledWidth = targetWidth;
						destinationHeight = scaledHeight = (int) (sourceHeight * widthScale);
					}
				} else {
					// 需要裁切
					if (widthScale > heightScale) {
						// 缩放宽度
						destinationWidth = scaledWidth = targetWidth;
						destinationHeight = scaledHeight = (int) (sourceHeight * widthScale);
						if (destinationHeight > targetHeight) {
							destinationHeight = targetHeight;
						}
					} else {
						// 缩放高度
						destinationHeight = scaledHeight = targetHeight;
						destinationWidth = scaledWidth = (int) (sourceWidth * heightScale);
						if (destinationWidth > targetWidth) {
							destinationWidth = targetWidth;
						}
					}
				}
			}
		}
		if (cropPosition == 0) {
			// 裁切头部和底部
			positionX = (destinationWidth - scaledWidth) / 2;
			positionY = (destinationHeight - scaledHeight) / 2;
		} else if (cropPosition < 0) {
			// 裁切底部
			positionX = (destinationWidth - scaledWidth) / 2;
			positionY = 0;
		} else if (cropPosition > 0) {
			// 裁切头部
			positionX = (destinationWidth - scaledWidth) / 2;
			positionY = destinationHeight - scaledHeight;
		}
	}

	public BufferedImage resize() {
		if (destination != null) {
			return destination;
		}
		computePhotoSize();
		destination = new BufferedImage(destinationWidth, destinationHeight,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = destination.createGraphics();
		Image newImage = source;
		// 使用Image.SCALE_SMOOTH设置为平滑地缩放
		if (scaledWidth != sourceWidth || scaledHeight != sourceHeight) {
			newImage = source.getScaledInstance(scaledWidth, scaledHeight,
					Image.SCALE_SMOOTH);
		}
		g.drawImage(newImage, positionX, positionY, null);
		addWatermark(g);
		g.dispose();
		return destination;
	}

	/**
	 * 加水印
	 * 
	 * @param g
	 */
	private void addWatermark(Graphics g) {
		if (watermark != null) {
			int watermarkWidth = watermark.getWidth();
			int watermarkHeight = watermark.getHeight();
			int watermarkPositionX = 0;
			int watermarkPositionY = 0;
			switch (watermarkPosition) {
			case 0:
				// 图片中心
				watermarkPositionX = (int) (((float) destinationWidth - watermarkWidth) / 2)
						+ watermarkOffsetX;
				watermarkPositionY = (int) (((float) destinationHeight - watermarkHeight) / 2)
						+ watermarkOffsetY;
				break;
			case 1:
				// 右上角
				watermarkPositionX = (int) (destinationWidth - watermarkWidth)
						+ watermarkOffsetX;
				watermarkPositionY = watermarkOffsetY;
				break;
			case 3:
				// 左下角
				watermarkPositionX = watermarkOffsetX;
				watermarkPositionY = (int) (destinationHeight - watermarkHeight)
						+ watermarkOffsetY;
				break;
			case 4:
				// 左上角
				watermarkPositionX = watermarkOffsetX;
				watermarkPositionY = watermarkOffsetY;
				break;
			default:
				// 如果不是0-4，那就是水印位置为右下角
				watermarkPositionX = (int) (destinationWidth - watermarkWidth)
						+ watermarkOffsetX;
				watermarkPositionY = (int) (destinationHeight - watermarkHeight)
						+ watermarkOffsetY;
			}
			g.drawImage(watermark, watermarkPositionX, watermarkPositionY, null);
		}
	}

	public void setTargetSize(int targetWidth, int targetHeight) {
		this.targetWidth = targetWidth;
		this.targetHeight = targetHeight;
	}

	public void setCrop(boolean crop) {
		this.crop = crop;
	}

	public void setCropPosition(int cropPosition) {
		this.cropPosition = cropPosition;
	}

	public void setUpscale(boolean upscale) {
		this.upscale = upscale;
	}

	public void setWatermark(BufferedImage watermark) {
		this.watermark = watermark;
	}

	public void setWatermarkPosition(int watermarkPosition) {
		this.watermarkPosition = watermarkPosition;
	}

	public void setWatermarkOffset(int watermarkOffsetX, int watermarkOffsetY) {
		this.watermarkOffsetX = watermarkOffsetX;
		this.watermarkOffsetY = watermarkOffsetY;
	}
	/**
	 * 转成目标尺寸的图片流
	 * @param inputStream 图片流
	 * @param targetWidth 目标尺寸宽
	 * @param targetHeight 目标尺寸高
	 * @return
	 * @throws IOException
	 */
	public static InputStream sizeImgInputStream(InputStream inputStream,int targetWidth,int targetHeight,String suffix)throws IOException {
			BufferedImage source = ImageIO.read(inputStream);
			
			ImageResizer resizer = new ImageResizer(source);
			resizer.setTargetSize(targetWidth, targetHeight);
			resizer.setUpscale(true);
			resizer.setCrop(false);
			resizer.setCropPosition(-1);
			BufferedImage bufferedImage = resizer.resize();
			return ImageIOExtUtil.createInputStream(bufferedImage,suffix);
			
	}
	
	public static void main(String[] arg) throws IOException{
//		byte[] buffer = new byte[1024];
//		InputStream inputStream = new FileInputStream(new File("F:\\a8773912b31bb051be533b24317adab44aede043.jpg"));
//		InputStream newInputStream = ImageIOExtUtil.createCycleInputStream(inputStream);
//		sizeImgInputStream(newInputStream,200,200);
//		newInputStream.reset();
//		sizeImgInputStream(newInputStream,200,200);
		
		BufferedImage sourceImage = ImageIO.read(new File("F:\\a8773912b31bb051be533b24317adab44aede043.jpg"));

		ImageResizer resizer = new ImageResizer(sourceImage);
		resizer.setTargetSize(200,500);
		resizer.setCrop(false);
		resizer.setUpscale(true);

		BufferedImage destinationImage = resizer.resize();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(destinationImage, "jpg", out);
		File cacheFile = new File("F:\\a8773912b31bb051be533b24317adab44aede043-200.jpg");
		FileUtils.writeByteArrayToFile(cacheFile, out.toByteArray());
		
	}
	 
	 
}
