package cn.com.gs.common.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *	图片去底色工具类
 */
public class ImageUtil {

	/**
	 * 去图片背景色
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
	 * 去图片背景色
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
		BufferedImage bi = ImageIO.read(is);
		Image image = (Image) bi;
		ImageIcon imageIcon = new ImageIcon(image);
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
		g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
		int alpha = 0;
		for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
			for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
				int rgb = bufferedImage.getRGB(j2, j1);

				int R = (rgb & 0xff0000) >> 16;
				int G = (rgb & 0xff00) >> 8;
				int B = (rgb & 0xff);
				if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
					rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
				}

				bufferedImage.setRGB(j2, j1, rgb);

			}
		}

		g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
		return bufferedImage;
	}

	public static void main(String[] args) {
		byte[] photoData = transferAlpha("D:/cash.png");
		FileUtil.storeFile("D:/cash1.png", photoData);
		System.out.println("图片处理完毕！");
	}
}
