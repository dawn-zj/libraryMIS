package cn.com.gs.ssm.libraryMIS.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.gs.ssm.libraryMIS.model.Role;

public interface RoleDao {
	Role getRoleById(Integer id);
	List<Role> getRole();
	//TODO
	int roleMenu(@Param("id") Integer id, @Param("menuIds") String menuIds, @Param("buttonIds") String buttonIds, @Param("update_time") Integer update_time);
}
