package com.free.platform.container;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.Log4jConfigurer;

import com.free.platform.base.utils.ConfigFileUtil;

public class Server {
	private static volatile boolean running = true;
	
	static{
		long refreshInterval = 4000;
		String fileName = "log4j.properties";
		String location = ConfigFileUtil.findFile(fileName);
		if(location==null){
			location = ConfigFileUtil.findFile("config/"+fileName);
			if(location==null){
				location = ClassUtils.getDefaultClassLoader().getResource("config/"+fileName).getFile();
			}
		}
		try {
			Log4jConfigurer.initLogging(location, refreshInterval);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Logger logger = LoggerFactory.getLogger(Server.class);
		try {
			final AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			ctx.registerShutdownHook();
			Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                	ctx.stop();
                	ctx.close();
                	synchronized (Server.class) {
                        running = false;
                        Server.class.notify();
                    }
                }
            });
			logger.info("Service start success!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
	        System.exit(1);
		}
		synchronized (Server.class) {
            while (running) {
                try {
                	Server.class.wait();
                } catch (Throwable e) {
                }
            }
        }
	}
}
