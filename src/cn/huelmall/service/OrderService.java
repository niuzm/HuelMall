package cn.huelmall.service;

import cn.huelmall.domain.Orders;

/**
 * 订单Service操作接口
 *
 */
public interface OrderService {
	//提交订单
	public void submitOrders(Orders orders);
	//确认订单
	public void firmOrder(Orders orders);

}
