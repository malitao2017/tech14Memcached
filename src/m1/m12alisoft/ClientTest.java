/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 上午10:33:31 
 * @author malitao
 * @version 
 */
package m1.m12alisoft;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

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
            cache.put("key", "value");  
            System.out.println("输出的内容："+cache.get("key"));  
        } finally {  
            manager.stop();  
        }  
    }  
  
}  