package cn.huelmall.dao;

import java.util.List;

import cn.huelmall.domain.Category;
import cn.huelmall.domain.Product;
/**
 * 前台页面接口操作
 * @author: Administrator
 * @function:TODO
 * @time:  2018年6月27日
 */
public interface ProductDao {

	//根据cid查询商品列表
	public List<Product> productList(String cid,int start,int pagesize);

	//根据pid查询商品详细
	public Product productInfo(String pid);
	//查询最热商品
	public List<Product> findHotProduct();
	//查询最新商品
	public List<Product> findNewProduct();
	
	
	
	/**
	 * 统计总条数
	 * @return
	 */
		public int findCount(int flag);
		
		/**
		 * 分页查询
		 * @return
		 */
		List<Product> findProductList(int start,int pageSize,int flag);
		
		public List<Category> findCategory();

		public void updateProductBypid(Product product);
		/**
		 * 根据cid查总数
		 */
	
		public int findCountByCid(String cid);
	
}
