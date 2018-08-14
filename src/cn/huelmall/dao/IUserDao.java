package cn.huelmall.dao;

import java.util.List;

import cn.huelmall.domain.Orders;
import cn.huelmall.domain.User;

/**
 * 用户DAO操作接口
 *  
 *
 */
public interface IUserDao {
    /**
     * 用户登录
     * @param userName
     * @param userPass
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
	public Long checkUsername(String username);
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
}
