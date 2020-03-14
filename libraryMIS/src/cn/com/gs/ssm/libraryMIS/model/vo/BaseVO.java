package cn.com.gs.ssm.libraryMIS.model.vo;

import java.io.Serializable;

public class BaseVO implements Serializable{
	private Integer id;
	private Integer generateTime;
	private Integer updateTime;
	private String mac;
	
	private String generateTimeCn;
	private String updateTimeCn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Integer generateTime) {
		this.generateTime = generateTime;
	}
	public Integer getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getGenerateTimeCn() {
		return generateTimeCn;
	}
	public void setGenerateTimeCn(String generateTimeCn) {
		this.generateTimeCn = generateTimeCn;
	}
	public String getUpdateTimeCn() {
		return updateTimeCn;
	}
	public void setUpdateTimeCn(String updateTimeCn) {
		this.updateTimeCn = updateTimeCn;
	}
	
}
