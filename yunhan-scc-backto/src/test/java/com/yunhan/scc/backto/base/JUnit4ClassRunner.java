package com.yunhan.scc.backto.base;


import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * @Title: JUnit4ClassRunner.java
 * @Package com.yunhan.scc.trading.system
 * 用一句话描述该文件做什么
 * @author wangtao
 * @date 2015-7-13 下午12:15:58
 * @version V0.1
 */
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {

	static {
		try {
			Log4jConfigurer
					.initLogging("classpath:config/properties/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

	public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}
}
