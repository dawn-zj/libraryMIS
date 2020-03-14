package cn.com.gs.ssm.libraryMIS.dao;

import org.springframework.stereotype.Repository;

import cn.com.gs.ssm.libraryMIS.dao.book.BaseDao;
import cn.com.gs.ssm.libraryMIS.model.Book;

@Repository
public class PageDao extends BaseDao{
	public int selectCountBook(Book bk){
		Integer page = (Integer)sqlSession.selectOne("com.ssm.libraryMIS.model.PageMapper.selectCountBook", bk);
		return page;
	}
}
