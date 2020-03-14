package cn.com.gs.ssm.libraryMIS.dao;

import java.util.List;

import cn.com.gs.ssm.libraryMIS.model.Button;

public interface ButtonDao {
	
	Button getButtonById(Integer id);
	
	List<Button> getButtonByUserId(Integer id);
	
	List<Button> getButtonByMenuId(Integer menuId);
}
