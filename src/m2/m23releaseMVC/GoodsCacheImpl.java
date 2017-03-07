/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 下午6:10:21 
 * @author malitao
 * @version 
 */
package m2.m23releaseMVC;

import javax.annotation.Resource;

import m2.m23releaseMVC.temp.Goods;
import m2.m23releaseMVC.temp.GoodsDao;

/** 
 *  
 * 创建日期：2015年12月18日 下午6:10:21 
 * @author malitao
 */
public class GoodsCacheImpl extends MemcachedSupport{
	@Resource(name = "goodsDaoImpl")
	private GoodsDao goodsDao;
	
	public Goods selectGoodsById(long goodsId) {
		Goods goods = null;
		String goodsKey = MemcachedKeyUtil.getGoodsKey(goodsId);
		goods = (Goods) getDetailData(goodsKey);
		if (goods == null) {
			goods = goodsDao.selectGoodsById(goodsId, false);
			if (goods != null) {
				setDetailData(goodsKey, goods);
			}
		}
		return goods;
	}
}
