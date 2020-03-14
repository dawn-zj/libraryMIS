package cn.com.gs.ssm.libraryMIS.service.impl.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.gs.ssm.libraryMIS.dao.MenuDao;
import cn.com.gs.ssm.libraryMIS.model.Menu;
import cn.com.gs.ssm.libraryMIS.model.vo.MenuVO;
import cn.com.gs.ssm.libraryMIS.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{
	@Resource
	private MenuDao menuDao;
	
	
	@Override
	public Menu getMenuById(Integer id) {
		return null;
	}

	/**
	 * 查询父级菜单
	 * 
	 * @param pid
	 * @return
	 * @throws Exception 
	 */
	public List<MenuVO> getMenuByPid(Integer pid) throws Exception {
		if(pid!=null){
			List<Menu> menuList = menuDao.getMenuByPid(pid);
			List<MenuVO> menuVOList = new ArrayList<MenuVO>();
			for(Menu menu : menuList){
				MenuVO menuVO = new MenuVO();
				BeanUtils.copyProperties(menu, menuVO);
				// 校验信息
				menuVOList.add(menuVO);
			}
			return menuVOList;
		}else{
			return new ArrayList<MenuVO>();
		}
	}

}
