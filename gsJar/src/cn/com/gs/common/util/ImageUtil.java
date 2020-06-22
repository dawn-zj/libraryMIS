package cn.com.gs.common.util;

import cn.com.gs.common.exception.NetGSRuntimeException;
import cn.com.gs.common.resource.ErrCode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *	图片去底色工具类
 */
public class ImageUtil {

	/**
	 * 处理图片红字颜色透明度
	 * @param bs 图片数据
	 * @param alpha 透明度 最大255（数值越大，颜色越红）
	 *              convertFontColor(file.getBytes(),(int) (templateVO.getTransparency()*2.55));
	 * @return
	 */
	public static byte[] convertFontColor(byte[] bs, int alpha) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bs);
			BufferedImage bi = ImageIO.read(bais);
			BufferedImage tmp = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			for (int i = 0, width = bi.getWidth(); i < width; i++) {
				for (int j = 0, height = bi.getHeight(); j < height; j++) {
					int rgb = bi.getRGB(i, j);

					byte[] d = HexUtil.int2Byte(rgb);
					int a = d[0] & 0xff; //0xff=255
					int r = d[1] & 0xff;
					int g = d[2] & 0xff;
					int b = d[3] & 0xff;
					if (r == 255 && g == 0 && b == 0) {
						d[0] = (byte) (alpha & 0xff);
						rgb = HexUtil.byte2Int(d);
					}

					tmp.setRGB(i, j, rgb);
				}
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(tmp, "png", baos);

			return baos.toByteArray();
		} catch (Exception e) {
			throw new NetGSRuntimeException(ErrCode.GEN_PHOTO_ERROR,
					"convert photo font color error, " + e.getMessage());
		}
	}


	/**
	 * 调整图片为透明背景
	 * @param sourcePhotoData 原图片字节数据
	 * @return
	 */
	public static byte[] transferAlpha(byte[] sourcePhotoData) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		InputStream is;
		try {
			is = new ByteArrayInputStream(sourcePhotoData);
			// 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
			
			BufferedImage bufferedImage = handlePhoto(is);
			//ImageIO.write(bufferedImage, "png", new File(targetPhotoPath));// 直接输出文件
			ImageIO.write(bufferedImage, "png", byteArrayOutputStream);//放到输出流中
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
 
		}
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 调整图片为透明背景
	 * @param sourcePhotoPath 原图片路径
	 * @return
	 */
	public static byte[] transferAlpha(String sourcePhotoPath) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		File file = new File(sourcePhotoPath);
		InputStream is;
		try {
			is = new FileInputStream(file);
			
			BufferedImage bufferedImage = handlePhoto(is);
			ImageIO.write(bufferedImage, "png", byteArrayOutputStream);//放到输出流中
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
 
		}
		return byteArrayOutputStream.toByteArray();
	}
	
	public static BufferedImage handlePhoto(InputStream is) throws Exception {
		//1.读取输入流，将图片加载到内存区，（可转为ImageIcon，方便得到图像的宽高）
		BufferedImage bi = ImageIO.read(is);
		Image image = (Image) bi;
		ImageIcon imageIcon = new ImageIcon(image);

		//2.新创建一个同等宽高的画像，准备对内存区的图片进行操作
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		// 获取画笔
		Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
		// 绘制Image的图片
		g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
		// 图片透明度
		int alpha = 0;
		// 外层遍历是Y轴的像素
		for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
			// 内层遍历是X轴的像素
			for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
				int rgb = bufferedImage.getRGB(j2, j1);
				/*
				* <<，有符号左移位，将运算数的二进制整体左移指定位数，低位用0补齐。
				* >>，有符号右移位，将运算数的二进制整体右移指定位数，整数高位用0补齐，负数高位用1补齐（保持负数符号不变）。
				* >>>，无符号右移位，不管正数还是负数，高位都用0补齐（忽略符号位）。
				*
				* Java中每个RGB像素所占的位数为8
				* */
				int R = (rgb & 0xff0000) >> 16;
				int G = (rgb & 0xff00) >> 8;
				int B = (rgb & 0xff);
				if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
					rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
				}

				bufferedImage.setRGB(j2, j1, rgb);

			}
		}

		// 绘制新RGB的图片
		g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
		return bufferedImage;
	}

	public static void main(String[] args) {
		byte[] photoData = transferAlpha("F:/temp/seal.png");
		FileUtil.storeFile("F:/temp/sealConventBGColor.png", photoData);
		System.out.println("图片处理完毕！");
	}
}
