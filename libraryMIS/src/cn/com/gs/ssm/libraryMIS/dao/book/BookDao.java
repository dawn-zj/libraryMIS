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
	
	public abstract void insertBook(Book bk);
	
	public abstract void deleteBook(String bookId);
	public abstract int batchDeleteBooks(String bookIds);
	
	public abstract void updateBook(Book bk);

	Book selectBookById(String id);
	/*该方法有两个或以上的参数，一定要加@Param，不然mybatis识别不了*/
	List<Book> searchBookByPage(@Param("book") Book book, @Param("start") int start, @Param("end") int end);
	int searchTotal(Book bk);
	
	
	
	
	

}
