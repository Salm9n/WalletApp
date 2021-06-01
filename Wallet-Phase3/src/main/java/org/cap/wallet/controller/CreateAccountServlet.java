package org.cap.wallet.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.User;
import org.cap.wallet.model.Account.AccountType;
import org.cap.wallet.service.AccountServiceImpl;
import org.cap.wallet.service.IAccountService;
import org.cap.wallet.service.IUserService;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountServiceImpl accountService = new AccountServiceImpl();
		Account account = new Account();
		User user = new User();
		
		String description = request.getParameter("description");
		String balance = request.getParameter("balance");
		account.setAccountType(AccountType.SAVINGS);
		if (request.getParameter("type").equals("CHECKINGS"))
			account.setAccountType(AccountType.CHECKINGS);
		
		account.setBalance(Double.valueOf(balance));
		account.setDescription(description);
		account.setAccountId(1);
		account.setOpeningDate(LocalDate.now());
		user.setUserId(1);
		
		
		accountService.addAccount(account, user);
		
	}

}
