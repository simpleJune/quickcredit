package com.free.credit.api.common.utils;

import com.free.platform.base.utils.ConfigFileUtil;
import com.free.platform.base.utils.StringUtils;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


/**
 * @类描述：自动加载properties文件类
 * @创建人：林继丰
 * @创建时间：2017/9/30 11:19
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class ReloadAbleProperty extends PropertySourcesPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ReloadAbleProperty.class);
    private volatile Map<String, PropertiesConfiguration> builderNameMapConfiguration = new ConcurrentHashMap<>();
    private static final String UTF_8 = "UTF-8";
    private volatile static CompositeConfiguration compositeConfiguration = new CompositeConfiguration();


    /**
     * 初始化配置
     *
     * @param files
     */
    public synchronized void initializeConfig(List<File> files) {
        try {
            logger.info("loading properties........file size : {}", files.size());
            for (File file : files) {
                logger.info("loading properties file:{}........", file.getName());
                Parameters params = new Parameters();
                final ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> reloadbuilder = new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(
                        PropertiesConfiguration.class);
                reloadbuilder.configure(params.fileBased().setEncoding(UTF_8).setFile(file));
                logger.info("setting properties to auto save........");
                reloadbuilder.setAutoSave(true);
                PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(reloadbuilder.getReloadingController(),
                        null, 1, TimeUnit.MINUTES);
                trigger.start();
                PropertiesConfiguration propertiesConfiguration = (PropertiesConfiguration) reloadbuilder.getConfiguration();
                builderNameMapConfiguration.put(reloadbuilder.toString(), propertiesConfiguration);
                compositeConfiguration.addConfiguration(propertiesConfiguration);
                addEventListener(reloadbuilder);
            }
            logger.info("loading success........");
        } catch (ConfigurationException e) {
            logger.error("ConfigurationException in ReloadAbleProperty.initializeConfig()", e);
        }
    }

    /**
     * 添加监听
     *
     * @param reloadbuilder
     */
    private void addEventListener(ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> reloadbuilder) {
        reloadbuilder.addEventListener(ConfigurationBuilderEvent.RESET, new EventListener<ConfigurationBuilderEvent>() {
            public void onEvent(ConfigurationBuilderEvent event) {
                try {
                    logger.info("------------ReloadAbleProperty Event change:-------------" + event);
                    String builderName = event.getSource().toString();
                    PropertiesConfiguration configuration = builderNameMapConfiguration.get(builderName);
                    if (configuration != null) {
                        compositeConfiguration.removeConfiguration(configuration);
                        configuration.clear();
                        configuration = (PropertiesConfiguration) event.getSource().getConfiguration();
                        compositeConfiguration.addConfiguration(configuration);
                        builderNameMapConfiguration.put(builderName, configuration);
                    }
                } catch (ConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setLocation(Resource location) {
        List<File> files = new ArrayList<>();
        try {
            files.add(location.getFile());
            initializeConfig(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.setLocation(getResource(location));
    }

    @Override
    public void setLocations(Resource[] locations) {
        List<File> files = new ArrayList<>();
        for (int i = 0; i < locations.length; i++) {
            try {
                locations[i] = getResource(locations[i]);
                files.add(locations[i].getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        initializeConfig(files);
        super.setLocations(locations);
    }

    private Resource getResource(Resource location) {
        String path = ConfigFileUtil.findFile(location.getFilename());
        if (StringUtils.isNotBlank(path)) {
            Resource resource = new FileSystemResource(path);
            if (resource.exists()) {
                location = null;
                logger.info(String.format("########################Loading resource file: %s", path));
                return resource;
            }
        }
        return location;
    }

    public static String getProperty(String key) {
        return String.valueOf(compositeConfiguration.getProperty(key));
    }

}
