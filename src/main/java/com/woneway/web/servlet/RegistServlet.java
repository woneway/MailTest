package com.woneway.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woneway.po.User;
import com.woneway.service.UserService;
import com.woneway.service.impl.UserServiceImpl;
import com.woneway.utils.MailUtil;
import com.woneway.utils.UUIDUtils;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	 {
		try{
		//接收数据
		//处理中文乱码
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		//封装数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setState(0); //0:未激活  1:已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//64位
		user.setCode(code);
		
		//调用业务层处理数据
		UserService userService = new UserServiceImpl();
		userService.regist(user);
		
		request.setAttribute("msg", "您已经注册成功！请去邮箱激活。");
		request.getRequestDispatcher("msg.jsp").forward(request, response);
		}catch(Exception e){
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
