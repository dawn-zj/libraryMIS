package cn.com.gs.ssm.libraryMIS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gs.ssm.libraryMIS.model.Book;
import cn.com.gs.ssm.libraryMIS.service.IPageService;

@Controller
public class PageController {
	@Autowired
	private IPageService pageService;
	public IPageService getPageService() {
		return pageService;
	}
	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}
	
	//查询总记录
	@RequestMapping("selectCountBook.do")
	public int selectCountBook(Book bk){
		return pageService.selectCountBook(bk);
	}
	
}
