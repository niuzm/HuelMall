package cn.huelmall.dao;

import cn.huelmall.domain.Orderitem;
import cn.huelmall.domain.Orders;
/**
 * 订单Dao操作
 *
 */
public interface OrdersDao {
	
	//插入订单
	public void addOrders(Orders orders);
	//插入订单项
	public void addOrderItmes(Orderitem orderitem);
	//确认订单
	public void firmOrder(Orders orders);

}
