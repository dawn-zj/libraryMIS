package cn.com.gs.ssm.libraryMIS.dao.book;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.com.gs.ssm.libraryMIS.model.Book;

/**
 * 1.传统mybatis提供的API
 * 2.Mapper接口动态代理
 * 只需要开发接口和映射文件，遵循一定的规则：
 * （1）mapper.xml文件的namespace名字=Mapper接口类路径
 * （2）mapper.xml文件的sql的id值=Mapper接口中的方法名称
 * 
 * 在spring里applicationContext.xml配置mapper扫描器
 * */
public interface BookDao{

	/**
	 * 增
	 * @param bk
	 */
	void insertBook(Book bk);

	/**
	 * 删
	 * @param bookId
	 */
	void deleteBook(String bookId);

	/**
	 * 批量删
	 * @param bookIds
	 * @return
	 */
	int batchDeleteBooks(String bookIds);

	/**
	 * 改
	 * @param bk
	 */
	void updateBook(Book bk);

	/**
	 * 根据id查
	 * @param id
	 * @return
	 */
	Book getBookById(String id);

	/**
	 * 查询所有
	 *
	 * @return
	 */
	List<Book> getBook();

	/*该方法有两个或以上的参数，一定要加@Param，不然mybatis识别不了*/
	/**
	 * 分页搜索
	 * @param book
	 * @param start
	 * @param end
	 * @return
	 */
	List<Book> searchBookByPage(@Param("book") Book book, @Param("start") int start, @Param("end") int end);

	/**
	 * 获取总条数
	 * @param bk
	 * @return
	 */
	int searchTotal(Book bk);
	
	
	
	
	

}
