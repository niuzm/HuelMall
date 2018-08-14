package cn.huelmall.web.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huelmall.domain.Cart;
import cn.huelmall.domain.CartItem;
import cn.huelmall.domain.Orderitem;
import cn.huelmall.domain.Orders;
import cn.huelmall.domain.User;
import cn.huelmall.service.OrderService;
import cn.huelmall.utils.CommonsUtils;

/**
 * 订单模块控制层
 * 
 * @time 2018/6/27
 *
 */
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	
	/**
	 * 我的订单显示
	 */
	@RequestMapping("/customer/order")
	public String showOrder() {
		return "/order_list.jsp";
	}

	/**
	 * 提交订单
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/order/submitOrders")
	public String submitOrders(HttpServletRequest req, HttpServletResponse resp) {
		Orders orders = new Orders();
		// 先判断是否登录
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("existUser");
		if (user == null) {
			return "redirect:/login.jsp";
		}
		// 目的：封装好一个orders对象 传递给orderService
		// 封装oid
		String oid = CommonsUtils.getUUID();
		orders.setOid(oid);
		// 下单的时间
		Date nowDate = new Date();
		// 转换时间格式(因为数据库中是datatime类型)
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orders.setOrdertime(Timestamp.valueOf(simpleDate.format(nowDate)));
		// private Double total;总金额
		// 获得购物车
		Cart cart = (Cart) session.getAttribute("cart");
		orders.setTotal(cart.getTotal());
		// private Integer state;支付状态(默认未支付 0)
		orders.setState(1);
		// private String address;收货人地址(默认为null)
		orders.setAddress(null);
		// private String name;收货人姓名(默认为null)
		orders.setName(null);
		// private String telephone;收货人电话号码(默认为null)
		orders.setTelephone(null);
		// private User user;//相当于数据库中的uid(将这个uid对应的user对象封装到orders中)
		orders.setUser(user);
		// 该订单中有多少订单项
		// List<OrderItem> orderItems = new ArrayList<OrderItem>();
		List<CartItem> cartItemList = cart.getCartItemList();
		for (CartItem cartItem : cartItemList) {
			// 根据购物车的购物项来对订单项进行数据封装
			Orderitem orderitem = new Orderitem();
			// private String itemid;订单项id
			orderitem.setItemid(CommonsUtils.getUUID());
			// private Integer count;订单项内商品的购买数量
			orderitem.setCount(cartItem.getNumber());
			// private Double subtotal;小计
			orderitem.setSubtotal(cartItem.getSubtotal());
			// private Product product;订单项内部的商品
			orderitem.setProduct(cartItem.getProduct());
			// private Orders orders; 该订单项属于那个订单
			orderitem.setOrders(orders);
			// 将该订单项放到订单项集合中
			List<Orderitem> orderitems = orders.getOrderitems();
			orderitems.add(orderitem);
		}
		// orders订单数据封装完毕,传到service的层
		orderService.submitOrders(orders);
		// 清空购物车
		session.setAttribute("cart", null);
		session.setAttribute("orders", orders);
		return "redirect:/order_info.jsp";
	}

	/**
	 * 确认订单
	 * 
	 * @param oid
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/order/firmOrder")
	public String firmOrder(Orders orders, String oid, HttpServletRequest req, HttpServletResponse resp) {
		String address = req.getParameter("address");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		orders.setOid(oid);
		orders.setName(name);
		orders.setTelephone(telephone);
		orders.setAddress(address);
		orderService.firmOrder(orders);
		return "redirect:/default.jsp";
	}
}
