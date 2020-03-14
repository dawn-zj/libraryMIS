package cn.com.gs.ssm.libraryMIS.model.vo;

public class ButtonVO extends BaseVO{

	private String name;
	private Integer pid;
	private boolean checked = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
