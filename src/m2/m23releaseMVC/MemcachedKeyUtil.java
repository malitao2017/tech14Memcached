/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 下午6:05:23 
 * @author malitao
 * @version 
 */
package m2.m23releaseMVC;

/** 
 *  
 * 创建日期：2015年12月18日 下午6:05:23 
 * @author malitao
 */
public class MemcachedKeyUtil {
	private static final String GOODS_KEY_PREFIX = "goods_";
	
	public static String getGoodsKey(long goodsId) {
		return GOODS_KEY_PREFIX + goodsId;
	}
}