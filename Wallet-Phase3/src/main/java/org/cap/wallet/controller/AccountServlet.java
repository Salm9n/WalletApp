package org.cap.wallet.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.User;
import org.cap.wallet.model.Account.AccountType;
import org.cap.wallet.service.AccountServiceImpl;
import org.cap.wallet.service.IAccountService;
import org.cap.wallet.service.IUserService;
import org.cap.wallet.service.UserServiceImpl;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAccountService accountService = new AccountServiceImpl();
		Account account = new Account();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		String description = request.getParameter("description");
		String balance = request.getParameter("balance");
		account.setAccountType(AccountType.SAVINGS);
		if (request.getParameter("type").equals("CHECKINGS"))
			account.setAccountType(AccountType.CHECKINGS);
		
		account.setBalance(Double.valueOf(balance));
		account.setDescription(description);
		account.setAccountId(1);
		account.setOpeningDate(LocalDate.now());
		user.setUserId(user.getUserId());
		
		accountService.addAccount(account, user);
		
		if( account != null) {
			response.getWriter().println("Account create Successfully");
			response.sendRedirect("pages/redirect.html");
		}
		//userService.addAccount(account, user);
	}

}
