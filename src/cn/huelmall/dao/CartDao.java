package cn.huelmall.dao;

import cn.huelmall.domain.Product;
/**
 *	购物车Dao操作接口
 * @author: Administrator
 * @function:TODO
 * @time:  2018年6月27日
 */
public interface CartDao {
	
	/**
	 * 根据pid查询商品
	 * @param pid
	 * @return
	 */
	public Product findProductByPid(String pid);

}
