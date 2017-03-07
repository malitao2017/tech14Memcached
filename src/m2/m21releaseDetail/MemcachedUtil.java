/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 下午5:49:37 
 * @author malitao
 * @version 
 */
package m2.m21releaseDetail;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/** 
 *  源于：http://blog.csdn.net/sup_heaven/article/details/32337711
 *  七、注意点 
第一、memcached是在服务器端的内存中缓存对象的，不是缓存或硬盘；
第二、memcached的pool可以关联多个server，
String[] servers = {"10.20.185.12:11001","10.20.185.25:11001"};  
Integer[] weights = {3,7};  
该配置表示30%的缓存在放在第一台服务器，70%的将放在第二台服务器，这样便可以充分利用不同服务器的内存了；
第三、我最困惑的是client是如何得到相应的pool的，后然看了点源码才知道是这样的。client是通过pool的name关联到某个pool的，上面的例子中在SockIOPool pool = SockIOPool.getInstance();  和MemCachedClient client=new MemCachedClient();虽然都没写poolName，但就是新建了一个”default“的pool，然后client关联到了这个”default“的pool。当然我们在新建这两个对象时可以给定具体的poolName。

 * 创建日期：2015年12月18日 下午5:49:37 
 * @author malitao
 */
public class MemcachedUtil {

	/**
	 * memcached客户端单例
	 */
	private static MemCachedClient cachedClient = new MemCachedClient();
	
	/**
	 * 初始化连接池
	 */
	static {
		//获取连接池的实例
		SockIOPool pool = SockIOPool.getInstance();
		
		//服务器列表及其权重
		String[] servers = {"127.0.0.1:11211"};
		Integer[] weights = {3};
		
		//设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);
		
		//设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(10);
		pool.setMinConn(10);
		pool.setMaxConn(1000);
		pool.setMaxIdle(1000*60*60);
		
		//设置连接池守护线程的睡眠时间
		pool.setMaintSleep(60);
		
		//设置TCP参数，连接超时
		pool.setNagle(false);
		pool.setSocketTO(60);
		pool.setSocketConnectTO(0);
		
		//初始化并启动连接池
		pool.initialize();
		
		//压缩设置，超过指定大小的都压缩
//		cachedClient.setCompressEnable(true);
//		cachedClient.setCompressThreshold(1024*1024);
	}
	
	private MemcachedUtil(){
	}
	
	public static boolean add(String key, Object value) {
		return cachedClient.add(key, value);
	}
	
	public static boolean add(String key, Object value, Integer expire) {
		return cachedClient.add(key, value, expire);
	}
	
	public static boolean put(String key, Object value) {
		return cachedClient.set(key, value);
	}
	
	public static boolean put(String key, Object value, Integer expire) {
		return cachedClient.set(key, value, expire);
	}
	
	public static boolean replace(String key, Object value) {
		return cachedClient.replace(key, value);
	}
	
	public static boolean replace(String key, Object value, Integer expire) {
		return cachedClient.replace(key, value, expire);
	}
	
	public static Object get(String key) {
		return cachedClient.get(key);
	}
	
}
