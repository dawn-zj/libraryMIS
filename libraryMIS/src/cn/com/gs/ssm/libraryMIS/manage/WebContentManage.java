package cn.com.gs.ssm.libraryMIS.manage;

import cn.com.gs.common.util.logger.LoggerConfig;
import cn.com.gs.common.util.logger.LoggerUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 定义一些需要在服务器启动就初始化的方法
 * 使用@PostConstruct注解，在bean创建完成后，执行一次@PostConstruct修饰的方法
 */
@Component
public class WebContentManage {

	/**
	 * 初始化一些服务器必要的参数
	 * 如：日志配置、定时器等等
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception {
		loggerInit();
	}


	/**
	 * 配置日志
	 */
	private void loggerInit() {
		try {
			LoggerConfig.init();
			LoggerUtil.debugLog("logger config init ok.");
		} catch (Exception e) {
			System.out.println("logger config init error, " + e.getMessage());
		}
	}
}
