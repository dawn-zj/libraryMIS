package cn.com.gs.common.util;

import java.io.*;
import java.util.Properties;

import cn.com.gs.common.exception.NetGSRuntimeException;
import cn.com.gs.common.resource.ErrCode;
import cn.com.gs.common.util.logger.LoggerUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfUtil {

	public void pdfToWord() {

	}

	public static void main(String[] args) {
		try {
			String pdfFile = "D:/1.pdf";
			PDDocument doc = PDDocument.load(new File(pdfFile));
			int pagenumber = doc.getNumberOfPages();
			pdfFile = pdfFile.substring(0, pdfFile.lastIndexOf("."));
			String fileName = pdfFile + ".doc";
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(fileName);
			Writer writer = new OutputStreamWriter(fos, "UTF-8");
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setSortByPosition(true);// 排序
			stripper.setStartPage(1);;// 设置转换的开始页
			stripper.setEndPage(pagenumber);// 设置转换的结束页
			stripper.writeText(doc, writer);
			writer.close();
			doc.close();
			System.out.println("pdf转换word成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据模板和内容生成新的pdf
	 * @param pdfTemplateData
	 * @param pro
	 */
	public static byte[] genPdfByTemplate(byte[] pdfTemplateData, Properties pro)  throws Exception{
		PdfReader reader = null;
		PdfStamper stamper = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			// 读取pdf模板
			reader = new PdfReader(pdfTemplateData);

			// 获取模板文本域
			stamper = new PdfStamper(reader, bos);
			AcroFields fields = stamper.getAcroFields();

			// 使用中文字体 使用 AcroFields填充值的不需要在程序中设置字体，在模板文件中设置字体为中文字体 Adobe 宋体 std L
			fields.addSubstitutionFont(BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false));

			// 遍历给文本域赋值
			for (Object o : pro.keySet()) {
				// 插入的数据都为字符类型
				fields.setField((String) o, pro.getProperty((String) o));
			}

			// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.setFormFlattening(true);
			stamper.close();
			stamper = null;

			return bos.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (stamper != null)
					stamper.close();
			} catch (Exception e) {
			}

			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 给pdf的指定页码和坐标添加图片
	 * @param pdfData pdf文件
	 * @param jpgData 图片
	 * @param pageNumber 页码
	 * @param x x坐标
	 * @param y y坐标
	 * @param w 图片宽度
	 * @param h 图片高度
	 */
	public static byte[] pdfAddImage(byte[] pdfData, byte[] jpgData, int pageNumber, float x, float y, float w, float h) throws Exception{
		PdfReader reader = null;
		PdfStamper stamper = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			reader = new PdfReader(pdfData);
			stamper = new PdfStamper(reader, baos);

			// 获取pdf总页数
			int totalPageNum = reader.getNumberOfPages();
			if (pageNumber > totalPageNum || pageNumber < 1)
				throw new NetGSRuntimeException(ErrCode.PAGE_NUM_OVER_LIMIT_IN_PDF, "page number over limit in pdf");

			// 获取该页码的内容
			PdfContentByte content = stamper.getOverContent(pageNumber);
			if (content == null)
				throw new NetGSRuntimeException("page number " + pageNumber + " out of range");

			// TODO Image相关内容
//			content.addImage(img);
			stamper.close();

			return baos.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (stamper != null)
					stamper.close();
			} catch (Exception e) {
			}

			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
			}
		}
	}
}
