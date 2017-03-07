/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 上午10:24:23 
 * @author malitao
 * @version 
 */
package m1.m11release;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/** 
 *  
 * 创建日期：2015年12月18日 上午10:24:23 
 * @author malitao
 */
public class MyCache {  
    public static void main(String[] args) {  
        MemCachedClient client=new MemCachedClient();  
        String [] addr ={"127.0.0.1:11211"};  
        Integer [] weights = {3};  
        SockIOPool pool = SockIOPool.getInstance();  
        pool.setServers(addr);  
        pool.setWeights(weights);  
        pool.setInitConn(5);  
        pool.setMinConn(5);  
        pool.setMaxConn(200);  
        pool.setMaxIdle(1000*30*30);  
        pool.setMaintSleep(30);  
        pool.setNagle(false);  
        pool.setSocketTO(30);  
        pool.setSocketConnectTO(0);  
        pool.initialize();  
          
//      String [] s  =pool.getServers();  
        
        /*
         *  20151218
         *  去掉以下的两行代码，会报报错，他们是过期的函数
         *  或者换成低版本 2.6.3换成2.5.3
         */
        client.setCompressEnable(true);  
        client.setCompressThreshold(1000*1024);  
          
//      将数据放入缓存  
        client.set("test2","test2");  
          
//      将数据放入缓存,并设置失效时间  
        Date date=new Date(2000000);  
        client.set("test1","test1", date);  
          
//      删除缓存数据  
//      client.delete("test1");  
          
//      获取缓存数据  
        String str =(String)client.get("test1");  
        System.out.println(str);  
    }  
}  
