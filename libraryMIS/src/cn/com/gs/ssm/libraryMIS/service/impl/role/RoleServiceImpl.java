package cn.com.gs.ssm.libraryMIS.service.impl.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.gs.common.util.date.DateUtil;
import cn.com.gs.ssm.libraryMIS.dao.MenuDao;
import cn.com.gs.ssm.libraryMIS.dao.RoleDao;
import cn.com.gs.ssm.libraryMIS.model.Menu;
import cn.com.gs.ssm.libraryMIS.model.Role;
import cn.com.gs.ssm.libraryMIS.model.vo.RoleVO;
import cn.com.gs.ssm.libraryMIS.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	@Resource
	private RoleDao roleDao;
	@Resource
	private MenuDao menuDao;
	
	@Override
	public RoleVO getRoleById(Integer id) {
		Role role = roleDao.getRoleById(id);
		if(role == null)
			return null;
		RoleVO roleVO = new RoleVO();
		BeanUtils.copyProperties(role, roleVO);
		
		return roleVO;
	}

	@Override
	public List<RoleVO> getRole() {
		List<Role> roleList=roleDao.getRole();
		List<RoleVO> roleVOList=new ArrayList<RoleVO>();
		for(Role role:roleList){
			RoleVO roleVO=new RoleVO();
			BeanUtils.copyProperties(role, roleVO);
			roleVOList.add(roleVO);
		}
		return roleVOList;
	}
	
	/**
	 * 授权
	 * @param roleVO
	 * @param menuIds
	 * @throws Exception 
	 */
	public void roleMenu(Integer id, String menuIds, String buttonIds) throws Exception {
		Role role = roleDao.getRoleById(id);
		
		long update_time = DateUtil.getTime();
		role.setUpdateTime((int)update_time);
		role.setMenuId(menuIds);
		int r = roleDao.roleMenu(id, menuIds, buttonIds, (int)update_time);
		if(r == 0)
			throw new Exception("授权失败");
		
	}

}
