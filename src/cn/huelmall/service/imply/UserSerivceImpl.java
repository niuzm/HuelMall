package cn.huelmall.service.imply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huelmall.dao.IUserDao;
import cn.huelmall.domain.Orders;
import cn.huelmall.domain.User;
import cn.huelmall.service.IUserService;

/**
 * 用户Service操作实现
 * 
 *
 */
@Service
public class UserSerivceImpl implements IUserService {
	// 自动注入：默认是按类型
	@Autowired
	private IUserDao userDao;

	/**
	 * 用户登陆业务处理
	 * 
	 * @param user
	 * @return
	 */
	public User findByUser(User user) {
		User muser = userDao.findByUser(user);
		System.out.println("user:" + muser);
		return muser;
	}

	public void insertUser(User user) {
		userDao.insertUser(user);

	}

	@Override
	public boolean checkUsername(String username) {
		Long row = 0L;
		row = ((IUserDao) userDao).checkUsername(username);
		return row > 0 ? true : false;
	}

	@Override
	public List<Orders> findMyOrder(String uid, int start, int pageSize) {
		int starts = (start - 1) * pageSize;
		return userDao.findMyOrder(uid, starts, pageSize);
	}

	@Override
	public int orderCountByUid(String uid) {
		return userDao.orderCountByUid(uid);
	}

	/**
	 * 计算总页数
	 * 
	 * @author Administrator
	 * @param pagesize
	 */
	@Override
	public int getTotalPage(int pageSize, String uid) {
		int totalnum = this.orderCountByUid(uid);
		int totalPage = 1;
		Double.parseDouble("" + totalnum);
		Double.parseDouble("" + pageSize);
		totalPage = (int) Math.ceil(totalnum / pageSize);
		return totalPage;
	}

}
