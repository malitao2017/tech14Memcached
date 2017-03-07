/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 下午6:06:04 
 * @author malitao
 * @version 
 */
package m2.m23releaseMVC;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/** 
 *  
 * 创建日期：2015年12月18日 下午6:06:04 
 * @author malitao
 */
public class MemcachedUtil {
	private int MEMCACHED_SERVER_NUM = 4;
	private SockIOPool[] pools = new SockIOPool[MEMCACHED_SERVER_NUM];
	private MemCachedClient[] mcs = new MemCachedClient[MEMCACHED_SERVER_NUM];
	private final String[] poolNames = new String[] { "GOODSDETAIL_POOL", "ARTICLEDETAIL_POOL", "COMMENTDETAIL_POOL", "OTHERDETAIL_POOL" };
	private static MemcachedUtil instance;
	private MemcachedUtil() {
		this.init();
	}
	// 单例
	public static MemcachedUtil getInstance() {
		if (MemcachedUtil.instance == null) {
			synchronized (MemcachedUtil.class) {
				if (MemcachedUtil.instance == null) {
					MemcachedUtil.instance = new MemcachedUtil();
				}
			}
		}
		return MemcachedUtil.instance;
	}
	
	public Object get(int index, String key) {
		return this.mcs[index].get(key);
	}
	
	public boolean set(int index, String key, Object value) {
		return this.mcs[index].set(key, value);
	}
	
	public boolean delete(int index, String key) {
		return this.mcs[index].delete(key);
	}
	public MemCachedClient getMemCachedClient(int index) {
		return this.mcs[index];
	}
	
	public void init() {
		String[] servers = {"127.0.0.1:11211","200.200.203.55:11211","200.200.203.54:11211","200.200.203.53:11211"}; 
		Integer[] weights = {3,2,2,3};
		for (int i = 0; i < MEMCACHED_SERVER_NUM; ++i) {
			this.pools[i] = SockIOPool.getInstance(poolNames[i]);
			this.pools[i].setServers(servers);
			this.pools[i].setWeights(weights);
			this.pools[i].setInitConn(10);
			this.pools[i].setMinConn(10);
			this.pools[i].setMaxConn(1000);
			this.pools[i].setMaxIdle(1000*60*60);
//			this.pools[i].setMaxBusyTime(maxBusyTime);
			this.pools[i].setMaintSleep(60);
			this.pools[i].setNagle(false);
			this.pools[i].setSocketTO(60);
			this.pools[i].setSocketConnectTO(0);
//			this.pools[i].setFailover(ifFailOver);
//			this.pools[i].setFailback(ifFailback);
//			this.pools[i].setAliveCheck(ifAliveCheck);
			this.pools[i].initialize();
			this.mcs[i] = new MemCachedClient(poolNames[i]);
		}
	}
}
