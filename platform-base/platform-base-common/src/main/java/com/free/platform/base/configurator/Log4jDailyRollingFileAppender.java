package com.free.platform.base.configurator;

import java.io.File;

import com.free.platform.base.utils.ConfigFileUtil;
import com.free.platform.base.utils.StringUtils;
import org.apache.log4j.DailyRollingFileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jDailyRollingFileAppender extends DailyRollingFileAppender {

	private static final Logger logger = LoggerFactory.getLogger(Log4jDailyRollingFileAppender.class);

	/**
	 * 生产环境将日志输出到{server}/logs/下面
	 */
	public void setFile(String file) {
		String filePath = file;
		String key = "${"+ ConfigFileUtil.SERVER_HOME+"}";
		String serverHome = ConfigFileUtil.getServerHome();
		String[] priKeys = new String[]{key,"./"};
		if (StringUtils.isNotBlank(serverHome)) {
			for(String pri:priKeys){
				if(file.indexOf(pri)==0){
					StringBuilder builder = new StringBuilder().append(serverHome).append(File.separator).append(file.substring(file.indexOf(pri)+1));
					filePath = builder.toString();
					break;
				}
			}
		}
		super.setFile(filePath);
		logger.info("########################Output log file location：" + fileName);
	}
	
}
