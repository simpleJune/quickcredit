package com.free.credit.api.common.servlet;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.Log4jConfigurer;
import org.springframework.util.ResourceUtils;
import org.springframework.web.util.Log4jWebConfigurer;
import org.springframework.web.util.ServletContextPropertyUtils;

import com.free.platform.base.utils.ConfigFileUtil;

public class Log4jConfigListener implements ServletContextListener {
	

	public void contextInitialized(ServletContextEvent event) {
		ServletContext  servletContext = event.getServletContext();
		String defaultConfigHome = servletContext.getInitParameter(ConfigFileUtil.CONFIG_HOME);
		String location = servletContext.getInitParameter(Log4jWebConfigurer.CONFIG_LOCATION_PARAM);
		if(defaultConfigHome!=null&&!"".equals(defaultConfigHome)&&System.getProperty(ConfigFileUtil.CONFIG_HOME)==null){
			ConfigFileUtil.initConfigHome(defaultConfigHome);
		}
		
		do{
			if (location.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
				location =location.replace(ResourceUtils.CLASSPATH_URL_PREFIX,"");
				if(defaultConfigHome!=null&&!"".equals(defaultConfigHome)){
					if(location.contains("/")){
						location=location.substring(location.lastIndexOf("/")+1);
					}else if(location.contains("\\")){
						location=location.substring(location.lastIndexOf("\\")+1);
					}
				}
			}else{
				try {
					location =new URL(location).getFile();
					break;
				}catch (MalformedURLException ex) {}
			}
			location = ConfigFileUtil.findFile(location);
		}while(false);
		
		if (location != null) {
			try {
				location = ServletContextPropertyUtils.resolvePlaceholders(location, servletContext);
				servletContext.log("Initializing log4j from [" + location + "]");
				String intervalString = servletContext.getInitParameter(Log4jWebConfigurer.REFRESH_INTERVAL_PARAM);
				if (intervalString != null) {
					try {
						long refreshInterval = Long.parseLong(intervalString);
						Log4jConfigurer.initLogging(location, refreshInterval);
					}
					catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Invalid 'log4jRefreshInterval' parameter: " + ex.getMessage());
					}
				}
				else {
					Log4jConfigurer.initLogging(location);
				}
			}catch (FileNotFoundException ex) {
				throw new IllegalArgumentException("Invalid 'log4jConfigLocation' parameter: " + ex.getMessage());
			}
		}else{
			Log4jWebConfigurer.initLogging(event.getServletContext());
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		Log4jWebConfigurer.shutdownLogging(event.getServletContext());
	}

}
