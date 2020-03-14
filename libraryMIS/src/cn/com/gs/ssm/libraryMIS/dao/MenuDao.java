package cn.com.gs.ssm.libraryMIS.dao;

import java.util.List;

import cn.com.gs.ssm.libraryMIS.model.Menu;

public interface MenuDao {
	Menu getMenuById(Integer id);
	List<Menu> getMenuByPid(Integer pid);
	//TODO
	List<Menu> getMenuByIds(String ids);
}
