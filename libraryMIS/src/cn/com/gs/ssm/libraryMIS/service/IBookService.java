package cn.com.gs.ssm.libraryMIS.service;

import cn.com.gs.ssm.libraryMIS.model.Book;
import cn.com.gs.ssm.libraryMIS.util.Page;

public interface IBookService {

	/**
	 * 增
	 *
	 * @param bk
	 */
	void insertBook(Book bk);

	/**
	 * 删除
	 * @param bookId
	 */
	void deleteBook(String bookId);

	/**
	 * 批量删除
	 * @param bookIds
	 * @return
	 */
	Integer batchDeleteBooks(String bookIds);

	/**
	 * 改
	 *
	 * @param bk
	 */
	void updateBook(Book bk);

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	Book selectBookById(String id);

	/**
	 * 分页查询图书
	 *
	 * @param page
	 * @param bk
	 * @return
	 */
	Page<Book> searchBookByPage(Page<Book> page,Book bk);

	/**
	 * 查询总记录
	 *
	 * @param bk
	 * @return
	 */
	Integer searchTotal(Book bk);

}
