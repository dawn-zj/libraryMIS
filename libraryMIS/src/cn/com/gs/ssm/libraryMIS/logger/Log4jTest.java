package cn.com.gs.ssm.libraryMIS.logger;

import org.apache.log4j.Logger;

public class Log4jTest {
	private static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		logger.error("error1...");
	}
}
