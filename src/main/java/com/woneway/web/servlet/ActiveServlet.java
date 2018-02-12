package com.woneway.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woneway.po.User;
import com.woneway.service.UserService;
import com.woneway.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ActiveServlet
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//接受激活码
			String code = request.getParameter("code");
			
			//根据激活码查询用户
			UserService userService = new UserServiceImpl();
			User user = userService.findByCode(code);
			if(user!=null){
				//已经查询到，修改用户的状态
				user.setState(1);
				user.setCode("");
				userService.update(user);
				request.setAttribute("msg", "恭喜您激活成功！");
			}else{
				//根据激活码没有查询到用户
				request.setAttribute("msg", "对不起，您的激活码已失效，请重新注册");
			}
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
