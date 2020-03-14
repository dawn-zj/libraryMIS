package cn.com.gs.ssm.libraryMIS.service;

import java.util.List;

import cn.com.gs.ssm.libraryMIS.model.Menu;
import cn.com.gs.ssm.libraryMIS.model.vo.MenuVO;

public interface IMenuService {
	Menu getMenuById(Integer id);
	List<MenuVO> getMenuByPid(Integer pid) throws Exception;
}
