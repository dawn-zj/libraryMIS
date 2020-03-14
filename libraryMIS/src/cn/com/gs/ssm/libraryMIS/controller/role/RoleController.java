package cn.com.gs.ssm.libraryMIS.controller.role;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.StringUtil;
import cn.com.gs.ssm.libraryMIS.controller.BaseController;
import cn.com.gs.ssm.libraryMIS.model.vo.ButtonVO;
import cn.com.gs.ssm.libraryMIS.model.vo.MenuVO;
import cn.com.gs.ssm.libraryMIS.model.vo.RoleVO;
import cn.com.gs.ssm.libraryMIS.service.IMenuService;
import cn.com.gs.ssm.libraryMIS.service.impl.button.ButtonServiceImpl;
import cn.com.gs.ssm.libraryMIS.service.impl.role.RoleServiceImpl;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController{
	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private IMenuService menuService;
	@Autowired
	private ButtonServiceImpl buttonService;
	@Autowired
	private HttpServletRequest request;
	
	//查询所有角色
	@RequestMapping(value = "roleList")
	public String searchRole(){
		List<RoleVO> list = roleService.getRole();
		request.setAttribute("list", list);
		return "role/roleList";
	}
	
	/**
	 * 角色显示跳转
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="toEditRoleMenu")
	public String toEditRoleMenu(Integer id) throws Exception{
		String[] menuArr = null;
		String[] buttonArr = null;
		List<MenuVO> secondMenu = null;
		List<MenuVO> thirdMenu = null;
		List<MenuVO> firstMenu = menuService.getMenuByPid(-1);
		
		if(firstMenu != null) {
			for(MenuVO firstMenuVO : firstMenu) {
				secondMenu = menuService.getMenuByPid(firstMenuVO.getId());
				int grandsonLength = firstMenuVO.getGrandsonLength();
				if(secondMenu != null) {
					for(MenuVO secondMenuVO : secondMenu) {
						thirdMenu = menuService.getMenuByPid(secondMenuVO.getId());
						if(thirdMenu != null) {
							for(MenuVO thirdMenuVO : thirdMenu) {
								//查询对应的按钮
								List<ButtonVO> button = buttonService.getButtonByMenuId(thirdMenuVO.getId());
								thirdMenuVO.setButton(button);
							}
						}
						secondMenuVO.setChild(thirdMenu);
						grandsonLength += secondMenuVO.getChildLength();
					}
				}
				firstMenuVO.setChild(secondMenu);
				firstMenuVO.setGrandsonLength(grandsonLength);
			}
		}
		
		request.setAttribute("firstMenu", firstMenu);
		
		RoleVO role = roleService.getRoleById(id);
		if(role != null) {
			//已授权的菜单id
			String menuIds = role.getMenuId();
			if(StringUtil.isNotBlank(menuIds)) {
				menuArr = menuIds.split(Constants.SPLIT_1);
			}
			
			//已授权的按钮id
			String buttonIds = role.getButtonId();
			if(StringUtil.isNotBlank(buttonIds)) {
				buttonArr = buttonIds.split(Constants.SPLIT_1);
			}
			if(menuArr != null) {
				for(MenuVO first : firstMenu) {
					for(String menuId : menuArr) {
						menuId = menuId.trim();
						if(first.getId() == (Long.parseLong(menuId))) {
							first.setChecked(true);
						}
					}
					
					List<MenuVO> secondList = first.getChild();
					for(MenuVO second : secondList) {
						for(String menuId : menuArr) {
							menuId = menuId.trim();
							if(second.getId() == (Long.parseLong(menuId))) {
								second.setChecked(true);
							}
						}
						
						List<MenuVO> thirdList = second.getChild();
						for(MenuVO third : thirdList) {
							for(String menuId : menuArr) {
								menuId = menuId.trim();
								if(third.getId() == (Long.parseLong(menuId))) {
									third.setChecked(true);
								}
							}
						
							List<ButtonVO> btnList = third.getButton();
							if(btnList != null && btnList.size() != 0 && buttonArr!= null) {
								for(ButtonVO btn : btnList) {
									for(String btnId : buttonArr) {
										btnId = btnId.trim();
										if(btn.getId() == (Long.parseLong(btnId))) {
											btn.setChecked(true);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		request.setAttribute("role", role);
		return "role/roleEdit";
	}
	
	/**
	 * 角色授权(前台ajaxSubmit，后台ModelAndView，需要JackSon的jar)
	 * @param id
	 * @param menuIds
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="editRoleMenu")
	public ModelAndView editRoleMenu(Integer id, String [] menuIds, String [] buttonIds) throws Exception{
		try {
			String menuId = "";
			if (menuIds != null) {
				menuId = Arrays.toString(menuIds).replace("[", "").replace("]", "");
			}
			String buttonId = "";
			if (buttonIds != null) {
				buttonId = Arrays.toString(buttonIds).replace("[", "").replace("]", "");
			}
			roleService.roleMenu(id, menuId, buttonId);
		} catch (Exception e) {
			throw e;
		}
		return getModelAndView(getSuccMap("角色授权成功"));
	}
}
