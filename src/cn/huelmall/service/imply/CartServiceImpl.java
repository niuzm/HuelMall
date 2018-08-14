package cn.huelmall.service.imply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huelmall.dao.CartDao;
import cn.huelmall.domain.Product;
import cn.huelmall.service.CartService;


/**
 * 购物车Service操作实现
 * 
 * @author 黄世民
 *
 */
@Service
public class CartServiceImpl implements CartService {
	// 自动注入：默认类型
	@Autowired
	private CartDao cartDao;

	/**
	 * 根据id查询商品Service实现类
	 */
	@Override
	public Product findProductByPid(String pid) {
		Product product = cartDao.findProductByPid(pid);
		return product;
	}

}
