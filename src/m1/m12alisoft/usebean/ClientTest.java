/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 上午10:33:31 
 * @author malitao
 * @version 
 */
package m1.m12alisoft.usebean;

import java.util.ArrayList;
import java.util.List;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

import m1.bean.TestBean;

/** 
 *  
 * 创建日期：2015年12月18日 上午10:33:31 
 * @author malitao
 */
public class ClientTest {  
    
    @SuppressWarnings("unchecked")  
    public static void main(String[] args) {  
        ICacheManager<IMemcachedCache> manager;  
        manager = CacheUtil.getCacheManager(IMemcachedCache.class,  
                MemcachedCacheManager.class.getName());  
//        manager.setConfigFile("memcached.xml");  
        manager.setConfigFile("m1/m12alisoft/memcached.xml");  
        manager.start();  
        try {  
            IMemcachedCache cache = manager.getCache("mclient_0");  
  
            TestBean bean=new TestBean();  
            bean.setName("name1");  
            bean.setAge(25);  
            cache.put("bean", bean);  
            TestBean beanClient=(TestBean)cache.get("bean");  
            System.out.println(beanClient.getName());  
              
            List<TestBean> list=new ArrayList<TestBean>();  
            list.add(bean);  
            cache.put("beanList", list);  
            List<TestBean> listClient=(List<TestBean>)cache.get("beanList");  
            if(listClient.size()>0){  
                TestBean bean4List=listClient.get(0);  
                System.out.println(bean4List.getName());  
            }  
        } finally {  
            manager.stop();  
        }  
    }  
  
}   