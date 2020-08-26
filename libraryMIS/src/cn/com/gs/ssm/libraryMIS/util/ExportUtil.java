package cn.com.gs.ssm.libraryMIS.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import cn.com.gs.ssm.libraryMIS.model.Book;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出工具类
 */
public class ExportUtil {

	/**
	 * 导出为excel
	 * HSSFWorkbook：当数据很多的时候可以多建几个sheet，一张表最大支持65536行数据，256列
	 * XSSFWorkbook：一张表最大支持1048576行，16384列
	 * @param dataset 数据集，与表头信息顺序保持一致
	 * @param map 表头信息
	 * @return
	 */
	public static HSSFWorkbook exportBookToExcel(List<?> dataset, Map<Integer,String> map){
		// 一个工作表最大数据量，默认1000条
		int maxNum = 1000;

		// 创建一个工作簿，即excel文件
		HSSFWorkbook workBook = new HSSFWorkbook();

		// 计算一共要分成几张工作表
		int dataSize = dataset.size();
		int sheetNum = dataSize % maxNum == 0 ? dataSize / maxNum : (dataSize / maxNum) + 1;

		// 遍历创建sheet表，分批量导出到sheet表
		for(int n = 0; n < sheetNum; n++){
			// 1.创建sheet工作表
			HSSFSheet sheet = workBook.createSheet("表" + (n + 1));
			
			// 设置单元格的宽度(0:表示第一行的第一个单元格，1：第一行的第二个单元格)
			sheet.setColumnWidth((short) 0, 5000);
			sheet.setColumnWidth((short) 1, 10000);
			sheet.setColumnWidth((short) 2, 5000);
			sheet.setColumnWidth((short) 3, 5000);
			sheet.setColumnWidth((short) 4, 5000);
			sheet.setColumnWidth((short) 5, 5000);
			sheet.setColumnWidth((short) 6, 5000);
			sheet.setColumnWidth((short) 7, 5000);
			
			// 2.创建第一行：表头行
			HSSFRow row = sheet.createRow((short) 0);
			// 表头列数=信息个数
			int cellNum = map.size();
			HSSFCell cell[] = new HSSFCell[cellNum];
			for (int i = 0; i < cellNum; i++) {
				cell[i] = row.createCell(i);
				cell[i].setCellValue(map.get(i));
			}
			
			int start = n * maxNum;
			int curNum = 0;
			int rowIndex = 1;
			//创建行，从1开始
			if(dataset != null && dataset.size() > 0){
				//每个表maxNum行数据
				for(int i = start; i < dataset.size(); i++){
					if(curNum == maxNum){
						break;
					}
					Book book = (Book)dataset.get(i);
					HSSFRow dataRow = sheet.createRow(rowIndex);
					insertData(book, dataRow, cellNum);
					
					curNum++;
					rowIndex++;
				}
			}
		}
		return workBook;
	}
	
	public static void insertData(Book book, HSSFRow dataRow, int cellNum){
		// 创建列
		HSSFCell data[] = new HSSFCell[cellNum];
		for (int j = 0; j < cellNum; j++) {
			data[j] = dataRow.createCell(j);
		}
		// 每一个单元格赋值
		data[0].setCellValue(book.getName());
		data[1].setCellValue(book.getAuthor());
		data[2].setCellValue(book.getPrice());
		data[3].setCellValue(book.getPublish());

	}
}
