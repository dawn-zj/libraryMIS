package cn.com.gs.ssm.libraryMIS.service;

import java.util.List;

import cn.com.gs.ssm.libraryMIS.model.Role;
import cn.com.gs.ssm.libraryMIS.model.vo.RoleVO;

public interface IRoleService {
	List<RoleVO> getRole();
	RoleVO getRoleById(Integer id);
}
