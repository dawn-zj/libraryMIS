package cn.com.gs.ssm.libraryMIS.model;

public class Button extends Base{
	private String name;
	private String url;
	private Integer menuId;
	private String buttonCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getButtonCode() {
		return buttonCode;
	}
	public void setButtonCode(String buttonCode) {
		this.buttonCode = buttonCode;
	}
}
