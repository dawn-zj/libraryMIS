package cn.com.gs.common.util.zxing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.FileUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class BarcodeUtil {
	/**
	 * 生成二维码图像
	 *
	 * @throws WriterException
	 * @throws IOException
	 */
	public static byte[] genBarcodeImage(String content) {
		int width = 200; // 图像宽度
		int height = 200; // 图像高度
		String format = "jpg";// 图像类型
		try {
			BarcodeFormat bf = BarcodeFormat.QR_CODE;

			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			/**
			 * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码 encode(String
			 * contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?>
			 * hints) contents:条形码/二维码内容 format：编码类型，如 条形码，二维码 等 width：码的宽度 height：码的高度
			 * hints：码内容的编码类型 BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
			 * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
			 */

			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, bf, width, height, hints);// 生成矩阵
			ByteArrayOutputStream bous = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, format, bous);// 输出图像
			return bous.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		byte[] bytes = genBarcodeImage("https://www.baidu.com");
		FileUtil.storeFile(Constants.FILE_OUT_PATH + "barcodeImage.jpg", bytes);
		System.out.println("制作完成");
	}
}
