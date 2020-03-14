package cn.com.gs.ssm.libraryMIS.service.impl.sysUser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.gs.ssm.libraryMIS.dao.ButtonDao;
import cn.com.gs.ssm.libraryMIS.dao.MenuDao;
import cn.com.gs.ssm.libraryMIS.dao.RoleDao;
import cn.com.gs.ssm.libraryMIS.model.Button;
import cn.com.gs.ssm.libraryMIS.model.Menu;
import cn.com.gs.ssm.libraryMIS.model.Role;
import cn.com.gs.ssm.libraryMIS.service.IUserService;

@Service
public class SysUserServiceImpl implements IUserService{
	@Resource
	private RoleDao roleDao;
	@Resource
	private MenuDao menuDao;
	@Resource
	private ButtonDao buttonDao;
	
	/**
	 * 根据角色获取菜单列表
	 * */
	@Override
	public List<Menu> getMenuByRoleId(Integer roleId) {
		Integer menuId = null;
		Menu menu = null;
		List<Menu> menuList = new ArrayList<Menu>();
		//获取角色信息
		Role role = roleDao.getRoleById(roleId);
		
		//获取菜单信息
		String menuIds = role.getMenuId();
		String[] menuIdArr = menuIds.split(",");
		for(String tempId : menuIdArr){
			tempId = tempId.trim();
			menuId = Integer.parseInt(tempId);
			menu = menuDao.getMenuById(menuId);
			menuList.add(menu);
		}
		
		return menuList;
	}

	/**
	 * 根据角色获取按钮列表
	 * */
	@Override
	public List<Button> getButtonByRoleId(Integer roleId) {
		Integer buttonId = null;
		Button button = null;
		List<Button> buttonList = new ArrayList<Button>();
		//获取角色信息
		Role role = roleDao.getRoleById(roleId);
		
		//获取按钮信息
		String buttonIds = role.getButtonId();
		String[] buttonIdArr = buttonIds.split(",");
		for(String tempId : buttonIdArr){
			buttonId = Integer.parseInt(tempId);
			button = buttonDao.getButtonById(buttonId);
			buttonList.add(button);
		}
		return buttonList;
	}

		
}
