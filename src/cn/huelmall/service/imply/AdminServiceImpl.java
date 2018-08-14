package cn.huelmall.service.imply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huelmall.dao.AdminDao;
import cn.huelmall.domain.Category;
import cn.huelmall.domain.Orders;
import cn.huelmall.domain.Product;
import cn.huelmall.domain.User;
import cn.huelmall.service.AdminService;

/**
 * Admin后台service操作实现
 * @author Administrator
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	// 自动注入：默认是按类型
	@Autowired
	private AdminDao adminDao;

	/**
	 * 查询全部商品业务
	 */
	@Override
	public List<Product> findAllProduct() {
		List<Product> productList = adminDao.findAllProduct();
		return productList;
	}
	/**
	 * 根据pid删除商品
	 */
	@Override
	public void delProduct(String pid) {
		adminDao.delProduct(pid);
	}
	/**
	 * 根据pid编辑商品
	 */
	@Override
	public Product editProduct(String pid) {
		Product product = adminDao.editProduct(pid);
		return product;
	}
	/**
	 * 查询全部分类
	 */
	@Override
	public List<Category> findAllCategory() {
		List<Category> categoryList = adminDao.findAllCategory();
		return categoryList;
	}
	/**
	 * 根据cid删除分类
	 */
	@Override
	public void delCategory(String cid) {
		adminDao.delCategory(cid);
	}
	/**
	 * 添加分类
	 */
	@Override
	public void addCategory(Category category) {
		adminDao.addCategory(category);
	}
	/**
	 * 根据cid回显分类
	 */
	@Override
	public Category editCategoryUI(String cid) {
		Category category =adminDao.editCategoryUI(cid);
		return category;
	}
	/**
	 * 修改分类
	 */
	@Override
	public void updateCategory(Category category) {
		adminDao.updateCategory(category);
	}
	/**
	 * 根据pid查询分类
	 */
	@Override
	public Category findCategoryByPid(String pid) {
		Category category=adminDao.findCategoryByPid(pid);
		return category;
	}

	@Override
	public void updateProduct(Product product) {
		adminDao.updateProduct(product);
		
	}

	

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		adminDao.addProduct(product);
		
	}

	@Override
	public List<User> findAllUser() {
		return adminDao.findAllUser();
	}

	@Override
	public User editUser(String uid) {
		return adminDao.editUser(uid);
	}

	@Override
	public void updateUser(User user) {
		
		adminDao.updateUser(user);
		
	}

	@Override
	public List<Orders> findAllOrder() {
		return adminDao.findAllOrder();
	}

	@Override
	public void deleteUser(String uid) {
		adminDao.deleteUser(uid);
	}
	


}
