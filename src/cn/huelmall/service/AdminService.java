package cn.huelmall.service;

import java.util.List;

import cn.huelmall.domain.Category;
import cn.huelmall.domain.Orders;
import cn.huelmall.domain.Product;
import cn.huelmall.domain.User;

/**
 * 后台service操作接口
 * 
 * @author: Administrator
 * @function:TODO
 * @time: 2018年6月26日
 */
public interface AdminService {

	//查询全部商品
	public List<Product> findAllProduct();
	//根据pid删除商品
	public void delProduct(String pid);
	//根据pid编辑商品
	public Product editProduct(String pid);
	//查询全部分类
	public List<Category> findAllCategory();
	//根据cid删除分类
	public void delCategory(String cid);
	//添加分类
	public void addCategory(Category category);
	//分类回显页面
	public Category editCategoryUI(String cid);
	//修改分类
	public void updateCategory(Category category);
	//根据pid查询分类
	public Category findCategoryByPid(String pid);
	//修改商品
	public void updateProduct(Product product);
	//添加商品
	public void addProduct(Product product);
	//查询全部用户
	public List<User> findAllUser();
	//根据uid编辑用户
	public User editUser(String uid);
	//修改用户信息
	public void updateUser(User user);
	//查询全部订单
	public List<Orders> findAllOrder();
	//根据uid删除用户
	public void deleteUser(String uid);
}
