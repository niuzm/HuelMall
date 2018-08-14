package cn.huelmall.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huelmall.domain.Orders;
import cn.huelmall.domain.PageBean;
import cn.huelmall.domain.User;
import cn.huelmall.service.IUserService;
import cn.huelmall.utils.CommonsUtils;
import cn.huelmall.utils.MD5Utils;

/**
 * 用户控制器
 * 
 * @author 
 *
 */
@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	/**
	 * 实现用户登陆 登陆成功后将用户名保存在session里
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/login")
	public String login(User user, HttpServletRequest req) {
		// 一次性验证码程序:
		String code1 = req.getParameter("code");
		String code2 = (String) req.getSession().getAttribute("code");
		req.getSession().removeAttribute("code");
		if (!code1.equalsIgnoreCase(code2)) {
			req.setAttribute("msg", "验证码输入错误!");
			return "/login.jsp";
		}
		User findUser = userService.findByUser(user);
		// 判断登陆是否正确
		if ( findUser!= null) {
			//使用MD5算法工具类，加密密码
			String md5Password = MD5Utils.md5(findUser.getPassword());
			System.out.println(md5Password);
			req.getSession().setAttribute("existUser", findUser);
			return "/index.jsp";
		} else {
			return "/login.jsp";
		}

	}

	/**
	 * 退出登陆
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/user/loginOut")
	public String loginOut(HttpServletRequest req) {
		req.getSession().invalidate();

		return "redirect:/login.jsp";
	}

	/**
	 * 实现用户注册
	 * 
	 * @param user
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/user/register")
	public String register(User user, HttpServletRequest req) throws ParseException {
		System.out.println("插入");
		user.setUid(CommonsUtils.getUUID());
		String birthdayStr = (String) req.getParameter("date");
		Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
		user.setBirthday(birthday);
		user.setCode(CommonsUtils.getUUID());
		user.setState(0);
		userService.insertUser(user);
		return "redirect:/login.jsp";

	}

	/**
	 * 校验表单用户名是否存在
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/user/checkUsername")
	public void checkUsername(User user, HttpServletRequest req, HttpServletResponse resp)
			throws ParseException, IOException {
		// 获得用户名
		String username = req.getParameter("username");
		boolean isExist = userService.checkUsername(username);
		String json = "{\"isExist\":" + isExist + "}";
		resp.getWriter().write(json);

	}
	/**
	 * 我的订单
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/order/myOrder")
	public String ordershow(String uid,Model model,HttpServletRequest request) {
		int page=1; //初始化当前页
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));//获取当前页
		}
		int pageSize=10;//每页显示的数量
		int totalnumber=userService.orderCountByUid(uid);//总数量
		int totalPage=userService.getTotalPage(pageSize, uid);//总页数
		
		List<Orders> MyOrdersList = userService.findMyOrder(uid, page, pageSize);
		
		PageBean<Orders> pb=new PageBean<>();
		pb.setCurrentPage(page);
		pb.setTotalNumber(totalnumber);
		pb.setPageSize(pageSize);
		pb.setTotalPage(totalPage);
		pb.setDateList(MyOrdersList);
		
		request.getSession().setAttribute("pageBean", pb);
		return "redirect:/order_list.jsp";
	} 

}
