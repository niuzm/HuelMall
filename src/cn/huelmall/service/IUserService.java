package cn.huelmall.service;

import java.util.List;

import cn.huelmall.domain.Orders;
import cn.huelmall.domain.User;

/**
 * 用户Service操作接口
 *
 */
public interface IUserService {
    /**
     * 用户登陆业务处理
     * @param user
     * @return
     */
	public User findByUser(User user);
	/**
	 * 注册用户
	 * @param user
	 */
	public void insertUser(User user);
	/**
	 * 检验用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean checkUsername(String username);
	/**
	 * 查询订单
	 * @param uid
	 * @return List<Orders> 
	 */
	public List<Orders> findMyOrder(String uid,int start,int pageSize);

	/**
	 * 通过uid查询订单项数
	 */
	public int orderCountByUid(String uid);
	
	/**
	 * 获取总页数
	 */
	public int getTotalPage(int pageSize,String uid);
}
