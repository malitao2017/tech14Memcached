/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 下午6:10:03 
 * @author malitao
 * @version 
 */
package m2.m23releaseMVC;

/** 
 *  
 * 创建日期：2015年12月18日 下午6:10:03 
 * @author malitao
 */
public class MemcachedSupport {

	private int MEMCACHED_DETAIL = MemcachedConstant.MEMCACHED_GOODSDETAIL;
	
	public boolean setDetailData(String key, Object value) {
		return MemcachedUtil.getInstance().set(MEMCACHED_DETAIL, key, value);
	}
	
	public Object getDetailData(String key) {
		return MemcachedUtil.getInstance().get(MEMCACHED_DETAIL, key);
	}
	
	public boolean deleteDetailData(String key) {
		return MemcachedUtil.getInstance().delete(MEMCACHED_DETAIL,key);
	}
}
