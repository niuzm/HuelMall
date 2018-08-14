package cn.huelmall.dao;

import java.util.List;

import cn.huelmall.domain.Category;
import cn.huelmall.domain.Orders;
import cn.huelmall.domain.Product;
import cn.huelmall.domain.User;

/**
 * 管理员Dao接口
 * 
 * @author:
 * @function:TODO
 * @time: 2018年6月26日
 */
public interface AdminDao {

	public List<Product> findAllProduct();

	public void delProduct(String pid);

	public Product editProduct(String pid);

	public List<Category> findAllCategory();

	public void delCategory(String cid);

	public void addCategory(Category category);

	public Category editCategoryUI(String cid);

	public void updateCategory(Category category);

	public Category findCategoryByPid(String pid);

	public void updateProduct(Product product);

	
	


	
	

	

	public void addProduct(Product product);

	public List<User> findAllUser();
	/**
	 * 查询所有用户
	 * @return
	 */
	
	public User editUser(String uid);

	public void updateUser(User user);

	public List<Orders> findAllOrder();

	public void deleteUser(String uid);
}
