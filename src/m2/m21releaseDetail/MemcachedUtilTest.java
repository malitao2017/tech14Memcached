/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 下午5:52:52 
 * @author malitao
 * @version 
 */
package m2.m21releaseDetail;

import org.junit.Assert;
import org.junit.Test;

import m2.bean.UserBean;

/** 
 *  
 * 创建日期：2015年12月18日 下午5:52:52 
 * @author malitao
 */
public class MemcachedUtilTest {

	@Test
	public void testMemcached() {
		MemcachedUtil.put("hello", "world", 60);
		String hello = (String) MemcachedUtil.get("hello");
		Assert.assertEquals("world", hello);
		System.out.println(hello);
		
		for(int i = 0; i < 10; ++i) {//10000000
			UserBean userBean = new UserBean("Jason" + i, "123456-" + i);
			MemcachedUtil.put("user" + i, userBean, 60);
			Object obj = MemcachedUtil.get("user" + i);
			Assert.assertEquals(userBean, obj);
			System.out.println(((UserBean)obj).getUsername()+":"+((UserBean)obj).getPassword());
		}
	}
}
