package cn.com.gs.ssm.libraryMIS.controller.book;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.date.DateUtil;
import cn.com.gs.common.util.logger.LoggerUtil;
import cn.com.gs.ssm.libraryMIS.util.CommonUtil;
import cn.com.gs.ssm.libraryMIS.util.ExportUtil;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.com.gs.ssm.libraryMIS.aop.LibraryLog;
import cn.com.gs.ssm.libraryMIS.logger.Log4jTest;
import cn.com.gs.ssm.libraryMIS.model.Book;
import cn.com.gs.ssm.libraryMIS.service.IBookService;
import cn.com.gs.ssm.libraryMIS.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private IBookService bookService;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 分页查询图书
	 * */
	@RequestMapping("bookList")//传当前页
	@LibraryLog(optype = "selectBook")
	public ModelAndView bookList(Page<Book> page, Book bk,ModelAndView mv){
		page = bookService.searchBookByPage(page, bk);
		mv.addObject("book",bk);
		mv.addObject("page",page);
		mv.setViewName("book/bookList");
		return mv;
	}
	
	@RequestMapping("/{bookId}/toUpdateBook.do")
	public String toUpdateBook(@PathVariable("bookId") String bookId, Model model){
		Book book = bookService.getBookById(bookId);
		model.addAttribute("book",book);
		return "updateBook";
	}
	
	@RequestMapping("toAddBook")
 	public String toAddBook(){
		return "book/bookAdd";
	}

	@RequestMapping("toEditBook")
	public String toEditBook(String id){
		Book book = bookService.getBookById(id);
		request.setAttribute("book", book);
		return "book/bookEdit";
	}

	@RequestMapping("/insertBook.do")
	@ResponseBody
	public HashMap<String, Object> insertBook(Book bk){
		bookService.insertBook(bk);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		return resultMap;
	}

	@RequestMapping("/editBook.do")
	@ResponseBody
	public HashMap<String, Object> editBook(Book bk){
		bookService.updateBook(bk);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		return resultMap;
	}

	/**
	 * 导出所有图书
	 * @author zhangjuan
	 */
	@ResponseBody
	@RequestMapping(value = "/exportAllToExcel")
	public void exportTemplateExcel(HttpServletResponse response){
		List<Book> bookList = bookService.getBook();

		String thss[] = new String[]{"名称","作者","价格","出版社"};
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(int i = 0; i < thss.length; i++){
			map.put(i, thss[i]);
		}
		HSSFWorkbook workBook = ExportUtil.exportBookToExcel(bookList, map);
		String fileName = "bookInfo_" + DateUtil.getTimeStamp() + ".xls";
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		OutputStream o;
		try {
			o = response.getOutputStream();
			workBook.write(o);
			workBook.close();
			o.close();
		} catch (IOException e) {
			LoggerUtil.errorLog(e.getMessage(), e);
		}

	}

/*	
	@RequestMapping("/{pageIndex}/selectBook.do")//传当前页
	@LibraryLog(optype = "selectBook")
	public ModelAndView selectBook(@PathVariable("pageIndex") Integer pageIndex,Book bk,ModelAndView mv){
		logger.error("查询图书");
		
		 * 分页
		 * （当前页-1）*页面条数		start = 当前页-1;start*pageSize
		
		//bk不为空：即搜索框有查询条件
		Integer curPage = 1;
		bk.getPage().setPageIndex(curPage);
		if(pageIndex!=null){
			curPage = pageIndex;
			bk.getPage().setPageIndex(pageIndex);
		}
		
		bk.getPage().setStart((bk.getPage().getPageIndex()-1)*CommonUtil.pageSize);
		bk.getPage().setPageSize(CommonUtil.pageSize);
		
		在此处set 当前页、总记录、总页数(再写一个page类)
		int totalCount = pageService.selectCountBook(bk);
		int totalPage = totalCount%CommonUtil.pageSize==0?totalCount/CommonUtil.pageSize:totalCount/CommonUtil.pageSize+1;
		Page page = new Page();
		page.setPageIndex(curPage);
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		
		System.out.println("开始Book："+bk.getPage().getStart()+"  页面显示条数："+
		bk.getPage().getPageSize()+"  当前页："+curPage+"  总记录："+totalCount+"  总页数："+totalPage);
		//搜索框结束
		
		分页结束
		
		List<?> books = bookService.selectBook(bk);
		mv.addObject("books",books);
		mv.addObject("page",page);
		mv.setViewName("showBook");
		return mv;
	}
	
	@RequestMapping("/{bookId}/deleteBook.do")
	public ModelAndView deleteBook(@PathVariable("bookId") String bookId,Page page,ModelAndView mv){
		bookService.deleteBook(bookId);
		List books = bookService.selectBook(null);
		mv.addObject("books",books);
		mv.setViewName("showBook");
		return mv;
	}
	
	@RequestMapping("/batchDeleteBooks.do")
	public void batchDeleteBooks(HttpServletRequest request, HttpServletResponse response){
		try {
			String bookIds = request.getParameter("bookIds");
			//System.out.println("前台id=="+bookIds);
			String[] strs = bookIds.split(",");
			int flag=-1;
			for(int i=0;i<strs.length;i++){
				//System.out.println("循环id=="+strs[i]);
				flag = bookService.batchDeleteBooks(strs[i]);
				//System.out.println("flag=="+flag);
			}
			
			System.out.println("影响记录："+flag);
			if(flag>0){
				response.getWriter().print(1);//删除成功
			}else{
				response.getWriter().print(0);//删除失败
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//添加图书时，对图书编号进行ajax校验
	@RequestMapping("/insertToSelectBookById.do")
	public void insertToSelectBookById(HttpServletRequest request, HttpServletResponse response){
		try {
			String bookId = request.getParameter("bookId");
			Book book = bookService.selectBookById(bookId);
			//返回给ajax处理
			if(book!=null){
				//response.getWriter().print(1);
				response.getWriter().write(1);
			}else{
				response.getWriter().print(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	*/
}
