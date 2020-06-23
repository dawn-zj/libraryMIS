package cn.com.gs.common.util.logger;

public class LoggerUtil {
	private static DebugLogger debugLog;

	/**
	 * 记录调试日志
	 *
	 * @param
	 */
	private static Object objLockDebug = new Object();


	public static void debug(String msg) {
		//开启debug
		if (true) {
			// 判断debugLog对象是否存在，不存在则创建
			synchronized (objLockDebug) {
				if (debugLog == null) {
					//TODO
					LoggerRes res = new LoggerRes();
					res.setLogFile(LoggerConfig.getLogPath() + "debug.log");
					res.setLoggerName("debug");

					try {
						debugLog = new DebugLogger(res);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			if (debugLog != null) {
				debugLog.Log(msg);
			}
		}

	}
}
