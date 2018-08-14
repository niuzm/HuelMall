package cn.huelmall.service;

import java.util.List;

import cn.huelmall.domain.Category;
import cn.huelmall.domain.Product;
/**
 * 前台商品Service操作接口
 *
 */
public interface ProductService {
	
	/**
	 * 根据cid查询所属商品
	 * @param cid
	 * @return
	 */
	public List<Product> productList(String cid,int start,int pageSize);
	/**
	 * 根据pid查询商品详情
	 * @param pid
	 * @return
	 */
	public Product productInfo(String pid);
	/**
	 * 获得最热商品
	 * @return
	 */
	public List<Product> findHotProduct();
	
	/**
	 * 获得最新商品
	 * @return
	 */
	public List<Product> findNewProduct();
	
	
	
/**
 * 查询分类信息
 * @return
 */
 	public List<Category> findCategory();
 	
 	/**
 	 * 计算总页数
 	 * @author Administrator
 	 * @param pagesize
 	 */
 	public int getTotalPage(int pagesize,int flag);
 	/**
	 * 分页查询
	 * @return
	 */
	List<Product> findProductList(int currentpage,int pageSize,int flag);
	/**
	 * 统计商品数量根据flag
	 * @param flag
	 * @return
	 */
	public int findCount(int flag);
	/**
	 * 修改商品信息
	 * @param product
	 */
	public void updateProductBypid(Product product);
	/**
	 * 根据cid统计商品数量
	 * @param cid
	 * @return
	 */
	public int findCountByCid(String cid);
	/**
	 * 根据cid统计总页数
	 * @param cid
	 * @param pageSize
	 * @return
	 */
	public int getTotalPageByCid(String cid, int pageSize);

}
