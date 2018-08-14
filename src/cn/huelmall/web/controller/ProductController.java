package cn.huelmall.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import cn.huelmall.domain.Category;
import cn.huelmall.domain.PageBean;
import cn.huelmall.domain.Product;
import cn.huelmall.service.AdminService;
import cn.huelmall.service.ProductService;
import cn.huelmall.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * 前台商品控制层
 * 
 * @author: Administrator
 * @function:TODO
 * @time: 2018年6月27日
 */
@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	/**
	 * adminService注入
	 */
	@Autowired
	private AdminService adminService;

	/**
	 * 显示商品分类 到导航栏中
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping("/product/findCategory")
	public void findCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 先从缓存中查询categoryList 如果有直接使用 没有就从数据库中查询 存到缓存中
		// 1.获得jedis对象 连接redis数据库
		Jedis jedis = JedisPoolUtils.getJedis();
		String categoryListJson = jedis.get("categoryListJson");
		// 2.判断categoryListJson是否为空
		if (categoryListJson == null) {
			System.out.println("缓存没有数据 查询数据库.....");
			List<Category> categoryList = adminService.findAllCategory();
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);
			jedis.set("categoryListJson", categoryListJson);
		}
		//解决中文问题
		resp.setCharacterEncoding("utf-8");
		//发送json到页面
		resp.getWriter().write(categoryListJson);
	}

	/**
	 * 首页最热最新商品
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping("/product/hotProduct")
	public String hotProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Product> hotProductList = productService.findHotProduct();
		List<Product> newProductList = productService.findNewProduct();
		req.setAttribute("hotProductList", hotProductList);
		req.setAttribute("newProductList", newProductList);
		return "forward:/index.jsp";
	}


	/**
	 * 根据pid查询商品详情
	 * 
	 * @param pid
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/product/productInfo")
	public String productInfo(String pid, HttpServletRequest req, HttpServletResponse resp) {
		Product product = productService.productInfo(pid);
		HttpSession session = req.getSession();
		session.setAttribute("product", product);
		return "redirect:/product_info.jsp";
	}
	
	


	/**
	 * 点击不同的分类显示不同的商品列表
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/product/productList")
	public String productList(String cid, HttpServletRequest req, HttpServletResponse resp) {
		int page=1;//初始页
		if(req.getParameter("page")!=null){
			page=Integer.parseInt(req.getParameter("page"));//获取当前页
		}
		int pageSize=12;//每页显示的条数
		int totalnumber=productService.findCountByCid(cid);//根据cid查询总条数
		int totalPage=productService.getTotalPageByCid(cid,pageSize);//根据cid查询总页数
		
		List<Product> productList = productService.productList(cid,page,pageSize);
		PageBean<Product> pb=new PageBean<>();
		pb.setCurrentPage(page);
		pb.setTotalNumber(totalnumber);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		pb.setDateList(productList);
		

		HttpSession session = req.getSession();

		session.setAttribute("pb", pb);
		session.setAttribute("pbcid", cid);
		return "redirect:/product_list.jsp";
	}
	

	/**
	 * 分页查询全部商品
	 * 
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/findAllProduct")
	public String findAllProduct(Model model, HttpServletRequest req, HttpServletResponse resp) {
		int page=1;
		if(req.getParameter("page")!=null){
			page=Integer.parseInt(req.getParameter("page"));
		}
		int pageSize=10;
		int totalnumber=productService.findCount(0);
		int totalPage=productService.getTotalPage(pageSize, 0);
		List<Product> productList = productService.findProductList(page, pageSize, 0);
		PageBean<Product> pb=new PageBean<>();
		pb.setCurrentPage(page);
		pb.setTotalNumber(totalnumber);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		pb.setDateList(productList);
		
		model.addAttribute("pageBean", pb);
		return "/admin/product/list.jsp";
	}
	
	
	
	
	/**
	 * 修改商品
	 * 
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/updateProducta")
	public String updateProduct(Product product,String pid,Model model, HttpServletRequest req, HttpServletResponse resp) {
		product.setPid(pid);
		productService.updateProductBypid(product);
		System.out.println(pid);
		return "redirect:/admin/findAllProduct.action";
	}
}
