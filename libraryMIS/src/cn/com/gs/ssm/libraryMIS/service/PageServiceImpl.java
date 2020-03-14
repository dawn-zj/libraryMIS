package cn.com.gs.ssm.libraryMIS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gs.ssm.libraryMIS.dao.PageDao;
import cn.com.gs.ssm.libraryMIS.model.Book;

@Service
public class PageServiceImpl implements IPageService{
	@Autowired
	private PageDao pageDao;
	public PageDao getPageDao() {
		return pageDao;
	}
	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	
	@Override
	public int selectCountBook(Book bk) {
		return pageDao.selectCountBook(bk);
	}

}
