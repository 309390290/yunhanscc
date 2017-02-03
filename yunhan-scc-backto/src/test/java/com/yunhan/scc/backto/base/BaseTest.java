package com.yunhan.scc.backto.base;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**  
 * @Title: BaseTest.java 
 * @Package com.yunhan.scc.trading.system 
 * 测试基础类
 * @author wangtao
 * @date 2015-7-13 下午12:32:10 
 * @version V0.1  
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/spring-core.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public abstract class BaseTest {

}
