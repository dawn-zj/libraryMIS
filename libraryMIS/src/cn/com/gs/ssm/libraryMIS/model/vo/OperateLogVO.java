package cn.com.gs.ssm.libraryMIS.model.vo;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.StringUtil;
import cn.com.gs.common.util.date.DateUtil;

public class OperateLogVO extends BaseVO {

	private String account;
	private String clientHost;
	private String opType;
	private String returnCode;
	private String errMsg;

	// 日志文件属性
	private String fileName;
	private String filePath;
	private Long fileSize;
	private Long fileTime;

	//扩展
	private String OpTimeStart;
	private String OpTimeEnd;
	private String fileTimeCn;
		
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getMac() {
		return "";
	}

	public String getOpTimeStart() {
		return OpTimeStart;
	}

	public void setOpTimeStart(String opTimeStart) {
		OpTimeStart = opTimeStart;
	}

	public String getOpTimeEnd() {
		return OpTimeEnd;
	}

	public void setOpTimeEnd(String opTimeEnd) {
		OpTimeEnd = opTimeEnd;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getFileTime() {
		return fileTime;
	}

	public void setFileTime(Long fileTime) {
		this.fileTime = fileTime;
		setFileTimeCn(DateUtil.getDate(fileTime));
	}

	public String getFileTimeCn() {
		return fileTimeCn;
	}

	public void setFileTimeCn(String fileTimeCn) {
		this.fileTimeCn = fileTimeCn;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getName());
		sb.append(" [");
		sb.append("id:");
		sb.append(getId());
		sb.append(" account:");
		sb.append(StringUtil.parseStringWithDefault(account, Constants.DEFAULT_STRING));
		sb.append(" clientHost:");
		sb.append(StringUtil.parseStringWithDefault(clientHost, Constants.DEFAULT_STRING));
		sb.append(" optype:");
		sb.append(StringUtil.parseStringWithDefault(opType, Constants.DEFAULT_STRING));
		sb.append(" optime:");
		sb.append(getGenerateTime());
		sb.append(" returnCode:");
		sb.append(StringUtil.parseStringWithDefault(returnCode, Constants.DEFAULT_STRING));
		sb.append(" errMsg:");
		sb.append(StringUtil.parseStringWithDefault(errMsg, Constants.DEFAULT_STRING));
		sb.append(" mac:");
		sb.append(getMac());
		sb.append("]");

		return sb.toString();
	}

}
