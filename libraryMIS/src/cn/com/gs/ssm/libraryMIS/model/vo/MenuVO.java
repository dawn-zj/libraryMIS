package cn.com.gs.ssm.libraryMIS.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单
 */
public class MenuVO extends BaseVO{

	private Integer pid;
	private String name;
	private String url;
	private String menuCode;
	private String img;

	// 扩展
	private List<MenuVO> child = new ArrayList<MenuVO>(); // 子菜单
	private List<ButtonVO> button = new ArrayList<ButtonVO>();
	private int childLength; // 二级菜单
	private int grandsonLength; // 三级菜单
	private boolean checked = false;
	private boolean sealMac;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<MenuVO> getChild() {
		return child;
	}

	public void setChild(List<MenuVO> child) {
		this.child = child;
		if(child != null) {
			setChildLength(child.size());
		}
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isSealMac() {
		return sealMac;
	}

	public void setSealMac(boolean sealMac) {
		this.sealMac = sealMac;
	}

	public Integer getChildLength() {
		return childLength;
	}

	public void setChildLength(Integer childLength) {
		this.childLength = childLength;
	}

	public List<ButtonVO> getButton() {
		return button;
	}

	public void setButton(List<ButtonVO> button) {
		this.button = button;
	}

	public int getGrandsonLength() {
		return grandsonLength;
	}

	public void setGrandsonLength(int grandsonLength) {
		this.grandsonLength = grandsonLength;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public void setChildLength(int childLength) {
		this.childLength = childLength;
	}

}
