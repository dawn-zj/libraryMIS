package cn.com.gs.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

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
	public static void genPdfByTemplate(byte[] pdfTemplateData, Properties pro) {
		//new PdfReader(pdfTemplateData);
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
	public static void pdfAddImage(byte[] pdfData, byte[] jpgData, int pageNumber, float x, float y, float w, float h) {
		
	}
}
