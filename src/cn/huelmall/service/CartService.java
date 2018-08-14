package cn.huelmall.service;

import cn.huelmall.domain.Product;
/**
 * 购物车service操作接口
 *
 */
public interface CartService {
	/**
	 * 根据pid查询商品
	 * @param pid
	 * @return
	 */
	Product findProductByPid(String pid);

}
