package cn.huelmall.service.imply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huelmall.dao.ProductDao;
import cn.huelmall.domain.Category;
import cn.huelmall.domain.Product;
import cn.huelmall.service.ProductService;

/**
 * 商品Service操作实现
 * @author Administrator
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	// 自动注入：默认是按类型
	@Autowired
	private ProductDao productDao;

	

	/**
	 * 根据pid查询商品详情
	 */
	@Override
	public Product productInfo(String pid) {
		Product product =productDao.productInfo(pid);
		return product;
	}

	/**
	 * 获得最热商品
	 */
	@Override
	public List<Product> findHotProduct() {
		List<Product> hotProductList=productDao.findHotProduct();
		return hotProductList;
	}

	/**
	 * 获得最新商品
	 */
	@Override
	public List<Product> findNewProduct() {
		List<Product> newProductList=productDao.findNewProduct();
		return newProductList;
	}

	
	
	/**
	 * 查询全部分类
	 */
	@Override
	public List<Category> findCategory() {
		List<Category> categoryList=productDao.findCategory();
		return categoryList;
	}

	
	
 	/**
 	 * 计算总页数
 	 * @author Administrator
 	 * @param pagesize
 	 */
 	public int getTotalPage(int pagesize,int flag) {
		int totalnum=this.findCount(flag);
		int totalPage=1;
		Double.parseDouble(""+totalnum);
		Double.parseDouble(""+pagesize);
		totalPage=(int) Math.ceil(totalnum/pagesize);
		return totalPage;
		
	}
 	/**
	 * 分页查询
	 * @return
	 */
 	 public List<Product> findProductList(int currentpage,int pageSize,int flag) {
		int start=(currentpage-1)*pageSize;
 		 return productDao.findProductList(start, pageSize, flag);
	}

 	/**
 	 * 分页查询根据id查询商品
 	 */
 	@Override
 	public List<Product> productList(String cid,int currentpage,int pageSize) {
 		int start=(currentpage-1)*pageSize;
 		List<Product> productList=productDao.productList(cid,start,pageSize);
 		return productList;
 	}

	@Override
	public int findCount(int flag) {
		return productDao.findCount(flag);
	}



	@Override
	public void updateProductBypid(Product product) {
		productDao.updateProductBypid(product);
	}
/**
 * 根据cid查总数
 */
	@Override
	public int findCountByCid(String cid) {
		return productDao.findCountByCid(cid);
	}
	/**
	 * 根据cid查总页数
	 */
	@Override
	public int getTotalPageByCid(String cid, int pageSize) {
		
		
		int totalnum=this.findCountByCid(cid);
		int totalPage=1;
		Double.parseDouble(""+totalnum);
		Double.parseDouble(""+pageSize);
		totalPage=(int) Math.ceil(totalnum/pageSize);
		return totalPage;
		
	}

}
