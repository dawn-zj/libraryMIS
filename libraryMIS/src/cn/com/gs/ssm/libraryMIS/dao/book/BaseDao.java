package cn.com.gs.ssm.libraryMIS.dao.book;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {
	/*
	这是非常传统的mybatis用法：交给spring管理sqlSessionFactory和sqlSession，
	在每个dao层注入sqlSession（包含所有的增删改查操作），由sqlSession调用各API
	eg:
		public List selectBook(Book bk) {
			//mapper文件的namespace+SQL语句的id
			List books = sqlSession.selectList("com.ssm.libraryMIS.model.BookMapper.selectBook",bk);
			return books;
		}
		每个语句都要指定statement和参数，比较麻烦
		
		优化：动态代理，遵循一定的约定，只使用Mapper接口利用动态代理实例化dao层
	*/
	@Autowired
	protected SqlSessionTemplate sqlSession;
	
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
}
