package org.cap.wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.wallet.model.User;
import org.cap.wallet.service.IUserService;
import org.cap.wallet.service.UserServiceImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		IUserService loginService=new UserServiceImpl();
		PrintWriter out=response.getWriter();
		
		String userName=request.getParameter("userName");
		String userPwd=request.getParameter("userPwd");
		
		User login=new User();
		login.setEmailId(userName);
		login.setPassword(userPwd);
		
		
		User validlogin=loginService.login(login.getEmailId(), login.getPassword());
		if(validlogin!=null) {
			HttpSession session=request.getSession();
			//session.setMaxInactiveInterval(1000);
			session.setAttribute("user", validlogin);
				response.sendRedirect("pages/main.html");
				System.out.println(validlogin.toString());
			}else {
				out.println("<b>Sorry!Invalid Login</b>");
				
				request.getRequestDispatcher("index.html").include(request, response);
				//response.sendRedirect("index.html");
			}
		
	}

}
