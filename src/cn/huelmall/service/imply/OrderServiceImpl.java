package cn.huelmall.service.imply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huelmall.dao.OrdersDao;
import cn.huelmall.domain.Orderitem;
import cn.huelmall.domain.Orders;
import cn.huelmall.service.OrderService;

/**
 * 订单Service操作实现
 * 
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	// 自动注入：默认是按类型
	@Autowired
	private OrdersDao ordersDao;

	/**
	 * 提交订单(事务保证订单和订单项数据插入的一致性)
	 */
	@Transactional(value="transactionManager",rollbackFor=Exception.class)
	@Override
	public void submitOrders(Orders orders) {
		
			  //插入订单
			ordersDao.addOrders(orders);
			//得到全部订单项
			List<Orderitem> orderitems = orders.getOrderitems();
			for (Orderitem orderitem : orderitems) {
				  //插入订单项目
				ordersDao.addOrderItmes(orderitem);
			}
	}
	
	/**
	 * 确认订单
	 */
	@Override
	public void firmOrder(Orders orders) {
		ordersDao.firmOrder(orders);
	}

}
