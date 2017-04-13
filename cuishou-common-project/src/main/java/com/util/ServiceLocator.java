package com.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vo.Config;

/**
 * 
 * 
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2008
 * 
 * @author haoxz11
 * @created Mar 7, 2008 11:21:21 AM
 * @version $Id: ServiceLocator.java 89112 2015-06-13 08:45:20Z zhjy $
 */
public class ServiceLocator {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ServiceLocator.class);

	private static ClassPathXmlApplicationContext context;

	static {
		long startTime = System.currentTimeMillis();
		String[] configs;
		if (System.getProperty("spring.configfile") != null) {
			configs = StringUtils.split(System.getProperty("spring.configfile"), ",");
		} else {
			configs = Config.getStringArray("sys.spring.configfile");
		}

		context = new ClassPathXmlApplicationContext(configs);
		long elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("Spring initialization completed in {} ms", elapsedTime);
	}

	public static void start() {
		context.start();
	}

	public static void init() {
		// 已经在static中初始化了
	}

	public static void destroy() {
		if (context instanceof ConfigurableApplicationContext) {
			((ConfigurableApplicationContext) context).close();
		}
	}

	public static <T> T getService(String beanName, Class<T> clazz) {
		return context.getBean(beanName, clazz);
	}

	public static <T> T getService(Class<T> clazz) {
		return context.getBean(clazz);
	}
}