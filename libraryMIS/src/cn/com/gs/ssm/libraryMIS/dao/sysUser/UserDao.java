package cn.com.gs.ssm.libraryMIS.dao.sysUser;

import cn.com.gs.ssm.libraryMIS.model.User;

public interface UserDao {
	public User getUserByAccount(String account);
}
