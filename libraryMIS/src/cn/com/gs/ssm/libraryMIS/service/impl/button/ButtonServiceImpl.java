package cn.com.gs.ssm.libraryMIS.service.impl.button;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.gs.ssm.libraryMIS.dao.ButtonDao;
import cn.com.gs.ssm.libraryMIS.model.Button;
import cn.com.gs.ssm.libraryMIS.model.Menu;
import cn.com.gs.ssm.libraryMIS.model.vo.ButtonVO;
import cn.com.gs.ssm.libraryMIS.service.IButtonService;

@Service
public class ButtonServiceImpl implements IButtonService{
	@Resource
	private ButtonDao buttonDao;

	@Override
	public Menu getMenuById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ButtonVO> getButtonByMenuId(Integer menuId) throws Exception {
		List<Button> buttonList = buttonDao.getButtonByMenuId(menuId);
		List<ButtonVO> buttonVOList = new ArrayList<ButtonVO>();
		for(Button button : buttonList) {
			ButtonVO buttonVO = new ButtonVO();
			BeanUtils.copyProperties(button, buttonVO);
			buttonVOList.add(buttonVO);
		}
		return buttonVOList;
	}

}
