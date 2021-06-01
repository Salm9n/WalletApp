package org.cap.wallet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.wallet.model.Account;
import org.cap.wallet.service.AccountServiceImpl;
import org.cap.wallet.service.IAccountService;
import org.cap.wallet.service.IUserService;
import org.cap.wallet.service.UserServiceImpl;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IAccountService accountService = new AccountServiceImpl();
		IUserService userService = new UserServiceImpl();
		
		Account account = accountService.getAccount(Integer.parseInt(request.getParameter("accId")));
		userService.withdraw(account, account,request.getParameter("description"),Double.parseDouble(request.getParameter("balance")));
		response.sendRedirect("pages/redirect.html");
	}

}
