package cn.com.gs.ssm.libraryMIS.service;

import java.util.List;

import cn.com.gs.ssm.libraryMIS.model.Button;
import cn.com.gs.ssm.libraryMIS.model.Menu;

public interface IUserService {
	public List<Menu> getMenuByRoleId(Integer roleId);
	public List<Button> getButtonByRoleId(Integer roleId);
	//public Integer login(String account, String password);
}
