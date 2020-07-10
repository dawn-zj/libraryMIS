package cn.com.gs.common.util;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.exception.NetGSRuntimeException;
import cn.com.gs.common.resource.ErrCode;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;

public class ConfigUtil {
	private static ConfigUtil instance;
	private Configuration properties;
	private FileBasedConfigurationBuilder<FileBasedConfiguration> builder;

	public static synchronized ConfigUtil getInstance() {
		if (instance == null) {
			ConfigUtil config = new ConfigUtil();
			try {
				config.load();
			} catch (Exception e) {
				e.printStackTrace();
				throw new NetGSRuntimeException(ErrCode.LOAD_CONF_ERROR, e.getMessage());
			}
			instance = config;
		}
		return instance;
	}


	private synchronized void load() {
		try {
			// 读取属性文件 .properties
			Parameters params = new Parameters();
			builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class);
			builder.configure(params.properties().setFileName(Constants.CONF_PATH + "config.properties").setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
			properties = builder.getConfiguration();
		} catch (Exception e) {
			throw new NetGSRuntimeException(ErrCode.LOAD_CONF_ERROR, e.getMessage());
		}
	}

	public synchronized void reload() {
		try {
			instance.load();
		} catch (Exception e) {
			throw new NetGSRuntimeException(ErrCode.RELOAD_CONF_ERROR, e.getMessage());
		}
	}

	public Long getTimeoutTime() {
		return properties.getLong("timeout.time", 1800000L);
	}

	public static void main(String[] args) {
		Long time = ConfigUtil.getInstance().getTimeoutTime();
		System.out.println(time);
	}
}
