package cn.com.gs.common.util.logger;

import cn.com.gs.common.define.Constants;

public class LoggerConfig {
	/**
	 * 日志路径
	 */
	private static String logPath = Constants.LOG_PATH;


	public static String getLogPath() {
		return logPath;
	}

}
