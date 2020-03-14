package cn.com.gs.ssm.libraryMIS.model.vo;

public class RoleVO extends BaseVO{
	private String name;
	private String menuId;
	private String buttonId;
	
	public String getName() {
		return name;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
