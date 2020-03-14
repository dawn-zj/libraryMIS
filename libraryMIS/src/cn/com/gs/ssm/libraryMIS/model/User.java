package cn.com.gs.ssm.libraryMIS.model;

public class User extends Base{
	private String name;
	private String account;
	private String password;
	private Integer roleId;
	private Integer status;		//状态：0启用，1停用
	private Integer failedNum;
	private Integer changePass;
	private Integer companyId;
	
	private String statusCn;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFailedNum() {
		return failedNum;
	}
	public void setFailedNum(Integer failedNum) {
		this.failedNum = failedNum;
	}
	public Integer getChangePass() {
		return changePass;
	}
	public void setChangePass(Integer changePass) {
		this.changePass = changePass;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getStatusCn() {
		return statusCn;
	}
	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", account=" + account + ", password=" + password + ", roleId=" + roleId
				+ ", status=" + status + ", failedNum=" + failedNum + ", changePass=" + changePass + ", companyId="
				+ companyId + ", statusCn=" + statusCn + "]";
	}
	
}
