package cn.huelmall.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cn.huelmall.domain.Category;
import cn.huelmall.domain.Orders;
import cn.huelmall.domain.Product;
import cn.huelmall.domain.User;
import cn.huelmall.service.AdminService;
import cn.huelmall.utils.CommonsUtils;

/**
 * 后台功能控制层
 * @author: Administrator
 * @function:TODO
 * @time:  2018年6月29日
 */
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;


	/**
	 * 查询所有用户
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/findAllUser")
	public String findAllUser(Model model){
		List<User> users = adminService.findAllUser();
		model.addAttribute("users", users);
		return "/admin/user/list.jsp";
	}
	

	/**
	 * 编辑
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/edit")
	public String editUser(Model model,String uid){
		User user = adminService.editUser(uid);
		model.addAttribute("user", user);
		return "/admin/user/edit.jsp";
	}
	
	/**
	 * 修改
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/update")
	public String updateUser(Model model,User user){
		adminService.updateUser(user);
		return "redirect:/admin/findAllUser.action";
	}
	/**
	 * 查询所有订单
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/findAllOrder")
	public String findAllOrder(Model model){
		List<Orders> ordersList= adminService.findAllOrder();
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("flag", 0);
		return "/admin/order/list.jsp";
	}
	/**
	 * 查询未发货订单
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/findwfhOrder")
	public String findwfhOrder(Model model){
		List<Orders> ordersList= adminService.findAllOrder();
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("flag", 1);
		return "/admin/order/list.jsp";
	}
	/**
	 * 删除
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/delete.action")
	public String deleteUser(Model model,String uid){
		adminService.deleteUser(uid);
		return "redirect:/admin/findAllUser.action";
	}
	
	
	/**
	 * 根据id删除商品
	 * 
	 * @param pid
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/delProduct")
	public String delProduct(String pid, HttpServletRequest req, HttpServletResponse resp) {
		adminService.delProduct(pid);
		return "redirect:/admin/findAllProduct.action";

	}

	/**
	 * 数据显示在edit表单
	 * 
	 * @param product
	 * @param req
	 * @param resp
	 * @return
	 */

	@RequestMapping("/admin/editProduct")
	public String editProduct(Model model, String pid, HttpServletRequest req, HttpServletResponse resp) {
		Product product = adminService.editProduct(pid);
		List<Category> caregoryList = adminService.findAllCategory();
		Category category = adminService.findCategoryByPid(pid);
		product.setCategory(category);
		String cid = product.getCategory().getCid();
		product.setCategories(caregoryList);
		model.addAttribute("cid", cid);
		model.addAttribute("product", product);
		/**
		 * selected组件分类信息插入
		 */
		return "forward:/admin/product/edit.jsp";

	}
	
	/**
	 * 分类查询
	 * 
	 * @param model
	 * @param pid
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/findCategory")
	public String findCategory(Model model, HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * selected组件分类信息插入
		 */
		List<Category> categoryList = adminService.findAllCategory();
		model.addAttribute("categoryList", categoryList);
		return "forward:/admin/category/list.jsp";
	}

	/**
	 * 根据cid删除商品分类
	 * 
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/delCategory")
	public String delCategory(String cid, Model model, HttpServletRequest req, HttpServletResponse resp) {
		adminService.delCategory(cid);
		return "redirect:/admin/findCategory.action";
	}

	/**
	 * 添加商品分类
	 * 
	 * @param category
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/addCategory")
	public String addCategory(Category category, Model model, HttpServletRequest req, HttpServletResponse resp) {
		category.setCid(CommonsUtils.getUUID());
		adminService.addCategory(category);
		return "redirect:/admin/findCategory.action";
	}

	/**
	 * 编辑分类页面数据回显
	 * 
	 * @param cid
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */

	@RequestMapping("/admin/editCategoryUI")
	public String editCategoryUI(String cid, Model model, HttpServletRequest req, HttpServletResponse resp) {
		Category category = adminService.editCategoryUI(cid);
		model.addAttribute("category", category);
		return "forward:/admin/category/edit.jsp";
	}

	/**
	 * 根据cid修改分类
	 * 
	 * @param cid
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/updateCategory")
	public String updateCategory(String cid, String cname, HttpServletRequest req, HttpServletResponse resp) {
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		adminService.updateCategory(category);
		return "redirect:/admin/findCategory.action";
	}
	
	
	/**
	 * 显示分类
	 * 
	 * @param cid
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/admin/category")
	public String categoryList(Model model) {
		List<Category> catList = adminService.findAllCategory();
		
		model.addAttribute("catList", catList);
		return "/admin/product/add.jsp";
	}
	
	/**
	 * 添加商品
	 * 
	 * @param cid
	 * @param model
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/admin/addProduct",method={RequestMethod.POST,RequestMethod.GET})
	public String addProduct(Product product,Model model,MultipartFile upload,HttpServletRequest request) {
		product.setPid(UUID.randomUUID().toString());
		product.setPdate(new Date());
		
		
		//上传图片
		String picname=UUID.randomUUID().toString();
		
		String oriName=upload.getOriginalFilename();
		String extName=oriName.substring(oriName.lastIndexOf("."));
		
		String path = request.getServletContext().getRealPath("/upload");
		
		try {
			upload.transferTo(new File(path+"/"+picname+extName));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String iPath="/products/1";
		product.setPimage("upload/"+picname + extName);
		adminService.addProduct(product);
		
		return "/admin/findAllProduct.action";
	}
}
