package cn.huelmall.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huelmall.domain.Cart;
import cn.huelmall.domain.CartItem;
import cn.huelmall.domain.Product;
import cn.huelmall.service.CartService;

/**
 * 购物车模块控制层
 * 
 * 
 * @function:TODO
 * @time: 2018年6月27日
 */
@Controller
public class CartController {
	@Autowired
	private CartService cartService;

	/**
	 * 添加购物功能
	 * 
	 * @param pid
	 * @param byNum
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/cart/addCart")
	public String addCart(String pid, String byNum, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		// 取得购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}
		// 转换成int类型
		int num = Integer.parseInt(byNum);
		// 获得商品的数量
		System.out.println(byNum);
		//如果俩个添加商品相同就在数量加一
		List<CartItem> cartItemList2 = cart.getCartItemList();
		//定义一个临时变量
		CartItem delCartItem=null;
		for (CartItem cartItem : cartItemList2) {
			String pid2 = cartItem.getProduct().getPid();
			if(pid2.equals(pid))
			{
				delCartItem=cartItem;
				num+=cartItem.getNumber();
			}
		}
		cartItemList2.remove(delCartItem);
		// 将num存到CartItem中
		CartItem cartItem = new CartItem();
		cartItem.setNumber(num);
		// 根据pid查询这个商品
		Product product = cartService.findProductByPid(pid);
		// 将商品封装到cartItem中
		cartItem.setProduct(product);
		// 根据计算得到商品的小计
		double subtotal = product.getShop_price() * num;
		// 将小计封装到cartItem中
		cartItem.setSubtotal(subtotal);

		// 将购物项加入到Cart购物车中
		// 先得到购物项集合
		List<CartItem> cartItemList = cart.getCartItemList();
		// 将cartItem添加到cartItemList
		cartItemList.add(cartItem);
		// 计算总计total
		double total = 0.0d;
		for (CartItem item : cartItemList) {
			total += item.getSubtotal();
		}
		// 添加到总计到购物车中
		cart.setTotal(total);
		// 计算积分存入cart购物车中
		double socre = total * 10;
		cart.setSocre(socre);
		// 将cartItemList添加到Cart中
		cart.setCartItemList(cartItemList);
		session.setAttribute("cart", cart);
		return "redirect:/cart.jsp";
	}

	/**
	 * 清空购物车
	 * @param byNum
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/cart/clearCart")
	public String clearCart(String byNum, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		//手动销毁Session 清空购物车
		session.invalidate();
		return "redirect:/cart.jsp";
		
	}
	
	/**
	 * 删除购物项
	 * @param pid
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/cart/delCartItem")
	public String delCartItem(String pid, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		//得到购物车对象
		Cart cart= (Cart) session.getAttribute("cart");
		//得到购物项集合
		List<CartItem> cartItemList = cart.getCartItemList();
		//定义一个要删除的cartItem购物项
		CartItem delCartItem=null;
		//定义要删除的购物项的小计
		double subtotal=0.0;
		for (CartItem cartItem : cartItemList) {
			String pid2 = cartItem.getProduct().getPid();
			//通过要删除的商品的pid得到要删除的购物项
			if(pid.equals(pid2))
			{
				delCartItem=cartItem;
				subtotal=delCartItem.getSubtotal();
			}
		}
		//得到新的总计和积分减掉被删除项的小计和积分
		double total=cart.getTotal()-subtotal;
		double socre=cart.getSocre()-subtotal*10;
		cart.setSocre(socre);
		cart.setTotal(total);
		cartItemList.remove(delCartItem);
		cart.setCartItemList(cartItemList);
		return "redirect:/cart.jsp";
	}
	
}
