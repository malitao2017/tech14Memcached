/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 下午5:55:35 
 * @author malitao
 * @version 
 */
package m2.m22releaseSpring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danga.MemCached.MemCachedClient;

import m2.bean.UserBean;

/** 
 *  
 * 创建日期：2015年12月18日 下午5:55:35 
 * @author malitao
 */
public class MemcachedSpringTest {

	private MemCachedClient cachedClient;
	
	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("m2/m22releaseSpring/spring.xml");//com/luo/config/beans.xml
		cachedClient = (MemCachedClient)context.getBean("memcachedClient");
	}
	
	@Test
	public void testMemcachedSpring() {
		UserBean user = new UserBean("luo", "hi");
		cachedClient.set("user", user);
		UserBean cachedBean = (UserBean)user;
		Assert.assertEquals(user, cachedBean);
		
		System.out.println(((UserBean)cachedBean).getUsername()+":"+((UserBean)cachedBean).getPassword());
	}
}
